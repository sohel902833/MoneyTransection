package com.sohel.moneytransection.Model;

public class CashInModel {
    String key,name,phone,amount,date,time,state;

    public CashInModel(){}
    public CashInModel(String key, String name, String phone, String amount, String date, String time,String state) {
        this.key = key;
        this.name = name;
        this.phone = phone;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.state=state;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
