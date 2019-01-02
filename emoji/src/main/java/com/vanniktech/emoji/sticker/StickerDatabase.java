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
import java.util.ArrayList;

public class StickerDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "sticker_db";
    private final static int DB_VERSION = 1;

    private static final String STICKER_TABLE_CATEGORY = "sticker_table_category";
    private static final String ID = "id";
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
                ID_CATEGORY + " TEXT)" +
                ";");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_CATEGORY + " TEXT, " +
                ID_STICKER + " TEXT, " +
                URL_STICKER + " TEXT)" +
                ";");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + STICKER_TABLE_RECENTLY + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                ID_STICKER + " TEXT, " +
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

    public boolean insertCategorySticker(String name, String id_category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_CATEGORY, name);
        contentValues.put(ID_CATEGORY, id_category);

        long insert = db.insert(STICKER_TABLE_CATEGORY, null, contentValues);

        return insert != -1;
    }

    public boolean insertSticker(String id_category, String id_sticker, String url) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_CATEGORY, id_category);
        contentValues.put(ID_STICKER, id_sticker);
        contentValues.put(URL_STICKER, url);


        long insert = db.insert(STICKER_TABLE, null, contentValues);

        return insert != -1;
    }

    public  boolean checkIsDataAlreadyInDBorNot(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + STICKER_TABLE_CATEGORY + " where " + ID_CATEGORY + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public boolean updateCategorySticker(Integer id, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_USAGE, time);
        db.update(STICKER_TABLE_RECENTLY, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }
//
//    public Integer deleteCategorySticker(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(STICKER_TABLE_CATEGORY,
//                "id = ? ",
//                new String[]{Integer.toString(id)});
//    }
//
    public ArrayList<String> getAllCategoryStickers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + STICKER_TABLE_CATEGORY, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(STICKER_TABLE_CATEGORY)));
            res.moveToNext();
        }
        return array_list;
    }
}
