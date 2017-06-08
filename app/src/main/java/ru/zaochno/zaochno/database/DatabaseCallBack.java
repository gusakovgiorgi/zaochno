package ru.zaochno.zaochno.database;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public interface DatabaseCallBack<T> {

    void returnData(T data);
}
