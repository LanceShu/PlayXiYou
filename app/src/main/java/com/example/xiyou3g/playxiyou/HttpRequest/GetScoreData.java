package com.example.xiyou3g.playxiyou.HttpRequest;

import android.os.Message;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.DataBean.ScoreBean;
import com.example.xiyou3g.playxiyou.Utils.HandleScoreData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/15.
 */

public class GetScoreData{

    private String year;
    private String team;

    public GetScoreData(String year,String team){
        this.year = year;
        this.team = team;
        get_Score();
    }

    private void get_Score(){
       synchronized (this){

           scoreBeanList.clear();
           final String[] __viewstate = new String[1];
           final String url = "http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121605";
           StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String s) {
                   Document document1 = Jsoup.parse(s);
                   __viewstate[0] = document1.select("input[name=__VIEWSTATE]").val();
                   try {
                       __viewstate[0] = URLEncoder.encode(__viewstate[0],"GBK");
                   } catch (UnsupportedEncodingException e) {

                   }
                   Log.e("viewstate",__viewstate[0]);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                   Log.e("failure",volleyError+"");
               }
           }){
               @Override
               public Map<String, String> getHeaders() throws AuthFailureError {
                   Map<String,String> header = new HashMap<>();
                   header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                   header.put("Accept-Encoding","gzip, deflate");
                   header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                   header.put("Referer","http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605");
                   header.put("Cookie",cookies);
                   header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                   return header;
               }

           };
           mqueue.add(stringRequest1);

           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String s) {
                           Message msg = Message.obtain();
                           msg.what = SCORE_CACHE;
                           msg.obj = s;
                           handler.sendMessage(msg);
                           Log.e("mainactivitycache3",msg.what+"");
                           HandleScoreData.handleScore(s);
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError volleyError) {
                           Log.e("failure",""+volleyError);
                       }
                   }){
                       @Override
                       public Map<String, String> getHeaders() throws AuthFailureError {
                           Map<String,String> header = new HashMap<>();
                           header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                           header.put("Accept-Encoding","gzip, deflate");
                           header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                           header.put("Referer","http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605");
                           header.put("Cookie",cookies);
                           header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                           return header;
                       }

                       @Override
                       public byte[] getBody() throws AuthFailureError {
                           Log.e("year and team=",year+"555"+team);
                           String body = "__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE="+__viewstate[0]+"&hidLanguage=&ddlXN="+year+"&ddlXQ="+team+"&ddl_kcxz=&btn_xq=%D1%A7%C6%DA%B3%C9%BC%A8";
                           return body.getBytes();
                       }
                   };
                   mqueue.add(stringRequest);
               }
           },500);
       }
    }
}
