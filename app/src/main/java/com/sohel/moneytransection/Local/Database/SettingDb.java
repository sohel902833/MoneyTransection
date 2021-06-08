package com.sohel.moneytransection.Local.Database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.sohel.moneytransection.Model.AppSettingModel;
import com.sohel.moneytransection.Model.UserModel;

public class SettingDb {
    private Activity activity;

    public SettingDb(Activity activity) {
        this.activity = activity;
    }


    public void setSetting(AppSettingModel settingModel) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("versionNo", String.valueOf(settingModel.getVersionNo()));
        editor.putInt("priority",settingModel.getPriority());
        editor.putInt("cashOut",settingModel.getCashOut());
        editor.putInt("sendMoney",settingModel.getSendMoney());
        editor.putInt("convertBalance",settingModel.getConvertBalance());
        editor.putInt("referBonus",settingModel.getReferBonus());
        editor.commit();
    }



    public AppSettingModel getAppSetting(){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("AppSetting", Context.MODE_PRIVATE);

        AppSettingModel settingModel=new AppSettingModel(
                Double.parseDouble(sharedPreferences.getString("versionNo","")),
                sharedPreferences.getInt("priority",0),
                sharedPreferences.getInt("cashOut",5),
                sharedPreferences.getInt("sendMoney",5),
                sharedPreferences.getInt("convertBalance",5),
                sharedPreferences.getInt("referBonus",500)
        );

        return  settingModel;
    }
}

