package com.example.yaghjava.dataBase;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class SqliteDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "yaghchal.db";
    public static final int DB_Version = 4;

    //table
    public static final String TABLE_GROCERIES = "groceries";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String BUY_DATE = "buy";
    public static final String EXPIRE_DATE = "expire";
    public static final String COMPANY = "company";
    public static final String TYPE = "type";



    public SqliteDB(Activity context) {
        super(context, DB_NAME, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_GROCERIES + " ( " +
                NAME + " TEXT NOT NULL , " +
                AMOUNT + " INTEGER DEFAULT NULL , " +
                BUY_DATE + " TEXT NOT NULL , " +
                EXPIRE_DATE + " TEXT , " +
                COMPANY + " TEXT ," +
                TYPE + " TEXT )";

        sqLiteDatabase.execSQL(query);
//        insert(sqLiteDatabase,"Tomato","Veg");

    }
//    public boolean insert(SQLiteDatabase sqLiteDatabase, String name, String type){
//        try {
//            String addQuery = "insert into " + SqliteDB.TABLE_GROCERIES + " (" + SqliteDB.NAME + " , " + SqliteDB.AMOUNT +" , " + SqliteDB.BUY_DATE + " , " + SqliteDB.EXPIRE_DATE + " , " + SqliteDB.COMPANY + " , " + SqliteDB.TYPE + ")" +
//                    "values (" + "'" + name + "'" + " , " + 0 + " , " + 0 + " , " + 0 + " , " + "'" + null + "'" + " , " + "'" + type + "'" + ") ";
//
//            sqLiteDatabase.execSQL(addQuery);
//            return true;
//
//        }catch (Exception e){
//            return false;
//        }
//
//
//    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_GROCERIES;
        sqLiteDatabase.execSQL(query);

        onCreate(sqLiteDatabase);
    }
}

