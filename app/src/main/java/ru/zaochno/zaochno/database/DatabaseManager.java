package ru.zaochno.zaochno.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.model.Category;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class DatabaseManager {

    private static DatabaseManager INSTANCE=new DatabaseManager();
    private DatabaseUtils mDatabaseUtils;

    private ExecutorService executorService;

    private DatabaseManager(){
        executorService= Executors.newFixedThreadPool(1);
        mDatabaseUtils=new DatabaseUtils(new DatabaseHelper(MainApplication.getInstance()));
    }

    public static DatabaseManager getInstance(){
        return INSTANCE;
    }

    public void getCategories(DatabaseCallBack<Category[]> callBack){
        executorService.submit(new LoadCategories(callBack));
    }

    public void saveCategories(Category[] categories){
        executorService.submit(new SaveCategories(categories));
    }


    class LoadCategories implements Runnable{
        private DatabaseCallBack<Category[]> callBack;
        LoadCategories(DatabaseCallBack<Category[]> callBack){
            this.callBack=callBack;
        }
        @Override
        public void run() {
            final Category[] categories=mDatabaseUtils.getCategories();
            if(callBack!=null) {
                callBack.returnData(categories);
            }
        }
    }
    class SaveCategories implements Runnable{
        private Category[] categories;

        SaveCategories(Category[] categories){
            this.categories=categories;
        }
        @Override
        public void run() {
            Category[] cachedCategories=mDatabaseUtils.getCategories();
            Set<Category> cachedCategoriesSet=new HashSet<>(Arrays.asList(cachedCategories));
            Set<Category> newCategoriesSet=new HashSet<>(Arrays.asList(categories));
            Set<Category> addSet=new HashSet<>();
            addSet.addAll(newCategoriesSet);
//            int newCategoriesSetSize=addSet.size();
            addSet.removeAll(cachedCategoriesSet);
            if(addSet.size()>0){
                mDatabaseUtils.saveCategories(addSet.toArray(new Category[addSet.size()]));
            }
        }
    }
}
