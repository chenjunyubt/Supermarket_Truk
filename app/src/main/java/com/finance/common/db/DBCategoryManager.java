package com.finance.common.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

public class DBCategoryManager {
    private static volatile DBCategoryManager instance;

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase mDb;
    private DatabaseHelper helper;

    private DBCategoryManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public static DBCategoryManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBCategoryManager.class) {
                if (instance == null) {
                    instance = new DBCategoryManager(context.getApplicationContext());
                }
            }
        }

        return instance;
    }

    public int getDB_VERSION() {
        return DatabaseHelper.DB_VERSION;
    }

    private synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database  
            mDb = helper.getWritableDatabase();
        }
        return mDb;
    }

    private synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database  
            if (null != mDb) {
                mDb.close();
            }
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }




}
