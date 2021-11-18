package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.widget.Button;
import android.widget.Toast;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;



import java.util.List;


public class MainActivity extends AppCompatActivity {
    groceriesDA access = new groceriesDA(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        access.openDB();
        addVegetables();
        addDairies();
        addProtein();
        addCereal();
//        access.updateVegAttributes("Tomato", 20, "27/9", "1/10");
        access.defaultGro("Tomato");

        access.updateVegAttributes("Tomato",1,"27/2","27/4",null,"Veg");
        access.updateVegAttributes("Cheese",0,"27/2","27/4",null,"Dairy");
        access.search("tom");
        access.closeDB();


        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
        }
        public void onFinish() {
            Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    //Add vegetables to database
    public void addVegetables(){
        access.insert("Tomato", "Veg");
        access.insert("Cucumber", "Veg");

        access.getAllVeg();


    }
    //Add dairies to database
    public void addDairies(){

        access.insert("Milk", "Dairy");
        access.insert("Cheese", "Dairy");



    }
    //Add Protein to database
    public void addProtein(){

        access.insert("Egg", "Protein");
        access.insert("Red Meat", "Protein");



    }
    //Add Cereals to database
    public void addCereal(){

        access.insert("Bread", "Cereal");
        access.insert("Rice", "Cereal");



    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}