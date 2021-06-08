package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import java.util.HashMap;

public interface UserInterface {
     void getCurrentUser(String userPhone,ProgressDialog progressDialog,UserResponse userResponse);
     void getUser(String userPhone,ProgressDialog progressDialog,UserResponse userResponse);
     void updateUser(String userPhone, HashMap<String,Object> updateUserMap, ProgressDialog progressDialog,FirebaseResponse firebaseResponse);
}
