package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import com.sohel.moneytransection.Model.CashInModel;

import java.util.HashMap;
import java.util.List;

public interface CashInInterface {
    void addCashIn(CashInModel cashInModel, ProgressDialog progressDialog, FirebaseResponse firebaseResponse);
    void updateCashIn(String key,HashMap<String,Object> updateCashIn, ProgressDialog progressDialog, FirebaseResponse firebaseResponse);
    void removeCashIn(String key, ProgressDialog progressDialog, FirebaseResponse firebaseResponse);
    void  getAllCashIn(ProgressDialog progressDialog, CashInResponse firebaseResponse);
    void getAllCashIn(String phone, ProgressDialog progressDialog, CashInResponse firebaseResponse);
    void getAllCashIn(String phone,String success, ProgressDialog progressDialog, CashInResponse firebaseResponse);
}
