package com.example.spalvos;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{

    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spalvosDB.db";
    public static final String TABLE_NAME = "Rezultatai";
    public static final String COLUMN_ID = "RezID";
    public static final String COLUMN_NAME = "RezName";
    public static final String COLUMN_REZ = "Rez";

    //initialize the database
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_REZ + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}


    public ArrayList<rezultatas> loadHandler() {
        int i = 1;
        ArrayList<rezultatas> sarasas = new ArrayList<>();
        String query = "Select * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_REZ + " DESC LIMIT 15";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    rezultatas score = new rezultatas();
                    score.setId(i++);
                    score.setVardas(cursor.getString(1));
                    score.setRezultatas(cursor.getInt(2));
                    sarasas.add(score);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return sarasas;
    }

    public void addHandler(rezultatas rez) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(COLUMN_ID, rez.getId());
        values.put(COLUMN_NAME, rez.getVardas());
        values.put(COLUMN_REZ, rez.getRezultatas());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
