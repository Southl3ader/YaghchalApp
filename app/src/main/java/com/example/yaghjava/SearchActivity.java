package com.example.yaghjava;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yaghjava.R;
import com.example.yaghjava.dataBase.groceriesDA;

public class SearchActivity extends AppCompatActivity {

    groceriesDA access = new groceriesDA(this);
    ImageView imageView;
    EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        //To Automatically Select Searchbar And Open Keyboard On Page Open

        EditText editText = (EditText) findViewById(R.id.searchbar);
        editText.requestFocus();

    }

    //Check For Back Button To Go Back To Main Page Not Close App

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(SearchActivity.this, FridgeActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }

        return super.onKeyDown(keyCode, event);
    }
}
