package com.sohel.moneytransection.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sohel.moneytransection.Fragments.CashInFragments;
import com.sohel.moneytransection.Fragments.PendingCashInFragments;
import com.sohel.moneytransection.Fragments.SuccessCashInFragments;

public class CashoutSectionPagerAdapter extends FragmentPagerAdapter {
    public CashoutSectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:{
                return new CashInFragments();
            }
            case 1: {
               return new PendingCashInFragments();
            }
            case 2: {
                return new SuccessCashInFragments();
            }
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return  "Home";
            case 1:
                return  "Pending";
            case 2:
                return  "Success";
             default:
                 return null;

        }
    }

}
