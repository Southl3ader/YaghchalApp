package com.example.yaghjava.model;


import androidx.annotation.Nullable;

public class groceriesModel {
    private String name;
    private int amount;
    private String buy, expire, company , type;

    public groceriesModel(String name, int amount, String buy, String expire, String company, String type) {
        this.name = name;
        this.amount = amount;
        this.buy = buy;
        this.expire = expire;
        this.company = company;
        this.type = type;
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
}

