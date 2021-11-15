package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
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
//        access.updateVegAttributes("Tomato", 20, "27/9", "1/10");
        access.defaultVeg("Tomato");

        access.updateVegAttributes("Tomato",0,"27/2","27/4",null,"Veg");
        access.updateVegAttributes("Cheese",0,"27/2","27/4",null,"Dairy");
        List<groceriesModel> models = access.search("tom");
        access.closeDB();
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

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void displayToastMsg(View v) {
        toastMsg("Hello how are you today!!");
    }
}