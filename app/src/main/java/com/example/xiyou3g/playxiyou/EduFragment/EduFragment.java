package com.example.xiyou3g.playxiyou.EduFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.playxiyou.Adapter.EduAdapter;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lance
 * on 2017/7/12.
 */

public class EduFragment extends Fragment{
    @BindView(R.id.tab)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edu_fragment,container,false);
        ButterKnife.bind(this, view);
        initWight();
        return view;
    }

    private void initWight() {
        //课程表的Fragment;
        CourseFragment courseFragment = new CourseFragment();
        //成绩查询的Fragment；
        ScoreFragment scoreFragment = new ScoreFragment();
        //培养计划的Fragment;
//        ProjectFragment projectFragment = new ProjectFragment();
        //学分统计的Fragment;
//        MajorFragment majorFragment = new MajorFragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(courseFragment);
        fragmentList.add(scoreFragment);
//        fragmentList.add(projectFragment);
//        fragmentList.add(majorFragment);

        List<String> tabList = new ArrayList<>();
        tabList.add("课程表");
        tabList.add("成绩查询");
//        tabList.add("培养计划");
//        tabList.add("学分统计");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
//        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(3)));

        EduAdapter eduAdapter = new EduAdapter(getChildFragmentManager(),fragmentList,tabList);
        viewPager.setAdapter(eduAdapter);
        tabLayout.setupWithViewPager(viewPager);

        replaceFragment(new CourseFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.viewpager,fragment);
        transaction.commit();
    }

}
