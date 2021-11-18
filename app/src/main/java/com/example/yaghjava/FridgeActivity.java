package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.lang.reflect.Array;

public class FridgeActivity extends AppCompatActivity {

    ImageView cabbage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

//        cabbage.findViewById(R.id.cabbage);
//        cabbage.setBackgroundResource(R.drawable.cabbage);

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
        // String[] array = new String[48];
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.items);
        int numberOfColumns = 4;

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        RecyclerViewAdapter  adapter = new RecyclerViewAdapter(this, data);
        //adapter.setClickListener((RecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);
    }
}