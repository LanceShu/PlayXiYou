package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance
 * on 2017/7/20.
 */

public class AttendAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabList = new ArrayList<>();

    public AttendAdapter(FragmentManager fm,List<Fragment> fragments,List<String> tabList) {
        super(fm);
        fragmentList = fragments;
        this.tabList = tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position % tabList.size());
    }
}
