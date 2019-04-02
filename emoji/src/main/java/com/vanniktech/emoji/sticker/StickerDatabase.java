package com.vanniktech.emoji.sticker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.ArrayList;

public class StickerDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "sticker_db";
    private final static int DB_VERSION = 1;
    private static final String ID = "id";
    private static final String ID_ST = "id_sticker";
    private static final String NAME = "name";
    private static final String UNI_CODE = "uni_code";
    private static final String URI = "URI";
    private static final String GROUP_ID = "groupId";
    private static final String STICKER_TABLE_RECENTLY = "sticker_table_recently";
    private static final String TIME_USAGE = "time";


    public StickerDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE_RECENTLY + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_ST + " TEXT, " +
                NAME + " TEXT, " +
                UNI_CODE + " TEXT, " +
                URI + " TEXT, " +
                GROUP_ID + " TEXT," +
                TIME_USAGE + " INTEGER)" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STICKER_TABLE_RECENTLY);
        onCreate(db);
    }

    public boolean insertOrUpdateRecentlySticker(StructItemSticker item) {

        if (checkRecentlySticker(item.getId())) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TIME_USAGE, System.currentTimeMillis());

            long insert = db.update(STICKER_TABLE_RECENTLY, contentValues, ID_ST + "= '" + item.getId()  +"'", null);

            return insert != -1;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_ST, item.getId());
            contentValues.put(NAME, item.getName());
            contentValues.put(UNI_CODE, item.getUniCode());
            contentValues.put(URI, item.getImageUrl());
            contentValues.put(GROUP_ID, item.getGroupId());
            contentValues.put(TIME_USAGE, System.currentTimeMillis());

            long insert = db.insert(STICKER_TABLE_RECENTLY, null, contentValues);

            return insert != -1;
        }

    }

    public boolean checkRecentlySticker(String idSticker) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_RECENTLY + " where " + ID_ST + " = '" + idSticker + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public boolean removeRecentSticker(String idSticker){

        if (checkRecentlySticker(idSticker)) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(STICKER_TABLE_RECENTLY, ID_ST + " = '" + idSticker + "'", null) > 0;

        }
        return false;
    }


    public ArrayList<StructItemSticker> getRecentlySticker() {

        ArrayList<StructItemSticker> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + STICKER_TABLE_RECENTLY + " ORDER BY " + TIME_USAGE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {

                        StructItemSticker item = new StructItemSticker();
                        item.setId(cursor.getString(cursor.getColumnIndex(ID_ST)));
                        item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                        item.setName(cursor.getString(cursor.getColumnIndex(UNI_CODE)));
                        item.setImageUrl(cursor.getString(cursor.getColumnIndex(URI)));
                        item.setGroupId(cursor.getString(cursor.getColumnIndex(GROUP_ID)));
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
}
