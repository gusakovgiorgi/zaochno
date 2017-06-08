package ru.zaochno.zaochno.database;

import android.provider.BaseColumns;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_ID = "category_id";
        public static final String COLUMN_NAME_NAME = "category_name";
        public static final String COLUMN_NAME_PARENT_CATEGORY_NAME_ID = "category_parent_id";

    }
}
