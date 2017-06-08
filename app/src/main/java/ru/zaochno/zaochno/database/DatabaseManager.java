package ru.zaochno.zaochno.database;

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


    class LoadCategories implements Runnable{
        DatabaseCallBack<Category[]> callBack;
        LoadCategories(DatabaseCallBack<Category[]> callBack){
            this.callBack=callBack;
        }
        @Override
        public void run() {
            Category[] categories=mDatabaseUtils.getCategories();
            if(callBack!=null) {
                callBack.returnData(categories);
            }
        }
    }
}
