package com.example.lemonbily.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PalPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public PalPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int i) {
        if (fragmentList != null) {
            return fragmentList.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        }
        return 0;
    }
}
