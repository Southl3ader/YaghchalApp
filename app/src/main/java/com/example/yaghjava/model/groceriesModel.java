package com.example.yaghjava.model;


import android.graphics.Bitmap;

public class groceriesModel {
    private int ID;
    private String name;
    private int amount;
    private String buy, expire, company , type;
    private Bitmap image;

    public groceriesModel(int ID, String name, int amount, String buy, String expire, String company, String type, Bitmap image) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.buy = buy;
        this.expire = expire;
        this.company = company;
        this.type = type;
        this.image = image;
    }
    public groceriesModel( String name, int amount, String buy, String expire, String company, String type, Bitmap image) {
        this.name = name;
        this.amount = amount;
        this.buy = buy;
        this.expire = expire;
        this.company = company;
        this.type = type;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

