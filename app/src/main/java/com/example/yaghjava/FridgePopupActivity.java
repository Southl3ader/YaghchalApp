package com.example.yaghjava;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yaghjava.dataBase.groceriesDA;

public class FridgePopupActivity extends AppCompatActivity {
    static groceriesDA access ;


    ImageView imageView;
    TextView textView ;
    EditText amount ;
    EditText bdate ;
    EditText edate ;
    EditText company;
    Button button ;
    private String[] itemcount;
    Context fridge_cnt;

    FridgePopupActivity(String[] ItemAmount , Context context){
        this.itemcount = ItemAmount;
        this.fridge_cnt = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        access.openDB();

        setContentView(R.layout.details_popup_company);
        imageView = findViewById(R.id.fridgepop_image);
        textView =  findViewById(R.id.fridgepop_name);
        amount = findViewById(R.id.fridgepop_amount);
        bdate = findViewById(R.id.fridgepop_bdate);
        edate = findViewById(R.id.fridgepop_edate);
        button = findViewById(R.id.fridgepop_savechange);
    }
}


