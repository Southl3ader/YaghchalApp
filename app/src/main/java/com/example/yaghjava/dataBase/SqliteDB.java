package com.example.yaghjava.dataBase;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class SqliteDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "yaghchal.db";
    public static final int DB_Version = 9;

    //g_table
    public static final String TABLE_GROCERIES = "groceries";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String BUY_DATE = "buy";
    public static final String EXPIRE_DATE = "expire";
    public static final String COMPANY = "company";
    public static final String TYPE = "type";
    public static final String IMAGE = "image";

    public static final String TABLE_FRIDGE= "fridge";
    public static final String F_NAME = "f_name";
    public static final String F_AMOUNT = "f_amount";
    public static final String F_BUY_DATE = "f_buy";
    public static final String F_EXPIRE_DATE = "f_expire";
    public static final String F_COMPANY = "f_company";
    public static final String F_TYPE = "f_type";
    public static final String F_IMAGE = "f_image";




    public SqliteDB(Activity context) {
        super(context, DB_NAME, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //g_table
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_GROCERIES + " ( " +
                NAME + " TEXT NOT NULL , " +
                AMOUNT + " INTEGER DEFAULT NULL , " +
                BUY_DATE + " TEXT NOT NULL , " +
                EXPIRE_DATE + " TEXT , " +
                COMPANY + " TEXT ," +
                TYPE + " TEXT ," +
                IMAGE + " BLOB )";

        sqLiteDatabase.execSQL(query);

        //f_table
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_FRIDGE + " ( " +
                F_NAME + " TEXT NOT NULL , " +
                F_AMOUNT + " INTEGER DEFAULT NULL , " +
                F_BUY_DATE + " TEXT NOT NULL , " +
                F_EXPIRE_DATE + " TEXT , " +
                F_COMPANY + " TEXT ," +
                F_TYPE + " TEXT ," +
                F_IMAGE + " BLOB )";

        sqLiteDatabase.execSQL(query1);



    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_GROCERIES;
        sqLiteDatabase.execSQL(query);
        String query1 = "DROP TABLE IF EXISTS " + TABLE_FRIDGE;
        sqLiteDatabase.execSQL(query1);

        onCreate(sqLiteDatabase);
    }
}

