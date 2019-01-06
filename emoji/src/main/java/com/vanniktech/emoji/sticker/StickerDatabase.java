package com.vanniktech.emoji.sticker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URL;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;

public class StickerDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "sticker_db";
    private final static int DB_VERSION = 1;

    private static final String STICKER_TABLE_CATEGORY = "sticker_table_category";
    private static final String ID = "id";
    private static final String COUNT = "count";
    private static final String ID_CATEGORY = "id_category";
    private static final String NAME_CATEGORY = "name";

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
                NAME_CATEGORY + " TEXT, " +
                ID_CATEGORY + " INTEGER," +
                COUNT + " INTEGER," +
                URL_STICKER + " TEXT)" +
                ";");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_CATEGORY + " INTEGER, " +
                ID_STICKER + " INTEGER, " +
                URL_STICKER + " TEXT)" +
                ";");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE_RECENTLY + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_CATEGORY + " INTEGER, " +
                ID_STICKER + " INTEGER, " +
                URL_STICKER + " TEXT, " +
                TIME_USAGE + " INTEGER)" +
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

    public boolean insertCategorySticker(String nameCategory, int idCategory, int count, String url) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_CATEGORY, nameCategory);
        contentValues.put(ID_CATEGORY, idCategory);
        contentValues.put(COUNT, count);
        contentValues.put(URL_STICKER, url);

        long insert = db.insert(STICKER_TABLE_CATEGORY, null, contentValues);

        return insert != -1;
    }

    public boolean insertSticker(int id_category, int id_sticker, String url) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_CATEGORY, id_category);
        contentValues.put(ID_STICKER, id_sticker);
        contentValues.put(URL_STICKER, url);

        long insert = db.insert(STICKER_TABLE, null, contentValues);

        return insert != -1;
    }

    public boolean insertOrUpdateRecentlySticker(int idSticker, int idCategory, String url, Long time) {

        if (checkRecentlySticker(idSticker)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TIME_USAGE, time);

            long insert = db.update(STICKER_TABLE_RECENTLY, contentValues, ID_STICKER + "=" + idSticker, null);

            return insert != -1;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_CATEGORY, idCategory);
            contentValues.put(ID_STICKER, idSticker);
            contentValues.put(URL_STICKER, url);
            contentValues.put(TIME_USAGE, time);

            long insert = db.insert(STICKER_TABLE_RECENTLY, null, contentValues);

            return insert != -1;
        }

    }

    public boolean checkRecentlySticker(int idSticker) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_RECENTLY + " where " + ID_STICKER + " = " + idSticker;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean checkIsDataAlreadyInDBorNot(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_CATEGORY + " where " + ID_CATEGORY + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public ArrayList<StructCategory> getAllCategory() {
        ArrayList<StructCategory> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + STICKER_TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                while (cursor.moveToNext()) {
                    int idCategory = cursor.getInt(cursor.getColumnIndex(ID_CATEGORY));
                    String name = cursor.getString(cursor.getColumnIndex(NAME_CATEGORY));
                    int count = cursor.getInt(cursor.getColumnIndex(COUNT));
                    String url = cursor.getString(cursor.getColumnIndex(URL_STICKER));
                    userList.add(new StructCategory(idCategory, name, count, url));
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

    public ArrayList<StructEachSticker> getAllSticker(int idCategory) {

        ArrayList<StructEachSticker> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + STICKER_TABLE + " WHERE " + ID_CATEGORY + "=" + idCategory;

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {

                    do {
                        String url = cursor.getString(cursor.getColumnIndex(URL_STICKER));
                        int idSticker = cursor.getInt(cursor.getColumnIndex(ID_STICKER));
                        list.add(new StructEachSticker(idSticker, url));

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
                        int idSticker = cursor.getInt(cursor.getColumnIndex(ID_STICKER));
                        int idCategory = cursor.getInt(cursor.getColumnIndex(ID_CATEGORY));
                        list.add(new StructRecentSticker("", url, idCategory, idSticker));
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


}
