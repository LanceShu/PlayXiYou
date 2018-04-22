package com.example.xiyou3g.playxiyou.AttendFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.playxiyou.Adapter.AttendAdapter;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance
 * on 2017/7/19.
 */

public class AttendLogFragment extends Fragment {

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AttendAdapter attendAdapter;

    private List<Fragment> fragmentList;
    private List<String> tabList;

    private AClassFragment aClassFragment;
    private ACheckFragment aCheckFragment;
    private APerFragment aPerFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.atten_login_fragment,container,false);
        initWight(view);
        attenHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 12:
                        Log.e("exitsuccess","attend");
                        break;
                }
            }
        };
        return view;
    }

    private void replace(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container,fragment);
        fragmentTransaction.commit();

    }

    private void initWight(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.atab);
        viewPager = (ViewPager) view.findViewById(R.id.aviewpager);

        aClassFragment = new AClassFragment();
        aCheckFragment = new ACheckFragment();
        aPerFragment = new APerFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(aClassFragment);
        fragmentList.add(aCheckFragment);
        fragmentList.add(aPerFragment);

        tabList = new ArrayList<>();
        tabList.add("教室情况");
        tabList.add("考勤信息");
        tabList.add("基本信息");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

        attendAdapter = new AttendAdapter(getActivity().getSupportFragmentManager(),fragmentList,tabList);

        viewPager.setAdapter(attendAdapter);
        tabLayout.setupWithViewPager(viewPager);
        replaceFragment(new AClassFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.aviewpager,fragment);
        transaction.commit();
    }
}
