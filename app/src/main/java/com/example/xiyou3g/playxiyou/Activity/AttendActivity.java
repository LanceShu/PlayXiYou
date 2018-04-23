package com.example.xiyou3g.playxiyou.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.xiyou3g.playxiyou.Adapter.AttendAdapter;
import com.example.xiyou3g.playxiyou.AttendFragment.ACheckFragment;
import com.example.xiyou3g.playxiyou.AttendFragment.AClassFragment;
import com.example.xiyou3g.playxiyou.AttendFragment.APerFragment;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lance
 * on 2017/7/22.
 */

public class AttendActivity extends AppCompatActivity {

    @BindView(R.id.atab)
    TabLayout tabLayout;

    @BindView(R.id.aviewpager)
    ViewPager viewPager;

    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atten_login_fragment);
        ButterKnife.bind(this);
        initWight();
    }

    private void initWight() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AClassFragment aClassFragment = new AClassFragment();
        ACheckFragment aCheckFragment = new ACheckFragment();
        APerFragment aPerFragment = new APerFragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(aClassFragment);
        fragmentList.add(aCheckFragment);
        fragmentList.add(aPerFragment);

        List<String> tabList = new ArrayList<>();
        tabList.add("教室情况");
        tabList.add("考勤信息");
        tabList.add("基本信息");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

        AttendAdapter attendAdapter = new AttendAdapter(getSupportFragmentManager(), fragmentList, tabList);

        viewPager.setAdapter(attendAdapter);
        tabLayout.setupWithViewPager(viewPager);
        replaceFrament(new AClassFragment());
    }

    private void replaceFrament(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.aviewpager,fragment);
        transaction.commit();
    }
}
