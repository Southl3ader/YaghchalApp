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
        addFruit();
        addOthers();
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
        access.insert("گوجه فرنگی", "Veg", imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.tomato)));
        access.insert("هویج", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.carrot)));
        access.insert("کلم", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cabbage)));
        access.insert("سیر", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.garlic)));
        access.insert("بادمجان", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.eggplant)));
        access.insert("فلفل دلمه ای", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.bell_pepper)));
        access.insert("فلفل", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pepper)));
        access.insert("چغندر", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.beetroot)));
        access.insert("بروکلی", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.broccoli)));
        access.insert("کدو حلوایی", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pumpkin)));
        access.insert("کرفس", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.celery)));
        access.insert("تربچه", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.radish)));
        access.insert("پیاز", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.onion)));
        access.insert("قارچ", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.mushroom)));
        access.insert("کاهو", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.lettuce)));
        access.insert("سیب زمینی", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.potato)));
        access.insert("خیار", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cucumber)));
        access.insert("نخود فرنگی", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pea_pods)));
        access.insert("زیتون", "Veg",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.olives)));




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
        access.insert("مرغ", "Protein",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.chicken)));



    }

    //Add Cereals to database
    public void addCereal(){
        access.insert("نان", "Cereal",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.bread)));
        access.insert("ذرت", "Cereal",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.corn)));




    }

    //Add Fruits to database
    public void addFruit(){
        access.insert("سیب", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.apple)));
        access.insert("انگور", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.grape)));
        access.insert("توت فرنگی", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.strawberry)));
        access.insert("هلو", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.peach)));
        access.insert("زردآلو", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.apricot)));
        access.insert("آووکادو", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.avocado)));
        access.insert("موز", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.banana)));
        access.insert("توت سیاه", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.blackberry)));
        access.insert("بلوبری", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.blueberry)));
        access.insert("طالبی", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cantaloupe)));
        access.insert("گیلاس", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.cherry)));
        access.insert("نارنگی", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.tangerine)));
        access.insert("نارگیل", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.coconut)));
        access.insert("انجیر", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.fig)));
        access.insert("گریپ فروت", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.grapefruit)));
        access.insert("خربزه", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.melon)));
        access.insert("آلو سیاه", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.damson)));
        access.insert("آلو زرد", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.mirabelle)));
        access.insert("کیوی", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.kiwi)));
        access.insert("لیمو", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.lemon)));
        access.insert("لیمو شیرین", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.sweet_lemon)));
        access.insert("انبه", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.mango)));
        access.insert("توت", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.mulberry)));
        access.insert("شلیل", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.nectarine)));
        access.insert("پرتقال", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.orange)));
        access.insert("گلابی", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pear)));
        access.insert("خرمالو", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.persimmon)));
        access.insert("آناناس", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pineapple)));
        access.insert("انار", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.pomegranate)));
        access.insert("به", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.quince)));
        access.insert("تمشک", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.raspberry)));
        access.insert("هندوانه", "Fruit",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.watermelon)));



    }

    public void addOthers(){
        access.insert("سوسیس", "Others",imageToBytes(BitmapFactory.decodeResource(getResources(),R.drawable.sausage)));




    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}