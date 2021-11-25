package com.example.yaghjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaghjava.R;
import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    groceriesDA access = new groceriesDA(this);
    EditText editText;
    RecyclerViewSearchAdapter searchAdapter = new RecyclerViewSearchAdapter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        editText = findViewById(R.id.searchbar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });

        //To Automatically Select Searchbar And Open Keyboard On Page Open

        EditText editText = (EditText) findViewById(R.id.searchbar);
        editText.requestFocus();

        RecyclerView recyclerViewSearch = findViewById(R.id.search);

        int numberOfColumns = 1;

        recyclerViewSearch.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        //RecyclerViewSearchAdapter adapterSearch = new RecyclerViewSearchAdapter(this, searchItems);


    }

    private void search(String text){
        access.openDB();

        ArrayList<groceriesModel> Search = (ArrayList<groceriesModel>) access.search(text);
        String[] searchItems = new String[Search.size()];
        for (int i = 0;i < Search.size();i++){
            searchItems[i] = Search.get(i).getName();
        }

        access.closeDB();
        searchAdapter.filter(searchItems);

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
