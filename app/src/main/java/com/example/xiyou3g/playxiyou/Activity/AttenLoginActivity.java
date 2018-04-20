package com.example.xiyou3g.playxiyou.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
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
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttenLogin;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendCode;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendPer;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.attenCookie;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.attendPerBean;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.islogin;
import static com.example.xiyou3g.playxiyou.Content.EduContent.handler;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginName;

/**
 * Created by Lance
 * on 2017/7/18.
 */

public class AttenLoginActivity extends AppCompatActivity {

    @BindView(R.id.alback)
    ImageView alBack;

    @BindView(R.id.aname)
    TextView aname;

    @BindView(R.id.apass)
    TextView apass;

    @BindView(R.id.acode)
    TextView acheck;

    @BindView(R.id.acodeimage)
    ImageView acodeImage;

    @BindView(R.id.alogin)
    Button alogin;

    @BindView(R.id.ahelp)
    Button ahelp;

    @BindView(R.id.aback)
    ImageView aback;

    @BindView(R.id.remember)
    CheckBox remember;

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
        ButterKnife.bind(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initWight();
    }

    private void initWight() {
        boolean isRemember = pref.getBoolean("isremember",false);
        if(isRemember){
            aname.setText(pref.getString("name",loginName));
            apass.setText(pref.getString("pass",""));
            remember.setChecked(true);
        }else{
            aname.setText(loginName);
        }
        new Thread(new GetAttendCode(acodeImage)).start();
    }

    @OnClick(R.id.alogin)
    void alogin() {
        String name = aname.getText().toString();
        String pass = apass.getText().toString();
        String code = acheck.getText().toString();

        try {
            GetAttenLogin.sendOKHttpRequest(name, pass, code, attenCookie, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtils.INSTANCE.e("stten faliure",e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
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
                                    attendPerBean.setName(dl.get(0).text());
                                    attendPerBean.setSex(dl.get(1).text());
                                    attendPerBean.setNum(dl.get(2).text());
                                    attendPerBean.setAcademy(dl.get(3).text());
                                    attendPerBean.setPhone(dl.get(4).text());
                                    attendPerBean.setMajor(dl.get(5).text());
                                    attendPerBean.setClassroom(dl.get(6).text());
                                    attendPerBean.setIdentity(dl.get(7).text());
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
    }

    @OnClick(R.id.ahelp)
    void ahelp() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.help_dialog, null);
        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.help_recyc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> helpList = new ArrayList<>();
        String[] array = new String[]{"功能：教室情况查询、个人考勤情况查询、个人信息查询"
                ,"智慧教室官网：\nhttp://jwkq.xupt.edu.cn:8080/"
                ,"PS：因为智慧教室的数据在假期期间被清空了，所以小猿捕获不到数据，因此里面暂无内容显示，开学后将进一步跟进智慧教室的开发~"};
        Collections.addAll(helpList, array);
        HelpAdapter helpAdapter = new HelpAdapter(helpList);
        recyclerView.setAdapter(helpAdapter);
        bottomSheetDialog.setContentView(dialogView);
        bottomSheetDialog.show();
    }

    @OnClick(R.id.acodeimage)
    void acodeImage() {
        new Thread(new GetAttendCode(acodeImage)).start();
    }

    @OnClick(R.id.aback)
    void aback() {
        finish();
    }
}
