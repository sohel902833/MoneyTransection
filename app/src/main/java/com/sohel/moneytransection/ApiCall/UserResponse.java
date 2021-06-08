package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import com.sohel.moneytransection.Model.UserModel;

public interface UserResponse {
    void onSuccess(String message, ProgressDialog progressDialog,UserModel userModel);
    void onError(String message, ProgressDialog progressDialog);
}
