package com.vanniktech.emoji.sticker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vanniktech.emoji.sticker.struct.StructAllSticker;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;
import com.vanniktech.emoji.sticker.struct.StructStickerGroup;
import com.vanniktech.emoji.sticker.struct.StructStickerItemGroup;

import java.util.ArrayList;

public class StickerDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "sticker_db";
    private final static int DB_VERSION = 1;

    private static final String STICKER_TABLE_CATEGORY = "sticker_table_category";
    private static final String ID = "id";
    private static final String ID_ST = "id_sticker";
    private static final String COUNT = "count";
    private static final String SORT = "sort";
    private static final String NAME = "name";
    private static final String TOKEN = "token";
    private static final String URL = "url";
    private static final String CREATE_AT = "createdAt";
    private static final String PRICE = "price";
    private static final String IS_VIP = "isVip";
    private static final String CREATE_BY = "createdBy";
    private static final String GROUP_ID = "groupId";

    private static final String STICKER_TABLE = "sticker_table";
    private static final String ID_STICKER = "id_sticker";
    private static final String URL_STICKER = "url";

    private static final String STICKER_TABLE_RECENTLY = "sticker_table_recently";
    private static final String TIME_USAGE = "time";


    public StickerDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE_CATEGORY + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                CREATE_AT + " INTEGER," +
                ID_ST + " TEXT," +
                NAME + " TEXT, " +
                TOKEN + " TEXT, " +
                URL + " TEXT, " +
                PRICE + " INTEGER," +
                IS_VIP + " INTEGER," +
                SORT + " INTEGER," +
                CREATE_BY + " TEXT)" +
                ";");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                CREATE_BY + " TEXT, " +
                ID_ST + " TEXT, " +
                NAME + " TEXT, " +
                TOKEN + " TEXT, " +
                URL + " TEXT, " +
                SORT + " INTEGER," +
                GROUP_ID + " TEXT)" +
                ";");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE_RECENTLY + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_ST + " TEXT, " +
                TOKEN + " TEXT, " +
                URL_STICKER + " TEXT, " +
                TIME_USAGE + " INTEGER," +
                GROUP_ID + " TEXT)" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + STICKER_TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + STICKER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STICKER_TABLE_RECENTLY);
        onCreate(db);
    }


    public boolean insertCategorySticker(String createAt, String id_st, String name, String token, String url, int price, boolean isVip, int sort, String createBy) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CREATE_AT, createAt);
        contentValues.put(ID_ST, id_st);
        contentValues.put(NAME, name);
        contentValues.put(TOKEN, token);
        contentValues.put(URL, url);
        contentValues.put(PRICE, price);
        contentValues.put(IS_VIP, isVip);
        contentValues.put(SORT, sort);
        contentValues.put(CREATE_BY, createBy);

        long insert = db.insert(STICKER_TABLE_CATEGORY, null, contentValues);

        Log.i("CCCCC", "insertCategorySticker: " + insert);
        return insert != -1;
    }

    public boolean insertSticker(String createAt, String id_st, String name, String token, String url, int sort, String groupId) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CREATE_BY, createAt);
        contentValues.put(ID_ST, id_st);
        contentValues.put(NAME, name);
        contentValues.put(TOKEN, token);
        contentValues.put(URL, url);
        contentValues.put(SORT, sort);
        contentValues.put(GROUP_ID, groupId);
        Log.i("CCCCC", "3 updateDB: " + id_st);

        long insert = db.insert(STICKER_TABLE, null, contentValues);

        Log.i("CCCCC", "insertSticker: " + insert);

        return insert != -1;
    }

    public boolean insertOrUpdateRecentlySticker(String idSticker, String idCategory, String token, String url, Long time) {

        if (checkRecentlySticker(idSticker)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TIME_USAGE, time);

            long insert = db.update(STICKER_TABLE_RECENTLY, contentValues, ID_ST + "=" + idSticker, null);

            return insert != -1;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(GROUP_ID, idCategory);
            contentValues.put(ID_ST, idSticker);
            contentValues.put(TOKEN, token);
            contentValues.put(URL_STICKER, url);
            contentValues.put(TIME_USAGE, time);

            long insert = db.insert(STICKER_TABLE_RECENTLY, null, contentValues);

            return insert != -1;
        }

    }

    public boolean checkRecentlySticker(String idSticker) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_RECENTLY + " where " + ID_ST + " = " + idSticker;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean checkIsDataAlreadyInDBorNot(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_CATEGORY + " where " + ID_ST + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public ArrayList<StructAllSticker> getAllCategory() {
        ArrayList<StructAllSticker> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + STICKER_TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                while (cursor.moveToNext()) {
                    StructAllSticker item = new StructAllSticker();


                    item.setId(cursor.getString(cursor.getColumnIndex(ID_ST)));
                    item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                    item.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
                    userList.add(item);

                    item.setStructItemStickers(getAllSticker(item.getId()));

                }
            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }
        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }
        return userList;
    }


    public ArrayList<StructItemSticker> getAllSticker(String idCategory) {

        ArrayList<StructItemSticker> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + STICKER_TABLE + " WHERE " + GROUP_ID + "=" + idCategory;

        Log.i("CCCCC", "selectQuery: " + selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {

                    do {
                        StructItemSticker item = new StructItemSticker();
                        item.setCreatedBy(cursor.getInt(cursor.getColumnIndex(CREATE_BY)));
                        item.setId(cursor.getString(cursor.getColumnIndex(ID_ST)));
                        item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                        item.setToken(cursor.getString(cursor.getColumnIndex(TOKEN)));
                        item.setSort(cursor.getInt(cursor.getColumnIndex(SORT)));
                        item.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
                        list.add(item);

                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }
        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public ArrayList<StructRecentSticker> getRecentlySticker() {

        ArrayList<StructRecentSticker> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + STICKER_TABLE_RECENTLY + " ORDER BY " + TIME_USAGE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {

                        String url = cursor.getString(cursor.getColumnIndex(URL_STICKER));
                        String idSticker = cursor.getString(cursor.getColumnIndex(ID_ST));
                        String idCategory = cursor.getString(cursor.getColumnIndex(GROUP_ID));
                        String token = cursor.getString(cursor.getColumnIndex(TOKEN));
                        list.add(new StructRecentSticker("", idCategory, idSticker, token, url));
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }
        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public boolean favoriteDeleteGroup(String idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(STICKER_TABLE_CATEGORY, " WHERE " + GROUP_ID + "=" + idCategory, null) > 0;
    }

    public boolean favoriteDeleteSticker(String idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(STICKER_TABLE, " WHERE " + GROUP_ID + "=" + idCategory, null) > 0;
    }

}