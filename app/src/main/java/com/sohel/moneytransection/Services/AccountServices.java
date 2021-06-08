package com.sohel.moneytransection.Services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Local.Database.SettingDb;
import com.sohel.moneytransection.Local.Database.UserDb;
import com.sohel.moneytransection.Model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountServices {
    Activity context;
    ProgressDialog progressDialog;
    UserDb userDb;
    SettingDb settingDb;
    List<UserModel> userModelList=new ArrayList<>();

    private  boolean isFirstTime=false;


    public AccountServices(Activity context) {
        this.context = context;
        progressDialog=new ProgressDialog(context);
        userDb=new UserDb(context);
        settingDb=new SettingDb(context);
     }
    public void activeAccount(UserModel user){
            progressDialog.setMessage("Please Wait.");
            progressDialog.setCancelable(false);
            progressDialog.show();
            isFirstTime=false;

            if(user.getCashBalance()>=1000){
                if(user.getAccontStatus().equals("inactive")) {

                    HashMap<String, Object> updateMap = new HashMap<>();
                    updateMap.put("accontStatus", "active");
                    updateMap.put("cashBalance", user.getCashBalance() - 1000);

                    ApiKey.getUserRef()
                            .child(userDb.getUserPhone())
                            .updateChildren(updateMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                          ApiKey.getUserRef()
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if (snapshot.exists()) {
                                                                userModelList.clear();
                                                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                                                    UserModel user = snapshot1.getValue(UserModel.class);
                                                                    userModelList.add(user);
                                                                }
                                                                if(isFirstTime==false){
                                                                    sendReferBalance(user);
                                                                }else{
                                                                    isFirstTime=true;
                                                                }
                                                             } else {
                                                                progressDialog.dismiss();
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "Account Active Failed..Please Try Again Leter.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(context, "Your Account Already Activated", Toast.LENGTH_SHORT).show();

                }
            }else{
                progressDialog.dismiss();
                Toast.makeText(context, "Insufficient Balance.Please Cash In First", Toast.LENGTH_SHORT).show();
            }




    }
    public void sendReferBalance(UserModel user){
        UserModel referUser= getReferUser(user);
        if(referUser!=null){

            HashMap<String, Object> updateMap = new HashMap<>();
            updateMap.put("cashBalance", referUser.getCashBalance()+settingDb.getAppSetting().getReferBonus());
            if(isFirstTime==false){
                isFirstTime=true;
                ApiKey.getUserRef()
                        .child(referUser.getPhone())
                        .updateChildren(updateMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    isFirstTime=true;
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Account Activated Successfully", Toast.LENGTH_SHORT).show();
                                }else{
                                    isFirstTime=true;
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }




        }
    }
    public UserModel getReferUser(UserModel myData){
            UserModel referUser=null;
           for(int i=0; i<userModelList.size(); i++){
               UserModel currentUser=userModelList.get(i);
               if(myData.getReeferId().equals(currentUser.getMyRefer())){
                   referUser=currentUser;
                   break;
               }
           }
        return  referUser;
    }



}
