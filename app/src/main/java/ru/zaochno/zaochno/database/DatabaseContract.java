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

    /* Inner class that defines the table contents */
    public static class TrainingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "training";
        public static final String COLUMN_NAME_ID = "training_id";
        public static final String COLUMN_NAME_NAME = "training_name";
        public static final String COLUMN_NAME_PAYMENT = "training_payment";
        public static final String COLUMN_NAME_ICO_URL = "training_ico_url";
        public static final String COLUMN_NAME_SHORT_TEXT = "training_short_text";
        public static final String COLUMN_NAME_FAVORITE = "training_favorite";
        public static final String COLUMN_NAME_CATEGORY_ID = "training_category_id";
    }
}
