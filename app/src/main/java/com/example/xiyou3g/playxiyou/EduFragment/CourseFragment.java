package com.example.xiyou3g.playxiyou.EduFragment;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.HttpRequest.GetCourseData;
import com.example.xiyou3g.playxiyou.R;

import java.util.Calendar;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/12.
 */

public class CourseFragment extends Fragment implements View.OnClickListener{

    private View view;
    private FrameLayout container;
    private TextView month;
    private TextView day1;
    private TextView day2;
    private TextView day3;
    private TextView day4;
    private TextView day5;
    private TextView day6;
    private TextView day7;
    private TextView isData;

    private boolean isOpenFab = false;
    private FloatingActionButton fab;
    private TextView team1;
    private TextView team2;
    private int startYear1;
    private int endYear1;
    private int iteam1;
    private int startYear2;
    private int endYear2;
    private int iteam2;

    private int width;
    private int height;
    private int screenWidth;
    private int screenHeight;
    private float density;
    private  ProgressDialog dialog;

    private int color;
    private final int[] item = {R.drawable.course_item_1,R.drawable.course_item_2,R.drawable.course_item_3,
            R.drawable.course_item_4,R.drawable.course_item_5,R.drawable.course_item_6,R.drawable.course_item_7,
            R.drawable.course_item_8,R.drawable.course_item_9,R.drawable.course_item_10};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;                 //屏幕宽度像素；
        height = dm.heightPixels;               //屏幕高度像素;
        density = dm.density;                   //屏幕密度;
        int densityDpi = dm.densityDpi;
        screenWidth = (int) (width/density);
        screenHeight = (int) (height/density);
        Log.e("screen=======",screenWidth+"   "+screenHeight);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.course_fragment,container,false);

        initWight(view);
        return view;
    }

    private void initWight(View view) {
        container = (FrameLayout) view.findViewById(R.id.course_container);
        month = (TextView) view.findViewById(R.id.month);
        day1 = (TextView) view.findViewById(R.id.day1);
        day2 = (TextView) view.findViewById(R.id.day2);
        day3 = (TextView) view.findViewById(R.id.day3);
        day4 = (TextView) view.findViewById(R.id.day4);
        day5 = (TextView) view.findViewById(R.id.day5);
        day6 = (TextView) view.findViewById(R.id.day6);
        day7 = (TextView) view.findViewById(R.id.day7);
        fab = (FloatingActionButton) view.findViewById(R.id.fab1);
        team1 = (TextView) view.findViewById(R.id.team1);
        team2 = (TextView) view.findViewById(R.id.team2);
        isData = (TextView) view.findViewById(R.id.course_tv);

        team1.setVisibility(View.GONE);
        team2.setVisibility(View.GONE);

        fab.setOnClickListener(this);
        team1.setOnClickListener(this);
        team2.setOnClickListener(this);

        if(stuname.equals("null")){
            fab.setVisibility(View.GONE);
            isData.setVisibility(View.VISIBLE);
        }else{
            setCalender();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initTeam();
                    setCourse();
                }
            },150);
        }
    }

    private void initTeam() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        if(month>=8 && month <9){
            team1.setText(year+"-"+(year+1)+" 第一学期");
            team2.setText((year-1)+"-"+year+" 第二学期");
            startYear1 = year;
            endYear1 = year+1;
            iteam1 = 1;
            startYear2 = year-1;
            endYear2 = year;
            iteam2 = 2;
        }else if(month >= 9){
            team2.setText(year+"-"+(year+1)+" 第一学期");
            team1.setText((year-1)+"-"+year+" 第二学期");
            startYear2 = year;
            endYear2 = year+1;
            iteam2 = 1;
            startYear1 = year-1;
            endYear1 = year;
            iteam1 = 2;
        }else if(month>=3 && month<8){
            team2.setText((year -1)+"-"+year+" 第二学期");
            team1.setText((year - 1)+"-"+year+" 第一学期");
            startYear2 = year-1;
            endYear2 = year;
            iteam2 = 2;
            startYear1 = year-1;
            endYear1 = year;
            iteam1 = 1;
        }else if(month <3){
            team2.setText((year-1)+"-"+year+" 第一学期");
            team1.setText((year-2)+"-"+(year-1)+" 第二学期");
            startYear2 = year-1;
            endYear2 = year;
            iteam2 = 1;
            startYear1 = year-2;
            endYear1 = year-1;
            iteam1 = 2;
        }
    }

    private void setCourse() {

        int x = 0;
        int y = 0;
        color = 0;
        for(int i=0;i<courseList.size();i++){

            if(courseList.get(i).getcName().equals("")){
                FrameLayout fl = new FrameLayout(getContext());
                int flWidth = (int) ((width - (45 * density))/7);
                FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(flWidth, (int) (100*density));
                flp.setMargins(x*flWidth, (int)(y*100*density),0,0);
                fl.setLayoutParams(flp);

                TextView tv = new TextView(getContext());
                FrameLayout.LayoutParams flp2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setLayoutParams(flp2);
                tv.setText("");
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOpenFab)
                            closeMenu(fab);
                    }
                });
                fl.addView(tv);

                container.addView(fl);
            }else{
                final FrameLayout fl = new FrameLayout(getContext());
                int flWidth = (int) ((width - (45 * density))/7);
                FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(flWidth, (int) (100*density));
                flp.setMargins(x*flWidth, (int)(y*100*density),0,0);
                fl.setLayoutParams(flp);
//                fl.setBackgroundResource(item[(int) ((Math.random()*1000)%7)]);
                fl.setBackgroundResource(item[courseList.get(i).getcColor()%10]);

                TextView tv = new TextView(getContext());
                FrameLayout.LayoutParams flp2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setLayoutParams(flp2);
                tv.setTextColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
                Log.e("current course=====",courseList.get(i).getcName()+" "+courseList.get(i).getcTeacher()+" "+courseList.get(i).getcPlace());
                tv.setText(courseList.get(i).getcName()+"\n"+courseList.get(i).getcTeacher()+"\n"+courseList.get(i).getcPlace());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOpenFab)
                            closeMenu(fab);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(fl,"rotation",0,-30,30,0);
                        animator.setDuration(500);
                        animator.start();
                    }
                });
                fl.addView(tv);
                container.addView(fl);
            }
            x++;
            if(x % 7 == 0){
                y++;
                x = 0;
            }
        }

    }

    private void setCalender() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mon = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e("current :",mon +"--"+day+"--"+week);

        month.setText(mon+"月");
        switch (week){
            case 1:
                day1.setText("一\n"+setDay(year,mon,day-6));
                day2.setText("二\n"+setDay(year,mon,day-5));
                day3.setText("三\n"+setDay(year,mon,day-4));
                day4.setText("四\n"+setDay(year,mon,day-3));
                day5.setText("五\n"+setDay(year,mon,day-2));
                day6.setText("六\n"+setDay(year,mon,day-1));
                day7.setText("日\n"+setDay(year,mon,day));
                day7.setBackgroundResource(R.color.days);
                day7.setTextColor(Color.WHITE);
                break;
            case 2:
                day1.setText("一\n"+setDay(year,mon,day));
                day1.setBackgroundResource(R.color.days);
                day1.setTextColor(Color.WHITE);
                day2.setText("二\n"+setDay(year,mon,day+1));
                day3.setText("三\n"+setDay(year,mon,day+2));
                day4.setText("四\n"+setDay(year,mon,day+3));
                day5.setText("五\n"+setDay(year,mon,day+4));
                day6.setText("六\n"+setDay(year,mon,day+5));
                day7.setText("日\n"+setDay(year,mon,day+6));
                break;
            case 3:
                day1.setText("一\n"+setDay(year,mon,day-1));
                day2.setText("二\n"+setDay(year,mon,day));
                day2.setBackgroundResource(R.color.days);
                day2.setTextColor(Color.WHITE);
                day3.setText("三\n"+setDay(year,mon,day+1));
                day4.setText("四\n"+setDay(year,mon,day+2));
                day5.setText("五\n"+setDay(year,mon,day+3));
                day6.setText("六\n"+setDay(year,mon,day+4));
                day7.setText("日\n"+setDay(year,mon,day+5));
                break;
            case 4:
                day1.setText("一\n"+setDay(year,mon,day-2));
                day2.setText("二\n"+setDay(year,mon,day-1));
                day3.setText("三\n"+setDay(year,mon,day));
                day3.setBackgroundResource(R.color.days);
                day3.setTextColor(Color.WHITE);
                day4.setText("四\n"+setDay(year,mon,day+1));
                day5.setText("五\n"+setDay(year,mon,day+2));
                day6.setText("六\n"+setDay(year,mon,day+3));
                day7.setText("日\n"+setDay(year,mon,day+4));
                break;
            case 5:
                day1.setText("一\n"+setDay(year,mon,day-3));
                day2.setText("二\n"+setDay(year,mon,day-2));
                day3.setText("三\n"+setDay(year,mon,day-1));
                day4.setText("四\n"+setDay(year,mon,day));
                day4.setBackgroundResource(R.color.days);
                day4.setTextColor(Color.WHITE);
                day5.setText("五\n"+setDay(year,mon,day+1));
                day6.setText("六\n"+setDay(year,mon,day+2));
                day7.setText("日\n"+setDay(year,mon,day+3));
                break;
            case 6:
                day1.setText("一\n"+setDay(year,mon,day-4));
                day2.setText("二\n"+setDay(year,mon,day-3));
                day3.setText("三\n"+setDay(year,mon,day-2));
                day4.setText("四\n"+setDay(year,mon,day-1));
                day5.setText("五\n"+setDay(year,mon,day));
                day5.setBackgroundResource(R.color.days);
                day5.setTextColor(Color.WHITE);
                day6.setText("六\n"+setDay(year,mon,day+1));
                day7.setText("日\n"+setDay(year,mon,day+2));
                break;
            case 7:
                day1.setText("一\n"+setDay(year,mon,day-5));
                day2.setText("二\n"+setDay(year,mon,day-4));
                day3.setText("三\n"+setDay(year,mon,day-3));
                day4.setText("四\n"+setDay(year,mon,day-2));
                day5.setText("五\n"+setDay(year,mon,day-1));
                day6.setText("六\n"+setDay(year,mon,day));
                day6.setBackgroundResource(R.color.days);
                day6.setTextColor(Color.WHITE);
                day7.setText("日\n"+setDay(year,mon,day+1));
                break;
        }
    }

    private int setDay(int year,int mon,int day) {
        if(day <= 0){
            switch(mon){
                case 5:
                case 7:
                case 10:
                case 12:
                    return 30+day;
                case 1:
                case 2:
                case 4:
                case 6:
                case 8:
                case 9:
                case 11:
                    return 31+day;
                case 3:
                    if(year % 4 == 0){
                        return 29+day;
                    }else{
                        return 28+day;
                    }
            }
        }else if(day>28 && day<=30){
            switch (mon){
                case 2:
                    if(year % 4 ==0)
                        return day;
                    else
                        return day-28;
                default:
                    return day;
            }
        }else if(day >=31){
            switch (mon){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if(day-31==0)
                        return day;
                    else
                    return day-31;
                case 4:
                case 6:
                case 9:
                case 11:
                    return day-30;
                case 2:
                    if(year % 4 == 0){
                        return day-29;
                    }else{
                        return day-28;
                    }
            }
        }
        return day;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab1:
                if(!isOpenFab){
                    openMenu(v);
                }else{
                    closeMenu(v);
                }
                break;
            case R.id.team1:
                Log.e("click","team1");
                courseList.clear();
                container.removeAllViews();
                new GetCourseData(startYear1,endYear1,iteam1,0);
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("正在努力加载...");
                dialog.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initWight(view);
                        dialog.dismiss();
                    }
                },500);
                break;
            case R.id.team2:
                Log.e("click","team2");
                courseList.clear();
                container.removeAllViews();
                new GetCourseData(startYear2,endYear2,iteam2,1);
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("正在努力加载...");
                dialog.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initWight(view);
                        dialog.dismiss();
                    }
                },500);
                break;
        }
    }

    private void closeMenu(View v) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.9f,0);
        alphaAnimation.setDuration(500);
        team1.startAnimation(alphaAnimation);
        team2.startAnimation(alphaAnimation);
        team1.setVisibility(View.GONE);
        team2.setVisibility(View.GONE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",-135,20,0);
        animator.setDuration(500);
        animator.start();
        isOpenFab = false;
    }

    private void openMenu(View v) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"rotation",0,-155,-135);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        team1.setVisibility(View.VISIBLE);
        team2.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,0.9f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        team1.startAnimation(alphaAnimation);
        team2.startAnimation(alphaAnimation);
        isOpenFab = true;
    }
}
