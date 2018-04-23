package com.example.xiyou3g.playxiyou.Activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xiyou3g.playxiyou.HttpRequest.GetCourseData;
import com.example.xiyou3g.playxiyou.HttpRequest.GetPerInfo;
import com.example.xiyou3g.playxiyou.MeFragment.GuideActivity;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleCourseData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.islogin;
import static com.example.xiyou3g.playxiyou.Content.EduContent.COURSE_CACHE;
import static com.example.xiyou3g.playxiyou.Content.EduContent.cookies;
import static com.example.xiyou3g.playxiyou.Content.EduContent.isCache;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginCheckCode;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginName;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginPassword;
import static com.example.xiyou3g.playxiyou.Content.EduContent.mqueue;
import static com.example.xiyou3g.playxiyou.Content.EduContent.student_name;

/**
 * Created by Lance
 * on 2017/7/12.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.username)
    EditText username;          //账号；

    @BindView(R.id.password)
    EditText password;          //密码；

    @BindView(R.id.codeedit)
    EditText codeCheck;         //验证码；

    @BindView(R.id.userclear)
    ImageView userClear;

    @BindView(R.id.passSee)
    ImageView passSee;

    @BindView(R.id.codeCheck)
    ImageView codeImage;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.tools)
    Button tools;

    @BindView(R.id.remember_pass)
    CheckBox remberPass;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Boolean isCanSee = false;
    private int flag;
    private byte[] imagebytes;
    private String cStuname = "";       //缓存的学生名字;

    private LoginHanlder loginHanlder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        islogin = 0;
        cStuname = "";
        mqueue = Volley.newRequestQueue(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initWight();                    //实例化控件;
        loginHanlder = new LoginHanlder(pref.edit(), this);
    }

    static class LoginHanlder extends Handler {
        private WeakReference<LoginActivity> activity;
        private WeakReference<SharedPreferences.Editor> editor;

        LoginHanlder (SharedPreferences.Editor editor, LoginActivity activity) {
            this.activity = new WeakReference<>(activity);
            this.editor = new WeakReference<>(editor);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity loginActivity = activity.get();
            SharedPreferences.Editor loginEditor = editor.get();
            if (activity != null && loginEditor != null) {
                switch (msg.what){
                    case COURSE_CACHE:
                        loginEditor.putString("EduCoures", String.valueOf(msg.obj));
                        loginEditor.apply();
                        break;
                }
            }
        }
    }

    private void initWight() {
        codeCheck.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        boolean isRemem = pref.getBoolean("remember",false);
        if(isRemem){
            username.setText(pref.getString("username",""));
            password.setText(pref.getString("password",""));
            remberPass.setChecked(true);
            cStuname = pref.getString("username","");
        }
        initCodeImage();
    }

    @OnClick(R.id.userclear)
    void userClear() {
        username.setText("");
        password.setText("");
    }

    @OnClick(R.id.passSee)
    void passSee() {
        if(isCanSee){
            passSee.setImageResource(R.mipmap.eye_cant_see);
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }else{
            passSee.setImageResource(R.mipmap.eyes_can_see);
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isCanSee = !isCanSee;
    }

    @OnClick(R.id.login)
    void login() {
//        if(cStuname.equals(username.getText().toString())){
//            isCache = true;
//        }else{
//            isCache = false;
//        }

        final String url = "http://222.24.62.120/default2.aspx";
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.information_tips);

        loginName = username.getText().toString();
        loginPassword = password.getText().toString();
        loginCheckCode = codeCheck.getText().toString();

        Log.e("information:", loginName + "-" + loginPassword + "-" + loginCheckCode);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(flag == 1){
                    Document document1 = Jsoup.parse(s);
                    student_name = document1.getElementById("xhxm").text();
                    student_name = student_name.substring(0, student_name.length() - 2);
                    Log.e("student_name:", student_name);

                    new GetPerInfo();                           //获取个人信息;
                    getCurrentCourse();                         //获取当前课表;

                    builder.setMessage("欢迎您，"+student_name+"同学！");
                    builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editor = pref.edit();
                            Log.e("checkbox state",remberPass.isChecked()+"");
                            if(remberPass.isChecked()){
                                editor.putBoolean("remember",true);
                                editor.putString("username",username.getText().toString());
                                editor.putString("password",password.getText().toString());
                            }else{
                                editor.clear();
                            }
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).create().show();
                } else {
                    builder.setMessage(R.string.login_failure);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            initCodeImage();
                        }
                    }).create().show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                builder.setMessage("请检查网络连接");
                builder.setPositiveButton("确认",null).create().show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header = new HashMap<>();
                header.put("Cookie",cookies);
                header.put("Referer","http://222.24.62.120/");
                header.put("Pragma","no-cache");
                header.put("Accept-Encoding","gzip, deflate");
                header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                Log.e("cookie",cookies+"1111111");
                return header;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return ("__VIEWSTATE=dDwxNTMxMDk5Mzc0Ozs%2BlYSKnsl%2FmKGQ7CKkWFJpv0btUa8%3D&txtUserName=" +loginName
                        +"&Textbox1=&TextBox2=" +loginPassword
                        +"&txtSecretCode=" +loginCheckCode
                        +"&RadioButtonList1=%D1%A7%C9%FA&Button1=&lbLanguage=&hidPdrs=&hidsc=").getBytes();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                System.out.println(response.headers);
                Log.e("statusCode",response.statusCode+"   length="+response.data.length);
                if(response.data.length > 7000){
                    flag = 1;
                }
                return super.parseNetworkResponse(response);
            }
        };
        mqueue.add(request);
    }

    @OnClick(R.id.tools)
    void tools() {
        PopupMenu popupMenu = new PopupMenu(this,tools);
        popupMenu.getMenuInflater().inflate(R.menu.login_tools,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.attend:
                        Intent attIntent = new Intent(LoginActivity.this,AttenLoginActivity.class);
                        startActivity(attIntent,ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                        break;
                    case R.id.siliu:
                        Intent intent1 = new Intent(LoginActivity.this, SiliuActivitySe.class);
                        startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                        break;
                    case R.id.guide:
                        Intent intent = new Intent(LoginActivity.this,GuideActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    @OnClick(R.id.codeCheck)
    void codeCheck() {
        initCodeImage();
    }

    private void initCodeImage() {
        final String checkCodeImageUrl = "http://222.24.62.120/CheckCode.aspx";

        StringRequest getCheckCode = new StringRequest(Request.Method.GET, checkCodeImageUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
                codeImage.setImageBitmap(bitmap);
                //Log.e("qwe", "success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                codeImage.setImageResource(R.mipmap.codeerror);
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                imagebytes = response.data;
                Log.e("response_headers", response.headers + "");
                cookies = response.headers.get("Set-Cookie");
                String dataString;
                try{
                    dataString = new String(response.data,"UTF-8");
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        mqueue.add(getCheckCode);
    }

    private void getCurrentCourse() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(isCache){
            String s = preferences.getString("EduCoures","null");
            HandleCourseData.handleCourse(s);
        }else{
            new GetCourseData(year,month,1);
        }

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
            Snackbar.make(login,R.string.exit_click, Snackbar.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        loginHanlder.removeCallbacksAndMessages(0);
    }
}
