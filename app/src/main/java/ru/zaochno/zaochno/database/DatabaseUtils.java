package ru.zaochno.zaochno.database;

import android.content.ContentValues;
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
        String selection = DatabaseContract.CategoryEntry.COLUMN_NAME_PARENT_CATEGORY_NAME_ID + " IS NULL";
        String[] selectionArgs = null;

        // How you want the results sorted in the resulting Cursor
        String sortOrder = null;

        List<Category> categories = new LinkedList<>();
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

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryEntry.COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryEntry.COLUMN_NAME_NAME));
            category = new Category(id, name, null);
            categories.add(category);
        }
        cursor.close();
        mDatabaseHelper.close();

        return categories.toArray(new Category[categories.size()]);

    }

    public void saveCategories(Category[] categories) {
        if (categories == null) return;
        // Gets the data repository in write mode
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        for (int i = 0; i < categories.length; i++) {
// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.CategoryEntry.COLUMN_NAME_ID, categories[i].getCategoryId());
            values.put(DatabaseContract.CategoryEntry.COLUMN_NAME_NAME, categories[i].getCategoryName());
            // Insert the new row, returning the primary key value of the new row
           /* long newRowId = */
            db.insert(DatabaseContract.CategoryEntry.TABLE_NAME, null, values);
        }
        mDatabaseHelper.close();

    }
}
