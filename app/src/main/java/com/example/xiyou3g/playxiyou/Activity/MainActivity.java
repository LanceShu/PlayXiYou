package com.example.xiyou3g.playxiyou.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.xiyou3g.playxiyou.AttendFragment.AttendUnlogFragment;
import com.example.xiyou3g.playxiyou.Content.EduContent;
import com.example.xiyou3g.playxiyou.EduFragment.EduFragment;
import com.example.xiyou3g.playxiyou.HttpRequest.GetScoreData;
import com.example.xiyou3g.playxiyou.MeFragment.MeFragment;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleScoreData;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.xiyou3g.playxiyou.Content.EduContent.SCORE_CACHE;
import static com.example.xiyou3g.playxiyou.Content.EduContent.Team;
import static com.example.xiyou3g.playxiyou.Content.EduContent.ViewStatelist;
import static com.example.xiyou3g.playxiyou.Content.EduContent.Year;
import static com.example.xiyou3g.playxiyou.Content.EduContent.cookies;
import static com.example.xiyou3g.playxiyou.Content.EduContent.courseList;
import static com.example.xiyou3g.playxiyou.Content.EduContent.currentScore;
import static com.example.xiyou3g.playxiyou.Content.EduContent.fragmentManager;
import static com.example.xiyou3g.playxiyou.Content.EduContent.isCache;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginName;
import static com.example.xiyou3g.playxiyou.Content.EduContent.mqueue;
import static com.example.xiyou3g.playxiyou.Content.EduContent.popupWindow;
import static com.example.xiyou3g.playxiyou.Content.EduContent.scoreInfos;
import static com.example.xiyou3g.playxiyou.Content.EduContent.student_name;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuname;

/**
 * Created by Lance
 * on 2017/7/12.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private BottomNavigationBar bottomNavigationBar;

    private SharedPreferences preferences;

    private MainHandler mainHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        initWight();

        getCurrentYearAndTeam();                                              //获取当前的学年与学期;
        ViewStatelist = getVisState(ViewStatelist);                           //获取培养计划的头_VISTSTE;
        getScoreData();

    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onResume() {
        super.onResume();
        mainHandler = new MainHandler(this, preferences.edit());
    }

    static class MainHandler extends Handler {
        private WeakReference<MainActivity> activity;
        private WeakReference<SharedPreferences.Editor> editor;

        MainHandler (MainActivity activity, SharedPreferences.Editor editor) {
            this.activity = new WeakReference<>(activity);
            this.editor = new WeakReference<>(editor);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = activity.get();
            SharedPreferences.Editor mainEditor = editor.get();
            if (mainActivity != null && mainEditor != null) {
                switch (msg.what){
                    case SCORE_CACHE:
                        mainEditor.putString("CacheScore", String.valueOf(msg.obj));
                        mainEditor.apply();
                        break;
                }
            }
        }
    }

    private void getScoreData() {
        if(isCache){
            String s = PreferenceManager.getDefaultSharedPreferences(this).getString("CacheScore","null");
            HandleScoreData.handleScore(s);
        }else{
            new GetScoreData(Year,Team);                                //获取成绩信息;
        }
    }

    private List<String> getVisState(final List<String> list) {
        list.clear();
        for(int i = 0; i < 3; i++){
            String url = "http://222.24.62.120/pyjh.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121607";
            final String[] __viewstate = new String[1];
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    //repsone_content = s;
                    Document document1 = Jsoup.parse(s);
                    __viewstate[0] = document1.select("input[name=__VIEWSTATE]").val();
                    try {
                        __viewstate[0] = URLEncoder.encode(__viewstate[0],"GBK");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.e("viewstate      ",__viewstate[0]);
                    list.add(__viewstate[0]);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    LogUtils.INSTANCE.e("VolleyError",volleyError+"");
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Cookie",cookies);
                    header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    header.put("Referer","http://222.24.62.120/pyjh.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121607");
                    header.put("Accept-Encoding","gzip, deflate");
                    header.put("Accept-Language", "zh-Hans-CN,zh-Hans,zh;q=0.8");
                    header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                    return header;
                }

            };
            mqueue.add(stringRequest1);
        }
        return list;
    }

    private void getCurrentYearAndTeam() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        int team;
        int startYear;
        int endYear;
        if(month >= 7 && day >= 15){
            startYear = year -1;
            endYear = year;
            team = 2;
        }else{
            startYear = year - 1;
            endYear = year;
            team = 1;
        }
        Year = startYear + "-" + endYear;
        Team = team + "";
        currentScore = Year + "   第" + Team + "学期";
    }

    private void initWight() {
        if(stuname == null || stuname.equals("null")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.information_tips);
            builder.setMessage("您还未进行教师评价，因此暂无内容展示，请您先到官网进行教师评价，谢谢！");
            builder.setPositiveButton("好的",null).create().show();
        }

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.main_bottom);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.jiaowu,"教务处"))
//                .addItem(new BottomNavigationItem(R.mipmap.tiyu,"体育部"))
                .addItem(new BottomNavigationItem(R.mipmap.kaoqin,"智慧考勤"))
                .addItem(new BottomNavigationItem(R.mipmap.me,"个人信息"))
                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.initialise();
        bottomNavigationBar.setPadding(0,10,0,0);
        bottomNavigationBar.setTabSelectedListener(this);
        addFragment(new EduFragment());
    }

    public void replaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_container,fragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                replaceFragment(new EduFragment());
                break;
//            case 1:
//                replaceFragment(new SportFragment());
//                break;
            case 1:
//                if(islogin == 0){
                    replaceFragment(new AttendUnlogFragment());
//                }else{
//                    replaceFragment(new AttendLogFragment());
//                }
                break;
            case 2:
                replaceFragment(new MeFragment());
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exitClick();
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitClick() {
        Timer timer;
        if(!isExit){
            isExit = true;
            Snackbar.make(bottomNavigationBar, R.string.exit_click, Snackbar.LENGTH_SHORT).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
        }else{
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("onTouch", "touch1");
        if(popupWindow != null && popupWindow.isShowing()){
            Log.e("onTouch", "touch2");
            popupWindow.dismiss();
            popupWindow = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHandler.removeCallbacksAndMessages(0);
        if (courseList.size() != 0 || scoreInfos.size() != 0) {
            EduContent.courseList.clear();
            EduContent.scoreInfos.clear();
        }
    }
}
