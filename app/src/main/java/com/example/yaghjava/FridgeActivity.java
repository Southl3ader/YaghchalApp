package com.example.yaghjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity {
    groceriesDA access = new groceriesDA(this);
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        access.openDB();

        ArrayList<groceriesModel> Veg = (ArrayList<groceriesModel>) access.getAllVeg();
        String[] vegetables = new String[Veg.size()];
        Bitmap[] vegImage = new Bitmap[Veg.size()];
        String[] vegAmount = new String[Veg.size()];

        for (int i = 0;i < Veg.size();i++){
            vegetables[i] = Veg.get(i).getName();
            vegImage[i] = Veg.get(i).getImage();
            vegAmount[i] = String.valueOf(Veg.get(i).getAmount());
        }


        ArrayList<groceriesModel> Dair = (ArrayList<groceriesModel>) access.getAllDairies();
        String[] dairies = new String[Dair.size()];
        Bitmap[] dairImage = new Bitmap[Dair.size()];
        String[] dairAmount = new String[Dair.size()];

        for (int i = 0;i < Dair.size();i++){
            dairies[i] = Dair.get(i).getName();
            dairImage[i] = Dair.get(i).getImage();
            dairAmount[i] = String.valueOf(Dair.get(i).getAmount());

        }

        ArrayList<groceriesModel> Prot = (ArrayList<groceriesModel>) access.getAllProteins();
        String[] proteins = new String[Prot.size()];
        Bitmap[] protImage = new Bitmap[Prot.size()];
        String[] protAmount = new String[Prot.size()];

        for (int i = 0;i < Prot.size();i++){
            proteins[i] = Prot.get(i).getName();
            protImage[i] = Prot.get(i).getImage();
            protAmount[i] = String.valueOf(Prot.get(i).getAmount());
            System.out.println(Prot.get(i).getAmount());

        }

        ArrayList<groceriesModel> Cer = (ArrayList<groceriesModel>) access.getAllCereals();
        String[] cereals = new String[Cer.size()];
        Bitmap[] cerImage = new Bitmap[Cer.size()];
        String[] cerAmount = new String[Cer.size()];

        for (int i = 0;i < Cer.size();i++){
            cereals[i] = Cer.get(i).getName();
            cerImage[i] = Cer.get(i).getImage();
            cerAmount[i] = String.valueOf(Cer.get(i).getAmount());

        }
        access.closeDB();

        RecyclerView recyclerViewVeg = findViewById(R.id.vegetables);
        RecyclerView recyclerViewDair = findViewById(R.id.dairies);
        RecyclerView recyclerViewProt = findViewById(R.id.protein);
        RecyclerView recyclerViewCer = findViewById(R.id.cereals);

        int numberOfColumns = 4;

        recyclerViewVeg.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewDair.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewProt.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewCer.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        RecyclerViewItemsAdapter adapterVeg = new RecyclerViewItemsAdapter(this, vegetables, vegImage, vegAmount);
        recyclerViewVeg.setAdapter(adapterVeg);
        RecyclerViewItemsAdapter adapterDair = new RecyclerViewItemsAdapter(this, dairies, dairImage, dairAmount);
        recyclerViewDair.setAdapter(adapterDair);
        RecyclerViewItemsAdapter adapterProt = new RecyclerViewItemsAdapter(this, proteins, protImage, protAmount);
        recyclerViewProt.setAdapter(adapterProt);
        RecyclerViewItemsAdapter adapterCer = new RecyclerViewItemsAdapter(this, cereals, cerImage, cerAmount);
        recyclerViewCer.setAdapter(adapterCer);


        //Bottom Menu OnClick Functions To Change Pages

        Button button = (Button) findViewById(R.id.recipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FridgeActivity.this, RecipeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        Button button1 = (Button) findViewById(R.id.settings);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FridgeActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });


        //OnClick Function For SearchBar

        EditText editText = (EditText) findViewById(R.id.searchbar);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FridgeActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }
}