package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PopupCompanyActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView ;
    EditText amount ;
    EditText bdate ;
    EditText edate ;
    EditText company;
    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_company);
//        imageView = findViewById(R.id.c_image);
//        textView =  findViewById(R.id.c_name);
//        amount = findViewById(R.id.c_amount);
//        bdate = findViewById(R.id.c_bdate);
//        edate = findViewById(R.id.c_edate);
//        company = findViewById(R.id.c_company);
//        button = findViewById(R.id.c_add);

    }
}