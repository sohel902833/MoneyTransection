package com.sohel.moneytransection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.sohel.moneytransection.Adapter.CashoutSectionPagerAdapter;

public class CashInActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager mviewPager;
    private CashoutSectionPagerAdapter sectionPagerAdapter;
    private TabLayout mTablayot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in);

        toolbar=findViewById(R.id.appBarId);
        setSupportActionBar(toolbar);
        this.setTitle("Cash In.");


        mviewPager=findViewById(R.id.adminTabpagerid);
        sectionPagerAdapter=new CashoutSectionPagerAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(sectionPagerAdapter);
        mTablayot=findViewById(R.id.admin_tabId);
        mTablayot.setupWithViewPager(mviewPager);

    }
}