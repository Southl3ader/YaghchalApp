package com.example.yaghjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    static groceriesDA access;
    ImageView imageView;
    EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        access = new groceriesDA(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppinglist_activity);
        show();

        //Bottom Menu OnClick Functions To Change Pages

        Button button = (Button) findViewById(R.id.fridge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingListActivity.this, FridgeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        Button button1 = (Button) findViewById(R.id.settings);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingListActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }
    //Check For Back Button To Go Back To Main Page Not Close App

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ShoppingListActivity.this, FridgeActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        show();
    }

    public void show(){
        access.openDB();

        ArrayList<groceriesModel> shop = (ArrayList<groceriesModel>) access.getAllShop();
        int[] shopID = new int[shop.size()];
        String[] shopNames = new String[shop.size()];
        Bitmap[] shopImage = new Bitmap[shop.size()];
        String[] shopAmount = new String[shop.size()];
        String[] shopType= new String[shop.size()];
        String[] shopCompany = new String[shop.size()];

        for (int i = 0;i < shop.size();i++){
            shopID[i] = shop.get(i).getID();
            shopNames[i] = shop.get(i).getName();
            shopImage[i] = shop.get(i).getImage();
            shopAmount[i] = String.valueOf(shop.get(i).getAmount());
            shopType[i] = shop.get(i).getType();
            shopCompany[i] = shop.get(i).getCompany();
        }

        RecyclerViewShoppingListAdapter adapterShop = new RecyclerViewShoppingListAdapter(getApplicationContext(), shopID,shopNames,shopCompany,shopImage, shopAmount, shopType);
        RecyclerView recyclerViewShop = findViewById(R.id.shoppingList_page);
        int numberOfColumns = 1;
        recyclerViewShop.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewShop.setAdapter(adapterShop);
    }
}
