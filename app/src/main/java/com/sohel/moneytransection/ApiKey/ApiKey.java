package com.sohel.moneytransection.ApiKey;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApiKey {

    private  static  final DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users");
    private  static  final DatabaseReference cashIn= FirebaseDatabase.getInstance().getReference().child("CashIn");
    private  static  final DatabaseReference balanceHistoryRef= FirebaseDatabase.getInstance().getReference().child("TransferHistory");
    private  static  final DatabaseReference adminAccount= FirebaseDatabase.getInstance().getReference().child("Admin").child("AdminAccount");
    private  static  final DatabaseReference appSetting= FirebaseDatabase.getInstance().getReference().child("Admin").child("AppSettings");


    public static DatabaseReference getAppSetting() {
        return appSetting;
    }

    public static DatabaseReference getCashIn() {
        return cashIn;
    }

    public static DatabaseReference getBalanceHistoryRef() {
        return balanceHistoryRef;
    }

    public static DatabaseReference getUserRef() {
        return userRef;
    }
    public static DatabaseReference getAdminAccount() {
        return adminAccount;
    }
}
