package com.finance.common.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.supermarket.utils.TTLog;

import java.util.Locale;

final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tingting_database";
    static final int DB_VERSION = 14;//2.1.0版本已修改为 14

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the category table
        createCategoryTableQuery(db);

        // Create the home cache(other) table
        createHomeTableQuery(db);

        // Create the home cache(fm) table
        createFmTableQuery(db);

        // Create the favorite audio table
        createFATableQuery(db);

        createFavoriteAlbumTableQuery(db);

        createFavoriteVodTableQuery(db);

        createFavoriteSpeechTableQuery(db);

        createFavoriteFmTableQuery(db);

        createSearchHistoryTableQuery(db);

        createDownloadAlbumTableQuery(db);

        createLetterChatTableQuery(db);

        String letteruserSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_CHAT_USER
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_CHAT_USER_USERID + " INTEGER , "
                + TableStructure.TABLE_CHAT_USER_NICKNAME + " TEXT , "
                + TableStructure.TABLE_CHAT_USER_FACEURL + " TEXT , "
                + TableStructure.TABLE_CHAT_USER_USERSTATUS + " INTEGER , "
                + TableStructure.TABLE_CHAT_USER_SENDTIME + " INTEGER , "
                + TableStructure.TABLE_CHAT_USER_AUDIOTEXT + " TEXT , "
                + TableStructure.TABLE_CHAT_USER_MESSAGEYPTE + " INTEGER , "
                + TableStructure.TABLE_CHAT_USER_USER_ID + " INTEGER , "
                + TableStructure.TABLE_CHAT_USER_CHATNUM + " INTEGER);";
        db.execSQL(letteruserSql);

        String downLoadDataSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_DOWNLOAD
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_TITLE + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_VOD_ID + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_AUDIO_NAME + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_DOWNLOAD_URL + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_DURATION + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_PLAY_URL + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_DOWNLOAD_FLAG + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_CONTEXT_LENGTH + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_FILE_PATH + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_USER_ID + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_DOWNLOAD_LENGTH + " INTEGER);";
        db.execSQL(downLoadDataSql);

        String discoverDataSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_DISCOVER
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_DISCOVER_UNIQUE + " TEXT " + " NOT NULL UNIQUE, "
                + TableStructure.TABLE_DISCOVER_CONTENT + " TEXT);";
        db.execSQL(discoverDataSql);

        createPlayTableQuery(db);

        upData(db, 1, -1);//增加所有表结构
    }

    /**
     * 增加 专题的收藏信息表
     *
     * @param db
     */
    private void createSpecialTableQuery(SQLiteDatabase db) {
        String subscribeSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_SPECIAL
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_SPECIAL_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_SPECIAL_TITLE + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_SPECIAL_COVER_BASE_URL + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_SPECIAL_PLAY_TIMES + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_SPECIAL_MTIME + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_SPECIAL_RECOMMENDATION + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_SPECIAL_USER_ID + " INTEGER DEFAULT 0 );";
        db.execSQL(subscribeSql);
    }

    /**
     * 增加订阅表
     *
     * @param db
     */
    private void createSubscribeTableQuery(SQLiteDatabase db) {
        String subscribeSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_SUBSCRIBE
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_SUBSCRIBE_LIST_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_SUBSCRIBE_VOD_ID + " INTEGER);";
        db.execSQL(subscribeSql);
    }

    private void createLetterChatTableQuery(SQLiteDatabase db) {
        String letterchatSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_LETTER_CHAT
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_LETTER_CHAT_MESSAGEID + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_SENDUSERID + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_OWNUSERID + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_ANOTHERID + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_SENDTIME + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_AUDIOTEXT + " TEXT , "
                + TableStructure.TABLE_LETTER_CHAT_AUDIOFULLURL + " TEXT , "
                + TableStructure.TABLE_LETTER_CHAT_MESSAGETYPE + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_DURATION + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_SENDFLAG + " INTEGER , "
                + TableStructure.TABLE_LETTER_CHAT_TYPE + " INTEGER);";
        db.execSQL(letterchatSql);
    }

    private void createDownloadAlbumTableQuery(SQLiteDatabase db) {
        String downLoadAlbumSql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_DOWNLOAD_ALBUM
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_TITLE + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_COVER_URL + " TEXT , "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_IS_END + " INTEGER , "
                + TableStructure.TABLE_DOWNLOAD_ALBUM_TYPE_ID + " INTEGER);";
        db.execSQL(downLoadAlbumSql);
    }

    private void createSearchHistoryTableQuery(SQLiteDatabase db) {
        String searchHistorySql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_SEARCH_HISTORY
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT , "
                + TableStructure.TABLE_SEARCH_HISTORY_USER_ID + " INTEGER , "
                + TableStructure.TABLE_SEARCH_HISTORY_VALUE + " TEXT UNIQUE , "
                + TableStructure.TABLE_SEARCH_HISTORY_TYPE_KEY + " INTEGER);";
        db.execSQL(searchHistorySql);
    }

    private void createFavoriteFmTableQuery(SQLiteDatabase db) {
        String radiosql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_RADIO
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_MYRADIO_FM_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_MYRADIO_NAME + " TEXT , "
                + TableStructure.TABLE_FAVORITE_MYRADIO_COVER_URL + " TEXT , "
                + TableStructure.TABLE_FAVORITE_MYRADIO_TOTAL_PLAY_TIMES + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_MYRADIO_FM_IPROGRAME_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_MYRADIO_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_MYRADIO_PROGRAME_NAME + " TEXT);";
        db.execSQL(radiosql);
    }

    private void createFavoriteSpeechTableQuery(SQLiteDatabase db) {
        String favoritespeechsql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_SPEECH
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_SPEECH_SPEECH_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_SPEECH_AUDIO_TEXT + " TEXT , "
                + TableStructure.TABLE_FAVORITE_SPEECH_AUDIO_URL + " TEXT , "
                + TableStructure.TABLE_FAVORITE_SPEECH_USERID + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_SPEECH_NICK_NAME + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_SPEECH_FACE + " TEXT , "
                + TableStructure.TABLE_FAVORITE_SPEECH_TIMES + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_SPEECH_DIGG + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_SPEECH_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_SPEECH_EGG + " INTEGER);";
        db.execSQL(favoritespeechsql);
    }

    private void createFavoriteVodTableQuery(SQLiteDatabase db) {
        String favoriteradiosql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_PRIVATE_RADIO
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_RADIO_USER_FM_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_RADIO_NAME + " TEXT , "
                + TableStructure.TABLE_FAVORITE_RADIO_ACL + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_RADIO_COVER_URL + " TEXT , "
                + TableStructure.TABLE_FAVORITE_RADIO_NUM + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_RADIO_TIMES + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_RADIO_USERID + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_RADIO_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_RADIO_NICK_NAME + " TEXT);";
        db.execSQL(favoriteradiosql);
    }

    private void createFavoriteProgramTableQuery(SQLiteDatabase db) {
        String favoriteradiosql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_PRIVATE_PROGRAM
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_PROGRAM_ID + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_USER_FM_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_FM_PROGRAM_NAME + " TEXT , "
                + TableStructure.TABLE_FAVORITE_PROGRAM_ACL + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_PROGRAM_COVER_URL + " TEXT , "
                + TableStructure.TABLE_FAVORITE_PROGRAM_NUM + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_PLAY_NUM + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_USERID + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_NAME + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_PROGRAM_NICK_NAME + " TEXT);";
        db.execSQL(favoriteradiosql);
    }

    private void createFavoriteAlbumTableQuery(SQLiteDatabase db) {
        String favoritealbumsql = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_ALBUM
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_ALBUM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_ALBUM_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_ALBUM_ALBUM_TYPE + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_ALBUM_TITLE + " TEXT , "
                + TableStructure.TABLE_FAVORITE_ALBUM_IS_END + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_ALBUM_COVER_URL + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_ALBUM_TIMES + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_ALBUM_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_ALBUM_VOD_NUM + " INTEGER);";
        db.execSQL(favoritealbumsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//			if (oldVersion < DB_VERSION && newVersion == DB_VERSION) {
//                dropMediaTableQuery(db);
//                createHomeTableQuery(db);
//                createFmTableQuery(db);
//            }
        upData(db, oldVersion, newVersion);
    }

    private void upData(SQLiteDatabase db, int oldVersion, int newVersion) {
        TTLog.e("lqsir", "upData newVersion " + newVersion + " - " + "oldVersion " + oldVersion);
        try {
            String sql1 = null;
            switch (oldVersion) {
                case 1:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_DOWNLOADTYPE + " INTEGER";
                    db.execSQL(sql1);
                case 2:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD + " ADD COLUMN " + TableStructure.TABLE_DOWNLOADTYPE + " INTEGER";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD + " ADD COLUMN " + TableStructure.TABLE_DOWNLOADTYPE_01 + " INTEGER";
                    db.execSQL(sql1);
                case 3:
                    createHomeTopListTableQuery(db);
                case 4:
                    createFavoriteProgramTableQuery(db);
                case 5:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_FAVORITE_RADIO + " ADD COLUMN " + TableStructure.TABLE_FAVORITE_MYRADIO_ORDER_ID + " INTEGER";
                    db.execSQL(sql1);
                case 6:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_STRING1 + " TEXT";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_COVER_URL + " TEXT";
                    db.execSQL(sql1);
                case 7:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_USERID + " INTEGER ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_PROGRAMTYPE + " INTEGER ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_PRIVATERADIOID + " INTEGER ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_FAVORITE_RADIO + " ADD COLUMN " + TableStructure.TABLE_FAVORITE_MYRADIO_FREQUENCY + " TEXT ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_FAVORITE_PRIVATE_RADIO + " ADD COLUMN " + TableStructure.TABLE_FAVORITE_RADIO_TYPE + " INTEGER ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_FAVORITE_PRIVATE_RADIO + " ADD COLUMN " + TableStructure.TABLE_FAVORITE_RADIO_PROGRAMNUM + " INTEGER ";
                    db.execSQL(sql1);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_PROGRAMID + " INTEGER ";
                    db.execSQL(sql1);
                    createCustomStyleNormal(db);
                    createHomeAdvertTableQuery(db);
                case 8:
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_PLAY_HISTORY + " ADD COLUMN " + TableStructure.PLAY_HISTORY_ITEM_TYPE + " INTEGER ";
                    db.execSQL(sql1);
                case 9:
                    createSubscribeTableQuery(db);
                case 10:
                    createSpecialTableQuery(db);
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_FAVORITE_AUDIO + " ADD COLUMN " + TableStructure.TABLE_FAVORITE_AUDIO_ALBUM_ID + " INTEGER ";
                    db.execSQL(sql1);
                case 11:
                    dropTable(db, TableStructure.TABLE_CATEGORY);
                    createCategoryTableQuery(db);
                case 12:
                    dropTable(db, TableStructure.TABLE_HOME);
                    dropTable(db, TableStructure.TABLE_CATEGORY);
                    dropTable(db, TableStructure.TABLE_HOME_FM);//更新到2.0.0，之前电台表数据表删除。

                    createHomeTableQuery(db);
                    createCategoryTableQuery(db);


                    createMusicRecommentTable(db, TableStructure.TABLE_HOME_FM);//创建电台页，精选电台数据表
                    createMusicRecommentTable(db, TableStructure.TABLE_MUSIC_RECOMMENT);//创建音乐电台，推荐数据表

                    db.execSQL("delete from " + TableStructure.TABLE_PLAY_HISTORY
                            + " where " + TableStructure.PLAY_HISTORY_ITEM_TYPE + " in(1002, 1003)");

                    createGlobalColor(db);//添加全局颜色值

                    //删除上一个版本电台类型缓存;
                    db.execSQL(getdeleteAllDateQuery(TableStructure.TABLE_DISCOVER));

                    //创建预约表
                    createMakeTable(db);

                    //创建全局收藏表
                    createFavoriteTable(db);

                    //正在直播相关表
                    createLiveAreaTableQuery(db);
                    createLiveCategoryTableQuery(db);

                    //创建全局订阅表
                    createSubscribeTable(db);

                    //下载表添加 新增时间字段（新增音频数据专辑放在第一位显示）
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_DOWNLOAD_ALBUM + " ADD COLUMN " + TableStructure.TABLE_DOWNLOAD_DATETIME + " TEXT ";
                    db.execSQL(sql1);
                case 13:
                    //预约表添加互动标识 （区分摇一摇互动界面添加，还是首页界面添加）
                    sql1 = "ALTER TABLE " + TableStructure.TABLE_MAKE_NAME + " ADD COLUMN " + TableStructure.TABLE_MAKE_INTERACTION_FLAG + " INTEGER ";
                    db.execSQL(sql1);

                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(SQLiteDatabase db, String table) {
        try {
            db.execSQL("drop table " + table + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGlobalColor(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_GLOBAL_COLOR + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_GLOBAL_COLOR_COLORFLAG + " INTEGER , "
                + TableStructure.TABLE_GLOBAL_COLOR_ID + " INTEGER , "
                + TableStructure.TABLE_GLOBAL_COLOR_type + " TEXT);";
        db.execSQL(query);
    }

    public void dropMediaTableQuery(SQLiteDatabase db) {
        String homeOtherQuery = "DROP TABLE " + TableStructure.TABLE_HOME + ";";
        String homeFmQuery = "DROP TABLE " + TableStructure.TABLE_HOME_FM + ";";
        db.execSQL(homeOtherQuery);
        db.execSQL(homeFmQuery);
    }

    public void createHomeTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_HOME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_HOEM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_URL + " TEXT , "
                + TableStructure.TABLE_HOME_CONTENT + " TEXT , "
                + TableStructure.TABLE_HOME_TYPE + " TEXT , "
                + TableStructure.TABLE_HOME_TIME + " TEXT , "
                + TableStructure.TABLE_HOME_ADDTIME + " LONG);";
        db.execSQL(query);
    }

    public void createHomeTopListTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_HOME_TOPLIST + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_HOEM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_URL + " TEXT , "
                + TableStructure.TABLE_HOME_CONTENT + " TEXT , "
                + TableStructure.TABLE_HOME_TYPE + " TEXT , "
                + TableStructure.TABLE_HOME_TIME + " TEXT);";
        db.execSQL(query);
    }

    public void createHomeAdvertTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_HOME_ADVERT + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_HOEM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_URL + " TEXT , "
                + TableStructure.TABLE_HOME_CONTENT + " TEXT , "
                + TableStructure.TABLE_HOME_TYPE + " TEXT , "
                + TableStructure.TABLE_HOME_TIME + " TEXT);";
        db.execSQL(query);
    }

    public void createFmTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_HOME_FM + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_HOME_FM_FM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_FREQUENCY + " TEXT , "
                + TableStructure.TABLE_HOME_FM_PLAY_TIMES + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_LIVE_URL + " TEXT , "
                + TableStructure.TABLE_HOME_FM_IS_FAVORITE + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_RECOMMENDATION + " TEXT , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_ST + " TEXT , "
                + TableStructure.TABLE_HOME_FM_ED + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_ST + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_ED + " TEXT);";
        db.execSQL(query);
    }

    /**
     * 创建音乐电台推荐数据缓存表
     *
     * @param db
     */
    public void createMusicRecommentTable(SQLiteDatabase db, String tableName) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + tableName + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_HOME_FM_FM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_FREQUENCY + " TEXT , "
                + TableStructure.TABLE_HOME_FM_PLAY_TIMES + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_IS_FAVORITE + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_RECOMMENDATION + " TEXT , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_PROGRAM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_ST + " TEXT , "
                + TableStructure.TABLE_HOME_FM_ANCHOR_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_ED + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_RADIO_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_CONTENT_CLASS_ID + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_IS_MUSIC + " INTEGER , "
                + TableStructure.TABLE_HOME_FM_NEXT_PROGRAM_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_ST + " TEXT , "
                + TableStructure.TABLE_HOME_FM_COVER_BASE_URL + " TEXT , "
                + TableStructure.TABLE_HOME_FM_CONTENT_CALSS_NAME + " TEXT , "
                + TableStructure.TABLE_HOME_FM_NEXT_ED + " TEXT);";
        db.execSQL(query);
    }

    public void createCategoryTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_CATEGORY + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_CATEGORY_ID + " INTEGER , "
                + TableStructure.TABLE_CATEGORY_NAME + " TEXT , "
                + TableStructure.TABLE_CATEGORY_ORDERID + " INTEGER, "
                + TableStructure.TABLE_CATEGORY_RED + " INTEGER, "
                + TableStructure.TABLE_CATEGORY_SUB + " TEXT , "
                + TableStructure.TABLE_CATEGORY_SELECTED + " INTEGER);";
        db.execSQL(query);
    }

    public void createFATableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE_AUDIO + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_AUDIO_VOI_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_AUDIO_NAME + " TEXT , "
                + TableStructure.TABLE_FAVORITE_AUDIO_COVER_URL + " TEXT , "
                + TableStructure.TABLE_FAVORITE_AUDIO_DURATION + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_AUDIO_TIMES + " INTEGER ,"
                + TableStructure.TABLE_FAVORITE_AUDIO_PLAY_URL + " TEXT ,"
                + TableStructure.TABLE_FAVORITE_AUDIO_USER_ID + " INTEGER DEFAULT 0 ,"
                + TableStructure.TABLE_FAVORITE_AUDIO_TITLE + " TEXT);";
        db.execSQL(query);
    }

    public void createCustomStyleNormal(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_CUSTOM_STYLE_NORMAL + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_CUSTOM_STYLE_NORMAL_ID + " INTEGER , "
                + TableStructure.TABLE_CUSTOM_STYLE_NORMAL_NAME + " TEXT , "
                + TableStructure.TABLE_CUSTOM_STYLE_ORDER_ID + " INTEGER , "
                + TableStructure.TABLE_CUSTOM_STYLE_SELECTED + " INTEGER , "
                + TableStructure.TABLE_CUSTOM_STYLE_USER_ID + " INTEGER);";
        db.execSQL(query);
    }

    /**
     * 创建预约表结构
     * @param db
     */
    public void createMakeTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_MAKE_NAME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_MAKE_NAME_PROGRAM_ID + " INTEGER , "
                + TableStructure.TABLE_MAKE_NAME_FM_ID + " INTEGER , "
                + TableStructure.TABLE_MAKE_NAME_NAME + " TEXT , "
                + TableStructure.TABLE_MAKE_FREQUENCY + " TEXT , "
                + TableStructure.TABLE_MAKE_NAME_COVERURL + " TEXT , "
                + TableStructure.TABLE_MAKE_NAME_PROGRAMANEM + " TEXT , "
                + TableStructure.TABLE_MAKE_NAME_ET + " TEXT , "
                + TableStructure.TABLE_MAKE_NAME_ST + " TEXT , "
                + TableStructure.TABLE_MAKE_START_DATE + " TEXT , "
                + TableStructure.TABLE_MAKE_END_DATE + " TEXT , "
                + TableStructure.TABLE_MAKE_TYPE + " INTEGER );";
        db.execSQL(query);
    }

    /**
     * 创建收藏表(包含所有收藏类型)
     * @param db
     */
    public void createFavoriteTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_FAVORITE + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_FAVORITE_TYPE + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_ID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_SUBID + " INTEGER , "
                + TableStructure.TABLE_FAVORITE_STATUS + " INTEGER , "
                + TableStructure.TABLE_CUSTOM_STYLE_USER_ID + " INTEGER);";
        db.execSQL(query);

        query = "create unique index IK_type_id_subid on table_favorite "
                + "(table_favorite_id, table_favorite_type, table_favorite_subid);";
        db.execSQL(query);
    }

    /**
     * 创建订阅表(订阅专辑无类型)
     * @param db
     */
    public void createSubscribeTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_SUBSCRIBE_ALL + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_SUBSCRIBE_ALL_ALBUM_ID + " INTEGER , "
                + TableStructure.TABLE_SUBSCRIBE_ALL_STATUS + " INTEGER , "
                + TableStructure.TABLE_SUBSCRIBE_ALL_USER_ID + " INTEGER);";
        db.execSQL(query);
        query = "create unique index IK_albumid on table_subscribe "
                + "(subscribe_album_id);";
        db.execSQL(query);
    }

    public void createPlayTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_PLAY_HISTORY
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.PLAY_HISTORY_ALBUM_ID + " INTEGER , "
                + TableStructure.PLAY_HISTORY_ALBUM_TYPE + " INTEGER , "
                + TableStructure.PLAY_HISTORY_PROGRAM_ID + " INTEGER , "
                + TableStructure.PLAY_HISTORY_ALBUM_NAME + " TEXT , "
                + TableStructure.PLAY_HISTORY_VOD_ID + " INTEGER , "
                + TableStructure.PLAY_HISTORY_VOD_NAME + " TEXT , "
                + TableStructure.PLAY_HISTORY_CREATE_NAME + " TEXT ,"
                + TableStructure.PLAY_HISTORY_ALBUM_URL + " TEXT ,"
                + TableStructure.PLAY_HISTORY_TIME + " TEXT, "
                + TableStructure.PLAY_HISTORY_FM_ID + " INTEGER, "
                + TableStructure.PLAY_HISTORY_FREQUENCY + " TEXT, "
                + TableStructure.PLAY_HISTORY_FM_PROGRAM_NAME + " TEXT, "
                + TableStructure.PLAY_HISTORY_FM_PROGRAM_NUM + " INTEGER ,"
                + TableStructure.PLAY_HISTORY_UPDATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + "user_id INTEGER DEFAULT 0);";
        db.execSQL(query);
    }

    private String getdeleteAllDateQuery(String tableName) {
        return String.format(Locale.US, "delete from %s;", tableName);
    }


    /**
     * 正在直播 地区表
     * @param db
     */
    public void createLiveAreaTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_LIVE_AREA + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_LIVE_AREA_ID + " INTEGER , "
                + TableStructure.TABLE_LIVE_AREA_NAME + " TEXT );";
        db.execSQL(query);
    }

    /**
     * 正在直播 频道表
     * @param db
     */
    public void createLiveCategoryTableQuery(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + TableStructure.TABLE_LIVE_CATEGORY + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableStructure.TABLE_LIVE_CATEGORY_ID + " INTEGER , "
                + TableStructure.TABLE_LIVE_CATEGORY_NAME + " TEXT , "
                + TableStructure.TABLE_LIVE_CATEGORY_ORDERID + " INTEGER, "
                + TableStructure.TABLE_LIVE_CATEGORY_TYPE + " INTEGER );";
        db.execSQL(query);
    }
}