package com.sohel.moneytransection.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sohel.moneytransection.Model.CashInModel;
import com.sohel.moneytransection.Model.UserModel;
import com.sohel.moneytransection.R;
public class CashInFragments extends Fragment {
    public CashInFragments() {
    }

    private Button cashInButton;
    private EditText amountEt;
    private UserModel currentUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cash_in_fragments, container, false);
        cashInButton=view.findViewById(R.id.cashin_Button);
        amountEt=view.findViewById(R.id.amountEt);

        cashInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=amountEt.getText().toString();

               /* CashInModel cashInModel=new CashInModel(
                        String.valueOf(System.currentTimeMillis()),
                )*/




            }
        });



        return view;
    }
}