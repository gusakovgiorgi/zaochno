package ru.zaochno.zaochno.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.model.Category;
import ru.zaochno.zaochno.model.Training;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class DatabaseManager {

    private static DatabaseManager INSTANCE = new DatabaseManager();
    private DatabaseUtils mDatabaseUtils;
    private ExecutorService executorService;
    private Training[] trainings;
    private  boolean loadFromInternet;
    private List<DatabaseCallBack<Training[]>> mWaitingCallbacks=new LinkedList<>();


    private DatabaseManager() {
        executorService = Executors.newFixedThreadPool(1);
        mDatabaseUtils = new DatabaseUtils(new DatabaseHelper(MainApplication.getInstance()));
    }

    public static DatabaseManager getInstance() {
        return INSTANCE;
    }

    public void startLoadingFromInternet(){
        loadFromInternet=true;
    }
    public boolean isLoadingFromInternet(){
        return loadFromInternet;
    }
    public synchronized void stopLoadingFromInternet(){
        loadFromInternet=false;
        if(mWaitingCallbacks.size()>0){
            executorService.submit(new LoadTrainings(mWaitingCallbacks));
        }
    }

    public void getCategories(DatabaseCallBack<Category[]> callBack) {
        executorService.submit(new LoadCategories(callBack));
    }

    public void saveCategories(Category[] categories) {
        executorService.submit(new SaveCategories(categories));
    }

    public void saveTrainings(Training[] trainings) {
        this.trainings=trainings;
        executorService.submit(new SaveTrainings(trainings));
    }

    public void getTrainings(DatabaseCallBack<Training[]> callBack){
        if(isLoadingFromInternet()){
            waitingUntilLoadEnd(callBack);
        }else if(this.trainings!=null){
            if(callBack!=null){
                callBack.returnData(this.trainings);
            }
        }else {
            executorService.submit(new LoadTrainings(callBack));
        }
    }

    private synchronized void waitingUntilLoadEnd(DatabaseCallBack<Training[]> callBack) {
        mWaitingCallbacks.add(callBack);
    }


    class LoadCategories implements Runnable {
        private DatabaseCallBack<Category[]> callBack;

        LoadCategories(DatabaseCallBack<Category[]> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void run() {
            final Category[] categories = mDatabaseUtils.getCategories();
            if (callBack != null) {
                callBack.returnData(categories);
            }
        }
    }

    class SaveCategories implements Runnable {
        private Category[] categories;

        SaveCategories(Category[] categories) {
            this.categories = categories;
        }

        @Override
        public void run() {
            Category[] cachedCategories = mDatabaseUtils.getCategories();
            Set<Category> cachedCategoriesSet = new HashSet<>(Arrays.asList(cachedCategories));
            Set<Category> newCategoriesSet = new HashSet<>(Arrays.asList(categories));
            Set<Category> addSet = new HashSet<>();
            addSet.addAll(newCategoriesSet);
//            int newCategoriesSetSize=addSet.size();
            addSet.removeAll(cachedCategoriesSet);
            if (addSet.size() > 0) {
                mDatabaseUtils.saveCategories(addSet.toArray(new Category[addSet.size()]));
            }
        }
    }

    class LoadTrainings implements Runnable {
        private DatabaseCallBack<Training[]> callBack;
        private List<DatabaseCallBack<Training[]>> waitingCallBacks;

        LoadTrainings(DatabaseCallBack<Training[]> callBack) {
            this.callBack = callBack;
        }
        LoadTrainings(List<DatabaseCallBack<Training[]>> waitingCallBacks) {
            this.waitingCallBacks = waitingCallBacks;
        }

        @Override
        public void run() {
            final Training[] trainings = mDatabaseUtils.getTrainings();
            if (callBack != null) {
                callBack.returnData(trainings);
            }
            if(this.waitingCallBacks!=null && this.waitingCallBacks.size()>0){
                for(DatabaseCallBack<Training[]> callBack : this.waitingCallBacks){
                    callBack.returnData(trainings);
                }
                waitingCallBacks.clear();
            }
        }
    }

    class SaveTrainings implements Runnable {
        private Training[] trainings;

        SaveTrainings(Training[] trainings) {
            this.trainings = trainings;
        }

        @Override
        public void run() {
            Training[] cachedTrainings = mDatabaseUtils.getTrainings();
            Set<Training> cachedTrainingsSet = new HashSet<>(Arrays.asList(cachedTrainings));
            Set<Training> newTrainingsSet = new HashSet<>(Arrays.asList(trainings));
            Set<Training> addSet = new HashSet<>();
            addSet.addAll(newTrainingsSet);
//            int newCategoriesSetSize=addSet.size();
            addSet.removeAll(cachedTrainingsSet);
            //todo release remove unneeded fields from database here in other ruunable too
            if(addSet.size()>0) {
                mDatabaseUtils.saveTrainings(trainings);
            }
            stopLoadingFromInternet();
        }
    }
}
