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
import com.example.xiyou3g.playxiyou.HttpRequest.GetScoreData;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Lance on 2017/7/12.
 */

public class EduFragment extends Fragment{

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EduAdapter eduAdapter;

    private List<Fragment> fragmentList;
    private List<String> tabList;

    private CourseFragment courseFragment;
    private ScoreFragment scoreFragment;
    private ProjectFragment projectFragment;
    private MajorFragment majorFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.edu_fragment,container,false);
        initWight(view);
        return view;
    }

    private void initWight(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        courseFragment = new CourseFragment();
        scoreFragment = new ScoreFragment();
        projectFragment = new ProjectFragment();
        majorFragment = new MajorFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(courseFragment);
        fragmentList.add(scoreFragment);
        fragmentList.add(projectFragment);
        fragmentList.add(majorFragment);

        tabList = new ArrayList<>();
        tabList.add("课程表");
        tabList.add("成绩查询");
        tabList.add("培养计划");
        tabList.add("学分统计");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(3)));

        eduAdapter = new EduAdapter(getChildFragmentManager(),fragmentList,tabList);
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
