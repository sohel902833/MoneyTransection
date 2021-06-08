package com.sohel.moneytransection.ApiCall;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Model.UserModel;

import java.util.HashMap;

public class UserCrud implements  UserInterface{


    @Override
    public void getCurrentUser(String userPhone, ProgressDialog progressDialog, UserResponse userResponse) {
        progressDialog.show();
        ApiKey.getUserRef()
                .child(userPhone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel user=snapshot.getValue(UserModel.class);
                            userResponse.onSuccess("User Get Success",progressDialog,user);

                        }else{
                            userResponse.onError("User Not Found",progressDialog);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userResponse.onError("User Not Found",progressDialog);

                    }
                });
    }

    @Override
    public void getUser(String userPhone, ProgressDialog progressDialog, UserResponse userResponse) {
        progressDialog.show();
        ApiKey.getUserRef()
                .child(userPhone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel user=snapshot.getValue(UserModel.class);
                            userResponse.onSuccess("User Get Success",progressDialog,user);

                        }else{
                            userResponse.onError("User Not Found",progressDialog);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userResponse.onError("User Not Found",progressDialog);

                    }
                });
    }

    @Override
    public void updateUser(String userPhone, HashMap<String, Object> updateUserMap, ProgressDialog progressDialog, FirebaseResponse firebaseResponse) {
        progressDialog.show();
        ApiKey.getUserRef()
                .child(userPhone)
                .updateChildren(updateUserMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           firebaseResponse.onSuccess("User Update Success",progressDialog);
                       }else{
                            firebaseResponse.onError("User Update Failed",progressDialog);
                       }
                    }
                });
    }
}
