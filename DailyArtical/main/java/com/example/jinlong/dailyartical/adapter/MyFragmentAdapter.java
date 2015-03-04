package com.example.jinlong.dailyartical.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JinLong on 2015/2/8.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int index) {

        return fragments.get(index);
    }

    @Override
    public int getCount() {

        return fragments.size();
    }
}

