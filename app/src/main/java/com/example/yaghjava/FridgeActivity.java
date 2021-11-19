package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FridgeActivity extends AppCompatActivity {
    groceriesDA access = new groceriesDA(this);
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        imageView = findViewById(R.id.img);
        //imageView.setBackgroundResource(R.drawable.tomato);

        access.openDB();

        ArrayList<groceriesModel> Veg = (ArrayList<groceriesModel>) access.getAllVeg();
        String[] vegetables = new String[Veg.size()];
        for (int i = 0;i < Veg.size();i++){
            vegetables[i] = Veg.get(i).getName();
        }

        ArrayList<groceriesModel> Dair = (ArrayList<groceriesModel>) access.getAllDairies();
        String[] dairies = new String[Dair.size()];
        for (int i = 0;i < Dair.size();i++){
            dairies[i] = Dair.get(i).getName();
        }

        ArrayList<groceriesModel> Prot = (ArrayList<groceriesModel>) access.getAllProteins();
        String[] proteins = new String[Prot.size()];
        for (int i = 0;i < Prot.size();i++){
            proteins[i] = Prot.get(i).getName();
        }

        ArrayList<groceriesModel> Cer = (ArrayList<groceriesModel>) access.getAllCereals();
        String[] cereals = new String[Cer.size()];
        for (int i = 0;i < Cer.size();i++){
            cereals[i] = Cer.get(i).getName();
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

        RecyclerViewAdapter  adapterVeg = new RecyclerViewAdapter(this, vegetables);
        recyclerViewVeg.setAdapter(adapterVeg);
        RecyclerViewAdapter  adapterDair = new RecyclerViewAdapter(this, dairies);
        recyclerViewDair.setAdapter(adapterDair);
        RecyclerViewAdapter  adapterProt = new RecyclerViewAdapter(this, proteins);
        recyclerViewProt.setAdapter(adapterProt);
        RecyclerViewAdapter  adapterCer = new RecyclerViewAdapter(this, cereals);
        recyclerViewCer.setAdapter(adapterCer);

    }
}