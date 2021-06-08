package com.sohel.moneytransection.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohel.moneytransection.R;
public class SuccessCashInFragments extends Fragment {
    public SuccessCashInFragments() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_success_cash_in_fragments, container, false);
        return view;
    }
}