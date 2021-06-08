package com.sohel.dokandar24admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.sohel.dokandar24admin.Adapter.AdminProfileSectionPagerAdapter;
import com.sohel.dokandar24admin.Adapter.CashInSectionPagerAdapter;

public class CashInRequestActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager mviewPager;
    private CashInSectionPagerAdapter sectionPagerAdapter;
    private TabLayout mTablayot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_request);



        toolbar=findViewById(R.id.appBarId);
        setSupportActionBar(toolbar);
        this.setTitle("Cash In.");

        mviewPager=findViewById(R.id.adminTabpagerid);
        sectionPagerAdapter=new CashInSectionPagerAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(sectionPagerAdapter);
        mTablayot=findViewById(R.id.admin_tabId);
        mTablayot.setupWithViewPager(mviewPager);
    }
}