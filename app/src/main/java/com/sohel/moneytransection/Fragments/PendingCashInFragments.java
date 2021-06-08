package com.sohel.moneytransection.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohel.moneytransection.R;
public class PendingCashInFragments extends Fragment {

    public PendingCashInFragments() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pending_cash_in_fragments, container, false);

        return view;
    }
}