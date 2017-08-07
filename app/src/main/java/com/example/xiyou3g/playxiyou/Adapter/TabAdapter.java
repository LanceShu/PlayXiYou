package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Lance on 2017/7/12.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> tabList;

    public TabAdapter(FragmentManager fm,List<Fragment> fragments,List<String> tabs) {
        super(fm);
        fragmentList = fragments;
        tabList = tabs;
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
