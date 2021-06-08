package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;
import android.renderscript.Sampler;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Model.CashInModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseCashIn  implements  CashInInterface{




    @Override
    public void addCashIn(CashInModel cashInModel, ProgressDialog progressDialog, FirebaseResponse firebaseResponse) {
        progressDialog.show();
        ApiKey.getCashIn()
                .child(cashInModel.getKey())
                .setValue(cashInModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            firebaseResponse.onSuccess("Cash In Request Sent Successful.",progressDialog);
                        }else{
                            firebaseResponse.onError("Cash In Request Failed.",progressDialog);
                        }
                    }
                });
    }

    @Override
    public void updateCashIn(String key,HashMap<String, Object> updateCashIn, ProgressDialog progressDialog, FirebaseResponse firebaseResponse) {
        progressDialog.show();
        ApiKey.getCashIn()
                .child(key)
                .updateChildren(updateCashIn)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            firebaseResponse.onSuccess("Cash In Updated Successful.",progressDialog);
                        }else{
                            firebaseResponse.onError("Cash In Updated Failed.",progressDialog);
                        }
                    }
                });
    }

    @Override
    public void removeCashIn(String key, ProgressDialog progressDialog, FirebaseResponse firebaseResponse) {
            ApiKey.getCashIn()
                    .child(key)
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                firebaseResponse.onSuccess("Cash In Deleted Successful.",progressDialog);
                            }else{
                                firebaseResponse.onError("Cash In Deleted Failed.",progressDialog);
                            }
                        }
                    });
    }

    @Override
    public void getAllCashIn(ProgressDialog progressDialog, CashInResponse firebaseResponse) {
        progressDialog.show();
        List<CashInModel> cashInList=new ArrayList<>();
        ApiKey.getCashIn()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            cashInList.clear();
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                CashInModel cashInModel=snapshot1.getValue(CashInModel.class);
                                cashInList.add(cashInModel);
                            }
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }else{
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        firebaseResponse.onError("No Cash In Found",progressDialog);
                    }
                });
    }

    @Override
    public void getAllCashIn(String phone, ProgressDialog progressDialog, CashInResponse firebaseResponse) {
      progressDialog.show();
       List<CashInModel> cashInList=new ArrayList<>();
        ApiKey.getCashIn()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            cashInList.clear();
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                CashInModel cashInModel=snapshot1.getValue(CashInModel.class);
                                if(cashInModel.getPhone().equals(phone))
                                    {
                                        cashInList.add(cashInModel);
                                    }
                            }
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }else{
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        firebaseResponse.onError("No Cash In Found",progressDialog);
                    }
                });

    }

    @Override
    public void getAllCashIn(String phone, String state, ProgressDialog progressDialog, CashInResponse firebaseResponse) {
        progressDialog.show();
        List<CashInModel> cashInList=new ArrayList<>();
        ApiKey.getCashIn()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            cashInList.clear();
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                CashInModel cashInModel=snapshot1.getValue(CashInModel.class);
                                if(cashInModel.getPhone().equals(phone) && cashInModel.getState().equals(state))
                                {
                                    cashInList.add(cashInModel);
                                }
                            }
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }else{
                            firebaseResponse.onSuccess("Success",progressDialog,cashInList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        firebaseResponse.onError("No Cash In Found",progressDialog);
                    }
                });
    }


}
