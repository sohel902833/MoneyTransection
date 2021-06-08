package com.sohel.moneytransection.Services;

import android.widget.EditText;

public class Common {
    public static void showEditTextError(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
        return;
    }
}
