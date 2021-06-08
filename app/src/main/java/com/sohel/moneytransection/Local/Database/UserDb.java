package com.sohel.moneytransection.Local.Database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.sohel.moneytransection.Model.UserModel;

public class UserDb {
    private Activity activity;

    public UserDb(Activity activity) {
        this.activity = activity;
    }


    public void setUserData(UserModel userModel) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",userModel.getName());
        editor.putString("phone",userModel.getPhone());
        editor.commit();
    }
    public String getUserPhone(){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        String phone=sharedPreferences.getString("phone","");
        return  phone;
    }
    public String getUserName() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        return name;
    }
}

