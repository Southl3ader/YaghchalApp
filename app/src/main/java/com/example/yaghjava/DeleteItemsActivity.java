package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.yaghjava.dataBase.groceriesDA;

public class DeleteItemsActivity extends AppCompatActivity {

    groceriesDA access = new groceriesDA(this);

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_items);

        delete = findViewById(R.id.delete);
       // delete.setOnClickListener(access.defaultGro(););

    }
}