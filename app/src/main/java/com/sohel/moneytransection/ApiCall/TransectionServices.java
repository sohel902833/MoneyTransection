package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Model.TransectionModel;

import java.util.HashMap;

public class TransectionServices implements Transection{
    private Context context;
    private ProgressDialog progressDialog;

    public TransectionServices(Context context){
        this.context=context;
        progressDialog=new ProgressDialog(context);
    }
    @Override
    public void updateAmount(String userPhone, ProgressDialog progressDialog, HashMap<String, Object> updateMap, FirebaseResponse firebaseResponse) {
        progressDialog.show();
        ApiKey.getUserRef()
                .child(userPhone)
                .updateChildren(updateMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            firebaseResponse.onSuccess("Updated Successful",progressDialog);
                        }else{
                            firebaseResponse.onError("Updated Failed",progressDialog);
                        }
                    }
                });
    }

    @Override
    public void addHistory(TransectionModel moneyHistory, ProgressDialog progressDialog, FirebaseResponse firebaseResponse) {
        progressDialog.show();
        ApiKey.getBalanceHistoryRef()
                .child(moneyHistory.getKey())
                .setValue(moneyHistory)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           firebaseResponse.onSuccess("History Added ",progressDialog);
                       }else{
                           firebaseResponse.onError("History Added Failed ",progressDialog);
                       }
                    }
                });
    }
}
