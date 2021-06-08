package com.sohel.dokandar24admin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sohel.dokandar24admin.Fragments.AdminCashInHistoryFragment;
import com.sohel.dokandar24admin.Fragments.AdminProfileFragment;

public class AdminProfileSectionPagerAdapter extends FragmentPagerAdapter {
    public AdminProfileSectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:{
                return new AdminProfileFragment();
            }
            case 1: {
               return new AdminCashInHistoryFragment();
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
                return  "Home";
            case 1:
                return  "History";
            default:
                 return null;

        }
    }

}
