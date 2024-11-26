package com.example.app.db


import android.provider.BaseColumns

object FeedReaderContract {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_DESCRIPTION = "description"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME} TEXT," +
                "${FeedEntry.COLUMN_PRICE} REAL," +
                "${FeedEntry.COLUMN_IMAGE} TEXT," +
                "${FeedEntry.COLUMN_DESCRIPTION} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}
