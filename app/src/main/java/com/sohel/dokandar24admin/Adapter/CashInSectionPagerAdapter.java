package com.sohel.dokandar24admin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sohel.dokandar24admin.Fragments.AdminCashInHistoryFragment;
import com.sohel.dokandar24admin.Fragments.AdminProfileFragment;
import com.sohel.dokandar24admin.Fragments.PaidCashInFragment;
import com.sohel.dokandar24admin.Fragments.PendingCashInFragment;

public class CashInSectionPagerAdapter extends FragmentPagerAdapter {
    public CashInSectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:{
                return new PendingCashInFragment();
            }
            case 1: {
               return new PaidCashInFragment();
            }
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return  "Pending";
            case 1:
                return  "Paid";
            default:
                 return null;

        }
    }

}
