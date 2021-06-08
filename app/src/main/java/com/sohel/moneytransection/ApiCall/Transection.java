package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import com.sohel.moneytransection.Model.TransectionModel;

import java.util.HashMap;

public interface Transection {
    void updateAmount(String userPhone, ProgressDialog progressDialog, HashMap<String,Object> updateMap, FirebaseResponse firebaseResponse);
    void addHistory(TransectionModel moneyHistory, ProgressDialog progressDialog, FirebaseResponse firebaseResponse);
}
