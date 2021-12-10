package com.example.yaghjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaghjava.R;
import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static Activity activity = SearchActivity.activity;
    static groceriesDA access;
    EditText editText;

    ImageView imageView;
    TextView textView;
    EditText amount, bdate, edate, company;
    Button button;

    public static String input = new String();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        access = new groceriesDA(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        editText = findViewById(R.id.searchbar);
        RecyclerView recyclerViewSearch = findViewById(R.id.search);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                access.openDB();

                ArrayList<groceriesModel> Search = (ArrayList<groceriesModel>) access.search(editable.toString());
                int[] id = new int[Search.size()];
                String[] searchItems = new String[Search.size()];
                String[] type = new String[Search.size()];
                Bitmap[] Image = new Bitmap[Search.size()];
                if (editable.length() != 0) {
                    for (int i = 0; i < Search.size(); i++) {
                        id[i] = Search.get(i).getID();
                        searchItems[i] = Search.get(i).getName();
                        Image[i] = Search.get(i).getImage();
                        type[i] = Search.get(i).getType();
                    }
                    access.closeDB();
                    RecyclerViewSearchAdapter adapterSearch = new RecyclerViewSearchAdapter(getApplicationContext(), id,searchItems,Image,type);
                    int numberOfColumns = 1;
                    recyclerViewSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns));
                    recyclerViewSearch.setAdapter(adapterSearch);

                }

            }
        });

        //To Automatically Select Searchbar And Open Keyboard On Page Open

        EditText editText = (EditText) findViewById(R.id.searchbar);
        editText.requestFocus();
//        access.openDB();
//        access.addToFridge(input);
//        access.closeDB();








    }

    public void popup_company(){
//        View view = getLayoutInflater().inflate(R.layout.popup_company, null);
//
//        imageView = view.findViewById(R.id.c_image);
//        textView =  view.findViewById(R.id.c_name);
//        amount = view.findViewById(R.id.c_amount);
//        bdate = view.findViewById(R.id.c_bdate);
//        edate = view.findViewById(R.id.c_edate);
//        company = view.findViewById(R.id.c_company);
//        button = view.findViewById(R.id.c_add);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        Intent intent =new Intent(SearchActivity.this, PopupCompanyActivity.class);
        startActivity(intent);


    }

//    private void search(String text){
//        access.openDB();
//
//        ArrayList<groceriesModel> Search = (ArrayList<groceriesModel>) access.search(text);
//        searchItems = new String[Search.size()];
//        for (int i = 0;i < Search.size();i++){
//            searchItems[i] = Search.get(i).getName();
//        }
//
//        access.closeDB();
//        searchAdapter.filter(searchItems);
//
//    }

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
