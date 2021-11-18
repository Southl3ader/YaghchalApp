package com.example.yaghjava.dataBase;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;
import java.util.List;


public class groceriesDA {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] cls = {SqliteDB.NAME , SqliteDB.AMOUNT , SqliteDB.BUY_DATE , SqliteDB.EXPIRE_DATE , SqliteDB.COMPANY, SqliteDB.TYPE};

    public groceriesDA(Activity context) {
        openHelper = new SqliteDB(context);
    }

    public void openDB(){
        database = openHelper.getWritableDatabase();
    }

    public void closeDB(){
        database.close();
    }


    //add grocery to table
    public void insert(String name, String type){
       String addQuery = "insert into "+ SqliteDB.TABLE_GROCERIES + " (" + SqliteDB.NAME + " , " + SqliteDB.AMOUNT +" , " + SqliteDB.BUY_DATE + " , " + SqliteDB.EXPIRE_DATE + " , " + SqliteDB.COMPANY + " , " + SqliteDB.TYPE + ") "
               + "select " + "'" + name + "'" + " , " + 0 + " , " +  "'" + null + "'" + " , " +  "'" + null +  "'" + " , " + "'" + null + "'" + " , " + "'" + type + "'"
               + "where not exists ( select 1 from " + SqliteDB.TABLE_GROCERIES + " where " + SqliteDB.NAME + " = " + "'" + name + "'" + " and " + SqliteDB.TYPE + " = " + "'" + type + "'" + ")";

        database.execSQL(addQuery);
    }

    //update Vegetables details
    public void updateVegAttributes(String name , int amount, String BDate, String ExDate, String company, String type){
        String upQuery = "update " + SqliteDB.TABLE_GROCERIES
                + " set " + SqliteDB.AMOUNT + " = " +  amount + " , "
                + SqliteDB.BUY_DATE + " = " + "'" + BDate + "'" + " , "
                + SqliteDB.EXPIRE_DATE + " = " + "'" + ExDate + "'" + " , "
                + SqliteDB.COMPANY + " = " + "'" + company + "'"
                + " where " + SqliteDB.NAME + " = " + "'" + name + "'" + " and " + SqliteDB.TYPE + " = " + "'" + type + "'";

        database.execSQL(upQuery);
    }

    //Set as default
    public void defaultGro(String name){
        String upQuery = "update " + SqliteDB.TABLE_GROCERIES
                + " set " + SqliteDB.AMOUNT + " = " + 0 + " , "
                + SqliteDB.BUY_DATE + " = " + "'" + null + "'" + " , "
                + SqliteDB.EXPIRE_DATE + " = " + "'" + null + "'"+ " , "
                + SqliteDB.COMPANY + " = " + "'" + null + "'"
                + " where " + SqliteDB.NAME + " = " + "'" + name + "'";

        database.execSQL(upQuery);
    }

   //get all vegetables
    public List<groceriesModel> getAllVeg(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.TYPE + " = " + "'" + "Veg" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }

    //get all Dairies
    public List<groceriesModel> getAllDairies(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.TYPE + " = " + "'" + "Dairy" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }

    //get all Proteins
    public List<groceriesModel> getAllProteins(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.TYPE + " = " + "'" + "Protein" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }

    //get all Cereals
    public List<groceriesModel> getAllCereals(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.TYPE + " = " + "'" + "Cereal" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }

    //search
    public List<groceriesModel> search(String input){
        if (input == null)
            input = "";
       List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.NAME + " like '" + input + "%'";

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }
//    public groceriesModel search(String input){
//        if (input == null)
//            input = "";
//        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
//                + " where " + SqliteDB.NAME + " like '" + input + "%'";
//
//        Cursor cursor = database.rawQuery(getAllVegQuery, null);
//        if (cursor.moveToFirst()) {
//            String name = cursor.getString(0);
//            int amount = cursor.getInt(1);
//            String buy = cursor.getString(2);
//            String expire = cursor.getString(3);
//            String company = cursor.getString(4);
//            String type = cursor.getString(5);
//
//            return new groceriesModel(name, amount, buy, expire, company, type);
//
//        }else return null;
//    }

    //Get all groceries
    public List<groceriesModel> getAllGro(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllQuery = "select * from " + SqliteDB.TABLE_GROCERIES;
        Cursor cursor = database.rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                int amount = cursor.getInt(1);
                String buy = cursor.getString(2);
                String expire = cursor.getString(3);
                String company = cursor.getString(4);
                String type = cursor.getString(5);

                models.add(new groceriesModel(name , amount , buy , expire , company , type));
            }while (cursor.moveToNext());
        }
        return models;
    }
}

