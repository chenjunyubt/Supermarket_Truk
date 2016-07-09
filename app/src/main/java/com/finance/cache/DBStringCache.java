package com.finance.cache;

import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.supermarket.utils.TTLog;

class DBStringCache {
    private static final String DBNAME = "ttcache";
    private static final int VERSION = 1;
    private static final String TABLENAME = "ttstrcache";
    
    private static final String CACHEID = "ttcache_id";
    private static final String CACHEURL = "ttcache_url";
    private static final String CACHEPAGE = "ttcache_page";
    private static final String CACHECONTENT = "ttcache_content";
    private static final String CACHESAVETIME = "ttcache_savetime";

    private static final String ATTRIBUTE_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT ";
    private static final String ATTRIBUTE_TEXT = "TEXT";
    private static final String ATTRIBUTE_INT = "INTEGER";
    private static final String ATTRIBUTE_DATETIME = "DATETIME";

    private static final String DROP_TABLE = "drop table ttstrcache";
    HashMap<String, String> createtable;
    private DBHelper mDB;

    public DBStringCache(Context context) {
        createtable = new HashMap<String, String>();
        mDB = new DBHelper(context);
        createtable.put(CACHEID, ATTRIBUTE_KEY);
        createtable.put(CACHEURL, ATTRIBUTE_TEXT);
        createtable.put(CACHEPAGE, ATTRIBUTE_INT);
        createtable.put(CACHECONTENT, ATTRIBUTE_TEXT);
        createtable.put(CACHESAVETIME, ATTRIBUTE_DATETIME);
    }

    public boolean cacheIsAvailable(String url, String page, int availableScope){
        String checkAvailablesql = "select * from " + TABLENAME + " where "
                + " " + CACHEURL
                + " = ? and " // url 相同
                + CACHEPAGE
                + " = ? and " // 页数相同
                + "strftime('%s','now') - strftime('%s'," + CACHESAVETIME + ") <"
                + availableScope; // 在可用范围内 单位为秒
        Cursor cursor = mDB.getReadableDatabase().rawQuery(checkAvailablesql,
                new String[] { url, page });
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.close();
            // 有数据
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public String getCache(String url, String page){
        String getAvailabldataesql = "select * from " + TABLENAME + " where "
                + " " + CACHEURL
                + " = ? and " // url 相同
                + CACHEPAGE
                + " = ?  " ;  //页数相同
        Cursor cursor = mDB.getReadableDatabase().rawQuery(getAvailabldataesql,
                new String[] { url, String.valueOf(page) });
        if(cursor.moveToFirst()){
            int valueindex = cursor.getColumnIndex(CACHECONTENT);
            String s = cursor.getString(valueindex);
            cursor.close();
            return s;
        }else{
            cursor.close();
            return "";
        }
    }
    
    public void putCache(String url, String page, String value){
        String cachedata = value.replace("'", "‘");
        String putcachestr = "replace into " + TABLENAME
                +"("
                +CACHEURL+","
                +CACHEPAGE+","
                +CACHECONTENT+","
                +CACHESAVETIME+","
                +")"
                +" values "
                +"("
                +"'"+ url + "',"
                +"'"+ page + "',"
                +"'"+cachedata+"',"
                +"datetime('now')"
                +")"
                ;
        SQLiteDatabase db = mDB.getWritableDatabase();
        db.beginTransaction();
        try{
            db.execSQL(putcachestr);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            db.endTransaction();
        }
        db.endTransaction();
    }
    
    public void clearToCache(Context context){
    	SQLiteDatabase db = mDB.getWritableDatabase();     	
    	db.execSQL("delete from ttstrcache");    	
    }
    
    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DBNAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {          
            StringBuffer buf = new StringBuffer();
            buf.append("create table " + TABLENAME + "(");
            for (Entry<String, String> entry : createtable.entrySet()) {
                buf.append(" " + entry.getKey() + " " + entry.getValue() + ",");
            }
            
            String rlt = buf.subSequence(0, buf.lastIndexOf(",") - 1).toString();// 去掉最后的 ','
            rlt += ");";
            TTLog.e("db init sql " + buf.toString());
            db.execSQL(rlt);
            db.execSQL("create unique index IK_Url_Page on " + TABLENAME + "(" + CACHEURL + "," + CACHEPAGE + ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }

    }
}