package com.example.yaghjava;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yaghjava.R;
import com.example.yaghjava.dataBase.groceriesDA;

public class RecipeActivity extends AppCompatActivity {

    groceriesDA access = new groceriesDA(this);
    ImageView imageView;
    EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);


        //Bottom Menu OnClick Functions To Change Pages

        Button button = (Button) findViewById(R.id.fridge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeActivity.this, FridgeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        Button button1 = (Button) findViewById(R.id.settings);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }
    //Check For Back Button To Go Back To Main Page Not Close App

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(RecipeActivity.this, FridgeActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }

        return super.onKeyDown(keyCode, event);
    }
}
