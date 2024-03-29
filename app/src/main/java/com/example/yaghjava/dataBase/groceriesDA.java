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
                + " where " + SqliteDB.F_ID + " in ( "
                + " select " + SqliteDB.F_ID + " from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_NAME + " = " + "'" + input + "'"
                + " order by " + SqliteDB.F_ID + " desc limit 1)";
        database.execSQL(query1);
    }

    //Remove grocery from fridge
    public void remove(int id){
        String query = "DELETE FROM " + SqliteDB.TABLE_FRIDGE
                + " WHERE " + SqliteDB.F_ID + " = " + id;

        database.execSQL(query);
    }
    //update grocery details in fridge
    public void updateAttributes(int id , int amount, String BDate, String ExDate, String company){
        String upQuery = "update " + SqliteDB.TABLE_FRIDGE
                + " set " + SqliteDB.F_AMOUNT + " = " +  amount + " , "
                + SqliteDB.F_BUY_DATE + " = " + "'" + BDate + "'" + " , "
                + SqliteDB.F_EXPIRE_DATE + " = " + "'" + ExDate + "'" + " , "
                + SqliteDB.F_COMPANY + " = " + "'" + company + "'"
                + " where " + SqliteDB.F_ID + " = " + id;

        database.execSQL(upQuery);
    }

    //Reduce grocery amount by 1
    public void reduce(int id){
        String query = "UPDATE " + SqliteDB.TABLE_FRIDGE
                + " SET " + SqliteDB.F_AMOUNT + " = " + SqliteDB.F_AMOUNT + " -1 "
                + "WHERE " + SqliteDB.F_ID + " = " + id;
        database.execSQL(query);

        String query1 = "DELETE FROM " + SqliteDB.TABLE_FRIDGE
                + " WHERE " + SqliteDB.F_ID + " = " + id + " and " + SqliteDB.F_AMOUNT + " = " + 0;
        database.execSQL(query1);
    }

    //Increase grocery amount by 1
    public void increase(int id){
        String query = "UPDATE " + SqliteDB.TABLE_FRIDGE
                + " SET " + SqliteDB.F_AMOUNT + " = " + SqliteDB.F_AMOUNT + " +1 "
                + "WHERE " + SqliteDB.F_ID + " = " + id;
        database.execSQL(query);
    }



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




//get all Fruits
public List<groceriesModel> getAllFruits(){

    List<groceriesModel> models = new ArrayList<>();
    String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
            + " where " + SqliteDB.F_TYPE + " = " + "'" + "Fruit" + "'";

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

    //get all other groceries
    public List<groceriesModel> getOthers(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllVegQuery = "select * from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_TYPE + " = " + "'" + "Others" + "'";

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

    //Add to shopping list
    public void addToShop(String input,int amount, String company){
        String query ="insert into " + SqliteDB.TABLE_SHOP + " ( " + SqliteDB.S_NAME + " , " + SqliteDB.S_TYPE + " , " + SqliteDB.S_IMAGE + " )"
                + " select " + SqliteDB.NAME + " , " + SqliteDB.TYPE + " , " + SqliteDB.IMAGE + " from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.NAME + " = " + "'" + input + "'";

        database.execSQL(query);
        String query1 = "update " + SqliteDB.TABLE_SHOP
                + " set " + SqliteDB.S_AMOUNT + " = " +  amount + " , "
                + SqliteDB.S_COMPANY + " = " + "'" + company + "'"
                + " where " + SqliteDB.S_ID + " in ( "
                + " select " + SqliteDB.S_ID + " from " + SqliteDB.TABLE_SHOP
                + " where " + SqliteDB.S_NAME + " = " + "'" + input + "'"
                + " order by " + SqliteDB.S_ID + " desc limit 1)";
        database.execSQL(query1);
    }

    //Add to fridge from shopping list
    public void shopToFridge(int sID, String input,int amount, String buy, String ex, String company){
        String query ="insert into " + SqliteDB.TABLE_FRIDGE + " ( " + SqliteDB.F_NAME + " , " + SqliteDB.F_TYPE + " , " + SqliteDB.F_IMAGE + " )"
                + " select " + SqliteDB.NAME + " , " + SqliteDB.TYPE + " , " + SqliteDB.IMAGE + " from " + SqliteDB.TABLE_GROCERIES
                + " where " + SqliteDB.NAME + " = " + "'" + input + "'";

        database.execSQL(query);

        String query1 = "update " + SqliteDB.TABLE_FRIDGE
                + " set " + SqliteDB.F_AMOUNT + " = " +  amount + " , "
                + SqliteDB.F_BUY_DATE + " = " + "'" + buy + "'" + " , "
                + SqliteDB.F_EXPIRE_DATE + " = " + "'" + ex + "'" + " , "
                + SqliteDB.F_COMPANY + " = " + "'" + company + "'"
                + " where " + SqliteDB.F_ID + " in ( "
                + " select " + SqliteDB.F_ID + " from " + SqliteDB.TABLE_FRIDGE
                + " where " + SqliteDB.F_NAME + " = " + "'" + input + "'"
                + " order by " + SqliteDB.F_ID + " desc limit 1)";
        database.execSQL(query1);

        String query2 = "DELETE FROM " + SqliteDB.TABLE_SHOP
                + " WHERE " + SqliteDB.S_ID + " = " + sID;

        database.execSQL(query2);

    }

    //Delete item from shopping list
    public void removeShop(int id){
        String query ="DELETE FROM " + SqliteDB.TABLE_SHOP
                + " WHERE " + SqliteDB.S_ID + " = " + id;

        database.execSQL(query);
    }

    //Get all groceries in shopping list
    public List<groceriesModel> getAllShop(){

        List<groceriesModel> models = new ArrayList<>();
        String getAllQuery = "select * from " + SqliteDB.TABLE_SHOP;
        Cursor cursor = database.rawQuery(getAllQuery, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String company = cursor.getString(3);
                String type = cursor.getString(4);
                byte[] image = cursor.getBlob(5);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);


                models.add(new groceriesModel(id,name , amount , company , type, bitmap));
            }while (cursor.moveToNext());
        }
        return models;
    }

}

