package com.example.yaghjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yaghjava.dataBase.groceriesDA;
import com.example.yaghjava.model.groceriesModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    groceriesDA access = new groceriesDA(MainActivity.this);

    Button btn;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.logo);

        img.setBackgroundResource(R.drawable.ic_fridge);

        access.openDB();
        addVegetables();
        addDairies();
        addProtein();
        addCereal();
        access.closeDB();

        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
        }
        public void onFinish() {
            Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
    //Image to byte
    public byte[] imageToBytes (Bitmap bitmap){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
        byte[] img = byteArray.toByteArray();
        return img;

    }

    //Add vegetables to database
    public void addVegetables(){
        access.insert("گوجه", "Veg", imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.tomato)));
        access.insert("هویج", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.carrot)));
        access.insert("کلم", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cabbage)));
        access.insert("سیر", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.garlic)));
        access.insert("بادمجان", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.eggplant)));




        access.getAllVeg();


    }

    //Add dairies to database
    public void addDairies(){
        access.insert("شیر", "Dairy",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.milk)));
        access.insert("پنیر", "Dairy",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cheese)));



    }
    //Add Protein to database
    public void addProtein(){
        access.insert("تخم مرغ", "Protein",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.egg)));
        access.insert("گوشت قرمز", "Protein",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.meat)));
        access.insert("ماهی", "Protein",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.fish)));



    }

    //Add Cereals to database
    public void addCereal(){
        access.insert("نان", "Cereal",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.bread)));




    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}