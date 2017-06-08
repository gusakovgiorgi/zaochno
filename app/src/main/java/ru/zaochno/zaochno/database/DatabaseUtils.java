package ru.zaochno.zaochno.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import ru.zaochno.zaochno.model.Category;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

class DatabaseUtils {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDataBase;


    DatabaseUtils(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    public Category[] getCategories() {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
       // you will actually use after this query.
        String[] projection = {
                DatabaseContract.CategoryEntry._ID,
                DatabaseContract.CategoryEntry.COLUMN_NAME_ID,
                DatabaseContract.CategoryEntry.COLUMN_NAME_NAME,
                DatabaseContract.CategoryEntry.COLUMN_NAME_PARENT_CATEGORY_NAME_ID
        };
        // Filter results WHERE
        String selection = DatabaseContract.CategoryEntry.COLUMN_NAME_PARENT_CATEGORY_NAME_ID + " = ?";
        String[] selectionArgs = { "" };

     // How you want the results sorted in the resulting Cursor
        String sortOrder =null;

        List<Category> categories=new LinkedList<>();
        Category category;

        Cursor cursor = sqLiteDatabase.query(
                DatabaseContract.CategoryEntry.TABLE_NAME,                      // The table to query
                projection,                                                     // The columns to return
                selection,                                                      // The columns for the WHERE clause
                selectionArgs,                                                  // The values for the WHERE clause
                null,                                                           // don't group the rows
                null,                                                           // don't filter by row groups
                sortOrder                                                       // The sort order
        );

        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryEntry.COLUMN_NAME_ID));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryEntry.COLUMN_NAME_NAME));
            category=new Category(id,name,null);
            categories.add(category);
        }

        return categories.toArray(new Category[categories.size()]);

    }
}
