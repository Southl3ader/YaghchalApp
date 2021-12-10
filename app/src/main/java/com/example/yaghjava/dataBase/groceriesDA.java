package com.example.yaghjava.dataBase;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;
import java.util.List;


public class groceriesDA {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] cls = {SqliteDB.NAME , SqliteDB.AMOUNT , SqliteDB.BUY_DATE , SqliteDB.EXPIRE_DATE , SqliteDB.COMPANY, SqliteDB.TYPE, SqliteDB.IMAGE};

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
    public void insert(String name, String type, byte[] image){
       String addQuery = "insert into "+ SqliteDB.TABLE_GROCERIES + " (" + SqliteDB.NAME + " , " + SqliteDB.AMOUNT +" , " + SqliteDB.BUY_DATE + " , " + SqliteDB.EXPIRE_DATE + " , " + SqliteDB.COMPANY + " , " + SqliteDB.TYPE + ") "
               + "select " + "'" + name + "'" + " , " + 0 + " , " +  "'" + null + "'" + " , " +  "'" + null +  "'" + " , " + "'" + null + "'" + " , " + "'" + type + "'"
               + "where not exists ( select 1 from " + SqliteDB.TABLE_GROCERIES + " where " + SqliteDB.NAME + " = " + "'" + name + "'" + " and " + SqliteDB.TYPE + " = " + "'" + type + "'" + ")";

        database.execSQL(addQuery);
        ContentValues cv = new ContentValues();
//        cv.put(SqliteDB.NAME, name);
//        cv.put(SqliteDB.AMOUNT,0);
//        cv.put(SqliteDB.BUY_DATE,"");
//        cv.put(SqliteDB.EXPIRE_DATE,"");
//        cv.put(SqliteDB.COMPANY,"");
//        cv.put(SqliteDB.TYPE,type);
        cv.put(SqliteDB.IMAGE,image);

        database.update(SqliteDB.TABLE_GROCERIES,cv,SqliteDB.NAME + " = '" + name + "'",null);
    }

    //add grocery to fridge
    public void addToFridge(String input){
        String query = "update " + SqliteDB.TABLE_GROCERIES
                + " set " + SqliteDB.AMOUNT + " = " + 1
                + " where " + SqliteDB.NAME + " = " + "'" + input + "'";

        database.execSQL(query);
    }
    public void addToFridge2(String input,int amount, String buy, String ex, String company){
        String query ="insert into " + SqliteDB.TABLE_FRIDGE + " ( " + SqliteDB.F_NAME + " , " + SqliteDB.F_TYPE + " , " + SqliteDB.F_IMAGE + " )"
                + " select " + SqliteDB.NAME + " , " + SqliteDB.TYPE + " , " + SqliteDB.IMAGE + " from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.NAME + " = " + "'" + input + "'";

        database.execSQL(query);
        String query1 = "update " + SqliteDB.TABLE_FRIDGE
                + " set " + SqliteDB.F_AMOUNT + " = " +  amount + " , "
                + SqliteDB.F_BUY_DATE + " = " + "'" + buy + "'" + " , "
                + SqliteDB.F_EXPIRE_DATE + " = " + "'" + ex + "'" + " , "
                + SqliteDB.F_COMPANY + " = " + "'" + company + "'"
//                + " where " + SqliteDB.F_NAME + " = " + "'" + input + "'"
                + " where " + SqliteDB.F_ID + " in ( "
                + " select " + SqliteDB.F_ID + " from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_NAME + " = " + "'" + input + "'"
                + " order by " + SqliteDB.F_ID + " desc limit 1)";
//                + " ORDER BY " + SqliteDB.F_ID + " DESC"
//                + " LIMIT 1";
        database.execSQL(query1);
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

    //Remove from fridge
    public void changeAmount(String name, int amount, String BDate, String ExDate,String company, String type){
        String query ="update " + SqliteDB.TABLE_FRIDGE
                +" set " + SqliteDB.F_AMOUNT + " = " + amount + " , "
                + " where " + SqliteDB.F_NAME + " = " + "'" + name + "'" + " and " + SqliteDB.F_BUY_DATE + " = " + "'" + BDate + "'" + " and " + SqliteDB.F_EXPIRE_DATE + " = " + "'" + ExDate + "'" + " and " + SqliteDB.F_COMPANY + " = " + "'" + company + "'" + " and " + SqliteDB.F_TYPE + " = " + "'" + type + "'";

        database.execSQL(query);
    }

    //Set as default
//    public void defaultGro(String name){
//        String upQuery = "update " + SqliteDB.TABLE_GROCERIES
//                + " set " + SqliteDB.AMOUNT + " = " + 0 + " , "
//                + SqliteDB.BUY_DATE + " = " + "'" + null + "'" + " , "
//                + SqliteDB.EXPIRE_DATE + " = " + "'" + null + "'"+ " , "
//                + SqliteDB.COMPANY + " = " + "'" + null + "'"
//                + " where " + SqliteDB.NAME + " = " + "'" + name + "'";
//
//        database.execSQL(upQuery);
//    }

   //get all vegetables
    public List<groceriesModel> getAllVeg(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_TYPE + " = " + "'" + "Veg" + "'";

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String buy = cursor.getString(3);
                String expire = cursor.getString(4);
                String company = cursor.getString(5);
                String type = cursor.getString(6);
                byte[] image = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , buy , expire , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

//    public List<groceriesModel> getAllVeg(){
//
//        List<groceriesModel> models = new ArrayList<>();
//        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
//                + " where " + SqliteDB.TYPE + " = " + "'" + "Veg" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;
//
//        Cursor cursor = database.rawQuery(getAllVegQuery, null);
//        if (cursor.moveToFirst()){
//            do {
//                String name = cursor.getString(0);
//                int amount = cursor.getInt(1);
//                String buy = cursor.getString(2);
//                String expire = cursor.getString(3);
//                String company = cursor.getString(4);
//                String type = cursor.getString(5);
//                byte[] image = cursor.getBlob(6);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//
//
//                models.add(new groceriesModel(name , amount , buy , expire , company , type, bitmap));
//            }while (cursor.moveToNext());
//        }
//        return models;
//    }

    //get all Dairies
    public List<groceriesModel> getAllDairies(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_TYPE + " = " + "'" + "Dairy" + "'";

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String buy = cursor.getString(3);
                String expire = cursor.getString(4);
                String company = cursor.getString(5);
                String type = cursor.getString(6);
                byte[] image = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , buy , expire , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

//    public List<groceriesModel> getAllDairies(){
//
//        List<groceriesModel> models = new ArrayList<>();
//        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
//                + " where " + SqliteDB.TYPE + " = " + "'" + "Dairy" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;
//
//        Cursor cursor = database.rawQuery(getAllVegQuery, null);
//        if (cursor.moveToFirst()){
//            do {
//                String name = cursor.getString(0);
//                int amount = cursor.getInt(1);
//                String buy = cursor.getString(2);
//                String expire = cursor.getString(3);
//                String company = cursor.getString(4);
//                String type = cursor.getString(5);
//                byte[] image = cursor.getBlob(6);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//
//
//                models.add(new groceriesModel(name , amount , buy , expire , company , type, bitmap));
//            }while (cursor.moveToNext());
//        }
//        return models;
//    }

    //get all Proteins
    public List<groceriesModel> getAllProteins(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_TYPE + " = " + "'" + "Protein" + "'";

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String buy = cursor.getString(3);
                String expire = cursor.getString(4);
                String company = cursor.getString(5);
                String type = cursor.getString(6);
                byte[] image = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , buy , expire , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

//    public List<groceriesModel> getAllProteins(){
//
//        List<groceriesModel> models = new ArrayList<>();
//        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
//                + " where " + SqliteDB.TYPE + " = " + "'" + "Protein" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;
//
//        Cursor cursor = database.rawQuery(getAllVegQuery, null);
//        if (cursor.moveToFirst()){
//            do {
//                String name = cursor.getString(0);
//                int amount = cursor.getInt(1);
//                String buy = cursor.getString(2);
//                String expire = cursor.getString(3);
//                String company = cursor.getString(4);
//                String type = cursor.getString(5);
//                byte[] image = cursor.getBlob(6);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//
//
//                models.add(new groceriesModel(name , amount , buy , expire , company , type, bitmap));
//            }while (cursor.moveToNext());
//        }
//        return models;
//    }

    //get all Cereals
    public List<groceriesModel> getAllCereals(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_TYPE + " = " + "'" + "Cereal" + "'";

        Cursor cursor = database.rawQuery(getAllVegQuery, null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String buy = cursor.getString(3);
                String expire = cursor.getString(4);
                String company = cursor.getString(5);
                String type = cursor.getString(6);
                byte[] image = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , buy , expire , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

//    public List<groceriesModel> getAllCereals(){
//
//        List<groceriesModel> models = new ArrayList<>();
//        String getAllVegQuery = "select * from " + SqliteDB.TABLE_GROCERIES
//                + " where " + SqliteDB.TYPE + " = " + "'" + "Cereal" + "'" + " and " + SqliteDB.AMOUNT + " >= " + 1;
//
//        Cursor cursor = database.rawQuery(getAllVegQuery, null);
//        if (cursor.moveToFirst()){
//            do {
//                String name = cursor.getString(0);
//                int amount = cursor.getInt(1);
//                String buy = cursor.getString(2);
//                String expire = cursor.getString(3);
//                String company = cursor.getString(4);
//                String type = cursor.getString(5);
//                byte[] image = cursor.getBlob(6);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//
//
//                models.add(new groceriesModel(name , amount , buy , expire , company , type, bitmap));
//            }while (cursor.moveToNext());
//        }
//        return models;
//    }

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
                byte[] image = cursor.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(name , amount , buy , expire , company , type, bitmap));
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
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String buy = cursor.getString(3);
                String expire = cursor.getString(4);
                String company = cursor.getString(5);
                String type = cursor.getString(6);
                byte[] image = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , buy , expire , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

}

