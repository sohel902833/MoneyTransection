package com.sohel.moneytransection.Model;

public class TransectionModel {
    String key;
    String userPhone;
    String userName;
    int amount;
    String time;

    public TransectionModel() {
    }

    public TransectionModel(String key, String userPhone, String userName, int amount, String time) {
        this.key=key;
        this.userPhone = userPhone;
        this.userName = userName;
        this.amount = amount;
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
