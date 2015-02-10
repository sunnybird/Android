package com.example.jinlong.dailyartical.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JinLong on 2015/2/8.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragmentlist;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentlist) {
        super(fm);
        this.fragmentlist = fragmentlist;

    }

    @Override
    public Fragment getItem(int index) {

        return fragmentlist.get(index);
    }

    @Override
    public int getCount() {

        return fragmentlist.size();
    }
}

