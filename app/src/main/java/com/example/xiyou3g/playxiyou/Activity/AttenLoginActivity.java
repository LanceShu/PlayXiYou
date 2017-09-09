package com.example.xiyou3g.playxiyou.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.Adapter.HelpAdapter;
import com.example.xiyou3g.playxiyou.AttendFragment.AttendLogFragment;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttenLogin;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendCode;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendClass;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendPer;
import com.example.xiyou3g.playxiyou.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance on 2017/7/18.
 */

public class AttenLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView alBack;
    private TextView aname;
    private TextView apass;
    private TextView acheck;
    private ImageView acodeImage;
    private Button alogin;
    private Button ahelp;
    private ImageView aback;

    private CheckBox remember;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Slide slide = new Slide();
        slide.setDuration(300);
        getWindow().setEnterTransition(slide);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.attenlogin_activity);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initWight();

    }

    private void initWight() {
        alBack = (ImageView) findViewById(R.id.alback);
        aname = (TextView) findViewById(R.id.aname);
        apass = (TextView) findViewById(R.id.apass);
        acheck = (TextView) findViewById(R.id.acode);
        acodeImage = (ImageView)findViewById(R.id.acodeimage);
        alogin = (Button)findViewById(R.id.alogin);
        ahelp = (Button)findViewById(R.id.ahelp);
        aback = (ImageView) findViewById(R.id.aback);
        remember = (CheckBox) findViewById(R.id.remember);

        boolean isRemember = pref.getBoolean("isremember",false);
        if(isRemember){
            aname.setText(pref.getString("name",loginName));
            apass.setText(pref.getString("pass",""));
            remember.setChecked(true);
        }else{
            aname.setText(loginName);
        }

        new Thread(new GetAttendCode(acodeImage)).start();

        acodeImage.setOnClickListener(this);
        alogin.setOnClickListener(this);
        ahelp.setOnClickListener(this);
        aback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alogin:
                String name = aname.getText().toString();
                String pass = apass.getText().toString();
                String code = acheck.getText().toString();

                try {
                    GetAttenLogin.sendOKHttpRequest(name, pass, code, attenCookie, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("stten faliure",e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
//                            Log.e("atten success",response.body().string());
                            String res = response.body().string();
                            Log.e("atten success",res);
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                if(jsonObject.getBoolean("IsSucceed")){

                                    islogin = 1;
                                    Log.e("attend cookie",attenCookie);
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("Obj");
                                    Log.e("attend obj",jsonObject1+"");
                                    Snackbar.make(alogin,"登录成功!",Snackbar.LENGTH_SHORT).show();
                                    editor = pref.edit();
                                    if(remember.isChecked()){
                                        editor.putBoolean("isremember",true);
                                        editor.putString("name",aname.getText().toString());
                                        editor.putString("pass",apass.getText().toString());
                                    }else{
                                        editor.clear();
                                    }
                                    editor.apply();
//                                    Message message = new Message();
//                                    message.what = 11;
//                                    attenHandler.sendMessage(message);

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(AttenLoginActivity.this,AttendActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },500);

                                    GetAttendPer.getAttendPer(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.e("attend per faliure",e.toString());
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String body = response.body().string();
                                            Document document = Jsoup.parse(body);
                                            Elements dl = document.getElementsByTag("dd");
//                                            Log.e("attend per success",dl.toString());
                                            attendPerBean.setName(dl.get(0).text());
                                            attendPerBean.setSex(dl.get(1).text());
                                            attendPerBean.setNum(dl.get(2).text());
                                            attendPerBean.setAcademy(dl.get(3).text());
                                            attendPerBean.setPhone(dl.get(4).text());
                                            attendPerBean.setMajor(dl.get(5).text());
                                            attendPerBean.setClassroom(dl.get(6).text());
                                            attendPerBean.setIdentity(dl.get(7).text());
                                            Log.e("attend attendBean success",attendPerBean.getName());
                                        }
                                    });

                                }else{
                                    String Msg = jsonObject.getString("Msg");
                                    if(Msg.equals("用户名或密码错误")){
                                        Snackbar.make(alogin,"用户名或密码错误!",Snackbar.LENGTH_SHORT).show();
                                    }else{
                                        Snackbar.make(alogin,"验证码输入错误!",Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.ahelp:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                View dialogView = LayoutInflater.from(this).inflate(R.layout.help_dialog,null);
                RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.help_recyc);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                List<String> helpList = new ArrayList<>();
                String[] array = new String[]{"功能：教室情况查询、个人考勤情况查询、个人信息查询"
                        ,"智慧教室官网：\nhttp://jwkq.xupt.edu.cn:8080/"
                        ,"PS：因为智慧教室的数据在假期期间被清空了，所以小猿捕获不到数据，因此里面暂无内容显示，开学后将进一步跟进智慧教室的开发~"};
                for(int i =0;i<array.length;i++){
                    helpList.add(array[i]);
                }
                HelpAdapter helpAdapter = new HelpAdapter(helpList);
                recyclerView.setAdapter(helpAdapter);
                bottomSheetDialog.setContentView(dialogView);
                bottomSheetDialog.show();
                break;
            case R.id.acodeimage:
                new Thread(new GetAttendCode(acodeImage)).start();
                break;
            case R.id.aback:
                finish();
                break;
        }
    }
}
