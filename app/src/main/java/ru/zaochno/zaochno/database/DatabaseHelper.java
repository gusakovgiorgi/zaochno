package ru.zaochno.zaochno.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_CATEGORY_ENTRY =
            "CREATE TABLE " + DatabaseContract.CategoryEntry.TABLE_NAME + " (" +
                    DatabaseContract.CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.CategoryEntry.COLUMN_NAME_ID + " INTEGER NOT NULL UNIQUE," +
                    DatabaseContract.CategoryEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.CategoryEntry.COLUMN_NAME_PARENT_CATEGORY_NAME_ID + " INTEGER)";

    private static final String SQL_DELETE_CATEGORY_ENTRY =
            "DROP TABLE IF EXISTS " + DatabaseContract.CategoryEntry.TABLE_NAME;

    private static final String SQL_CREATE_TRAINING_ENTRY =
            "CREATE TABLE " + DatabaseContract.TrainingsEntry.TABLE_NAME + " (" +
                    DatabaseContract.TrainingsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_ID + " INTEGER NOT NULL UNIQUE," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_PAYMENT + " INTEGER," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_ICO_URL + " TEXT," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_SHORT_TEXT + " TEXT," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_FAVORITE + " INTEGER," +
                    DatabaseContract.TrainingsEntry.COLUMN_NAME_CATEGORY_ID + " INTEGER)";

    private static final String SQL_DELETE_TRAINING_ENTRY =
            "DROP TABLE IF EXISTS " + DatabaseContract.TrainingsEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORY_ENTRY);
        db.execSQL(SQL_CREATE_TRAINING_ENTRY);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_CATEGORY_ENTRY);
        db.execSQL(SQL_DELETE_TRAINING_ENTRY);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
