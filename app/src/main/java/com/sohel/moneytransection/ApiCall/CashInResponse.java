package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import com.sohel.moneytransection.Model.CashInModel;

import java.util.List;

public interface CashInResponse {
    void onSuccess(String message, ProgressDialog progressDialog, List<CashInModel> cashInModelList);
    void onError(String message, ProgressDialog progressDialog);

}
