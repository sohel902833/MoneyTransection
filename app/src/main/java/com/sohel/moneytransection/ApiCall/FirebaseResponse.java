package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;
import android.widget.ProgressBar;

public interface FirebaseResponse {
    void onSuccess(String message, ProgressDialog progressDialog);
    void onError(String message, ProgressDialog progressDialog);
}
