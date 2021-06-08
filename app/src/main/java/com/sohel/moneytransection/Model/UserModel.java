package com.sohel.moneytransection.Model;

public class UserModel {

    String name,address,country,city,phone,password,userId,reeferId,myRefer,accontStatus;
    int cashBalance, reeferBalance;

    public UserModel(){

    }


    public UserModel(String name, String address, String country, String city, String phone, String password, String userId, String reeferId, String myRefer, String accontStatus, int cashBalance, int reeferBalance) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.password = password;
        this.userId = userId;
        this.reeferId = reeferId;
        this.myRefer = myRefer;
        this.accontStatus = accontStatus;
        this.cashBalance = cashBalance;
        this.reeferBalance = reeferBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getMyRefer() {
        return myRefer;
    }

    public void setMyRefer(String myRefer) {
        this.myRefer = myRefer;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReeferId() {
        return reeferId;
    }

    public void setReeferId(String reeferId) {
        this.reeferId = reeferId;
    }

    public String getAccontStatus() {
        return accontStatus;
    }

    public void setAccontStatus(String accontStatus) {
        this.accontStatus = accontStatus;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public int getReeferBalance() {
        return reeferBalance;
    }

    public void setReeferBalance(int reeferBalance) {
        this.reeferBalance = reeferBalance;
    }
}
