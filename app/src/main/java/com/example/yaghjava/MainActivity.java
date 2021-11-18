package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{
//    RecyclerViewAdapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ArrayList<String> vegetableNames = new ArrayList<>();
//        vegetableNames.add("carrot");
//        vegetableNames.add("eggplant");
//        vegetableNames.add("cabbage");
//        vegetableNames.add("onion");
//        vegetableNames.add("garlic");
//
//        RecyclerView recyclerView = findViewById(R.id.items);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RecyclerViewAdapter(this, vegetableNames);
//        adapter.setClickListener((RecyclerViewAdapter.ItemClickListener) this);
//        recyclerView.setAdapter(adapter);
//    }
//
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//    }
//
//    public void toastMsg(String msg) {
//        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
//
//    public void displayToastMsg(View v) {
//        toastMsg("Hello how are you today!!");
//    }

    RecyclerViewAdapter adapter;
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });
        // data to populate the RecyclerView with
//        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.items);
//        int numberOfColumns = 6;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//        adapter = new RecyclerViewAdapter(this, data);
//        adapter.setClickListener((RecyclerViewAdapter.ItemClickListener) this);
//        recyclerView.setAdapter(adapter);
    }


    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }
}