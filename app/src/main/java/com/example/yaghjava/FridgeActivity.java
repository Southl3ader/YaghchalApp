package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FridgeActivity extends AppCompatActivity {
    groceriesDA access = new groceriesDA(this);
    ImageView cabbage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

//        cabbage.findViewById(R.id.cabbage);
//        cabbage.setBackgroundResource(R.drawable.cabbage);
        access.openDB();

        ArrayList<groceriesModel> Veg = (ArrayList<groceriesModel>) access.getAllVeg();
        access.closeDB();
        String[] data = new String[Veg.size()];
        for (int i = 0;i < Veg.size();i++){
            data[i] = Veg.get(i).getName();
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.items);
        int numberOfColumns = 4;

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        RecyclerViewAdapter  adapter = new RecyclerViewAdapter(this, data);
        //adapter.setClickListener((RecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);
        //g
    }
}