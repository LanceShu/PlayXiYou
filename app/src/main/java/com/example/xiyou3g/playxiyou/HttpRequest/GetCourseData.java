package com.example.xiyou3g.playxiyou.HttpRequest;

import android.content.SharedPreferences;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.DataBean.CourseBean;
import com.example.xiyou3g.playxiyou.Utils.HandleCourseData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/13.
 */

public class GetCourseData {

    private int startYear;
    private int endYear;
    private int team;
    private int isBegin;

    public GetCourseData(int startYear,int endYear,int team,int isBegin){
        this.startYear = startYear;
        this.endYear = endYear;
        this.team = team;
        this.isBegin = isBegin;
        getCourseTable();
    }

    public GetCourseData(int year,int month,int isBegin){
        if(month >= 9){
            startYear = year;
            endYear = year +1;
            team = 1;
        }
        if(month < 3){
            startYear = year -1;
            endYear = year;
            team = 1;
        }
        if(month >= 3 || month <9){
            startYear = year -1;
            endYear = year;
            team = 2;
        }
        this.isBegin = isBegin;
        getCourseTable();
    }


    private void getCourseTable() {
        synchronized (this){
            courseList.clear();
            final String[] aaa = new String[1];
            String url ="http://222.24.62.120/xskbcx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121603";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Document document3 = Jsoup.parse(s);
                    aaa[0] = document3.select("input[name=__VIEWSTATE]").val();
                    try {
                        aaa[0] = URLEncoder.encode(aaa[0],"GBK");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Cookie",cookies);
                    header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    header.put("Referer","http://222.24.62.120/xskbcx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121603");
                    header.put("Accept-Encoding","gzip, deflate");
                    header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                    header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                    return header;
                }
            };
            mqueue.add(stringRequest);

            if(isBegin == 1){
                StringRequest request = new StringRequest(Request.Method.GET,url , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Message message = Message.obtain();
                        message.what = COURSE_CACHE;
                        message.obj = s;
                        handler.sendMessage(message);
                        HandleCourseData.handleCourse(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> header = new HashMap<>();
                        header.put("Cookie",cookies);
                        header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                        header.put("Referer","http://222.24.62.120/xskbcx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121603");
                        header.put("Accept-Encoding","gzip, deflate");
                        header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                        return header;
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return ("__EVENTTARGET=xqd&__EVENTARGUMENT=&__VIEWSTATE="+aaa[0]
                                +"&xnd="+startYear+"-"+endYear+"&xqd="+team).getBytes();
                    }
                };
                mqueue.add(request);
            }else{
                StringRequest request = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Message message = Message.obtain();
                        message.what = COURSE_CACHE;
                        message.obj = s;
                        handler.sendMessage(message);
                        HandleCourseData.handleCourse(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> header = new HashMap<>();
                        header.put("Cookie",cookies);
                        header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                        header.put("Referer","http://222.24.62.120/xskbcx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121603");
                        header.put("Accept-Encoding","gzip, deflate");
                        header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                        return header;
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return ("__EVENTTARGET=xqd&__EVENTARGUMENT=&__VIEWSTATE="+aaa[0]
                                +"&xnd="+startYear+"-"+endYear+"&xqd="+team).getBytes();
                    }
                };
                mqueue.add(request);
            }
        }

    }

}
