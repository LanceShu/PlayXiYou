package com.example.xiyou3g.playxiyou.HttpRequest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/14.
 */

public class GetPerInfo implements Runnable {
    @Override
    public void run() {
        getPersonInfo();
    }

    private void getPersonInfo() {
       synchronized (this){
           final String url = "http://222.24.62.120/xsgrxx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121501";
           StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
               @Override
               public void onResponse(String s) {
                   Log.e("noteacher",s.contains("你还没有进行本学期的课堂教学质量评价")+"");
                   if(!s.contains("你还没有进行本学期的课堂教学质量评价")){
                       Document document = Jsoup.parse(s);
                       stuname = document.getElementById("xm").text();
                       stuid = document.getElementById("xh").text();
                       stuacademy = document.getElementById("lbl_xy").text();
                       stumajor = document.getElementById("lbl_zymc").text();
                       stuclass = document.getElementById("lbl_xzb").text();
                       stueducation = document.getElementById("lbl_CC").text();
                       stuSex = document.getElementById("lbl_xb").text();
                       stuYear = document.getElementById("lbl_dqszj").text();
                       Log.e("person2222222",stuname+"--"+stuid+"--"+stuacademy+"--"+stumajor+"--"+stuclass+"--"+stueducation);
                   }else{
                       stuname = "null";
                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                   Log.e("pererror333333",volleyError+"");
               }
           }){
               @Override
               protected Response<String> parseNetworkResponse(NetworkResponse response) {
                   Log.e("Person state:",response.statusCode+"");
                   return super.parseNetworkResponse(response);
               }

               @Override
               public Map<String, String> getHeaders() throws AuthFailureError {
                   Map<String,String> header = new HashMap<>();
                   header.put("Cookie",cookies);
                   header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                   header.put("Referer","http://222.24.62.120/xsgrxx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121501");
                   header.put("Accept-Encoding","gzip, deflate");
                   header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                   header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                   return header;
               }
           };
           mqueue.add(stringRequest);
       }
    }
}
