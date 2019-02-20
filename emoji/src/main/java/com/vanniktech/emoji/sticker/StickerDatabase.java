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
    private static final String SORT = "sort";
    private static final String NAME = "name";
    private static final String TOKEN = "token";
    private static final String URI = "URI";
    private static final String GROUP_ID = "groupId";
    private static final String REF_ID = "ref_id";
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
                REF_ID + " INTEGER, " +
                NAME + " TEXT, " +
                TOKEN + " TEXT, " +
                URI + " TEXT, " +
                SORT + " INTEGER," +
                GROUP_ID + " TEXT," +
                TIME_USAGE + " INTEGER)" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STICKER_TABLE_RECENTLY);
        onCreate(db);
    }

    public boolean insertOrUpdateRecentlySticker(String id_st, long ref_id, String name, String token, String uri, int sort, String groupId, Long time) {

        if (checkRecentlySticker(id_st)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TIME_USAGE, time);

            long insert = db.update(STICKER_TABLE_RECENTLY, contentValues, ID_ST + "= '" + id_st  +"'", null);

            return insert != -1;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_ST, id_st);
            contentValues.put(REF_ID, ref_id);
            contentValues.put(NAME, name);
            contentValues.put(TOKEN, token);
            contentValues.put(URI, uri);
            contentValues.put(SORT, sort);
            contentValues.put(GROUP_ID, groupId);
            contentValues.put(TIME_USAGE, time);

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
                        item.setRefId(cursor.getLong(cursor.getColumnIndex(REF_ID)));
                        item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                        item.setToken(cursor.getString(cursor.getColumnIndex(TOKEN)));
                        item.setUri(cursor.getString(cursor.getColumnIndex(URI)));
                        item.setSort(cursor.getInt(cursor.getColumnIndex(SORT)));
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
