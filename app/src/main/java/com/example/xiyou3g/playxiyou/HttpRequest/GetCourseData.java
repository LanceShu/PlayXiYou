package com.example.xiyou3g.playxiyou.HttpRequest;

import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.DataBean.CourseBean;

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

public class GetCourseData implements Runnable {

    private int startYear;
    private int endYear;
    private int team;
    private int isBegin;
    int color;

    public GetCourseData(int startYear,int endYear,int team,int isBegin){
        this.startYear = startYear;
        this.endYear = endYear;
        this.team = team;
        this.isBegin = isBegin;
    }

    public GetCourseData(int year,int month,int isBegin){
        if(month >= 9){
            startYear = year;
            endYear = year +1;
            team = 1;
        }
        if(month <3){
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
    }

    @Override
    public void run() {
        getCourseTable();
    }

    private void getCourseTable() {
        synchronized (this){
            courseList.clear();
            color = -1;
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
                        Document document = Jsoup.parse(s);
                        Elements tr = document.getElementsByTag("tr");
                        for(int i = 4;i<tr.size()-12;i=i+2){
                            Elements td = tr.get(i).getElementsByTag("td");
                            if(i==4||i==8||i==12){
                                for(int j = 2;j<td.size();j++){
                                    Elements td1 = td.get(j).getElementsByTag("td");

                                    if((td1.get(0)+"").length()>60){

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                                        String[] b = a.split(">");
                                        String[] c = b[1].split("<");
                                        String course_name = c[0];
                                        String[] d = b[3].split("<");
                                        String course_teacher ="@"+  d[0];
                                        String[] e = b[4].split("<");
                                        String course_place = e[0];
                                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                                        Log.e("view66666666666666",infor);
                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName(course_name);
                                        courseBean.setcTeacher(course_teacher);
                                        courseBean.setcPlace(course_place);
                                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                                        courseBean.setcColor(isExists(courseBean.getcName()));
                                        courseList.add(courseBean);
                                    } else{

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName("");
                                        courseBean.setcTeacher("");
                                        courseBean.setcPlace("");
                                        courseList.add(courseBean);
                                    }
                                }
                            }else{
                                for(int j = 1;j<td.size();j++){
                                    Elements td1 = td.get(j).getElementsByTag("td");
                                    if((td1.get(0)+"").length()>60){

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                                        String[] b = a.split(">");
                                        String[] c = b[1].split("<");
                                        String course_name = c[0];
                                        String[] d = b[3].split("<");
                                        String course_teacher ="@"+  d[0];
                                        String[] e = b[4].split("<");
                                        String course_place = e[0];
                                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                                        Log.e("view66666666666666",infor);
                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName(course_name);
                                        courseBean.setcTeacher(course_teacher);
                                        courseBean.setcPlace(course_place);
                                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                                        courseBean.setcColor(isExists(courseBean.getcName()));
                                        courseList.add(courseBean);
                                    } else{

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName("");
                                        courseBean.setcTeacher("");
                                        courseBean.setcPlace("");
                                        courseList.add(courseBean);
                                    }
                                }
                            }
                        }

                        course_content = document.getElementsByTag("tr").text();
                        Log.e("Content:",course_content);
//                        Message message = Message.obtain();
//                        message.what = UPDATE_COURSE;
//                        handler.sendMessage(message);
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
                        // repsone_content = s;
                        Document document = Jsoup.parse(s);
                        Elements tr = document.getElementsByTag("tr");
                        for(int i = 4;i<tr.size()-12;i=i+2){
                            Elements td = tr.get(i).getElementsByTag("td");
                            if(i==4||i==8||i==12){
                                for(int j = 2;j<td.size();j++){
                                    Elements td1 = td.get(j).getElementsByTag("td");

                                    if((td1.get(0)+"").length()>60){

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                                        String[] b = a.split(">");
                                        String[] c = b[1].split("<");
                                        String course_name = c[0];
                                        String[] d = b[3].split("<");
                                        String course_teacher ="@"+  d[0];
                                        String[] e = b[4].split("<");
                                        String course_place = e[0];
                                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                                        Log.e("view66666666666666",infor);
                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName(course_name);
                                        courseBean.setcTeacher(course_teacher);
                                        courseBean.setcPlace(course_place);
                                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                                        courseBean.setcColor(isExists(courseBean.getcName()));
                                        courseList.add(courseBean);
                                    } else{

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName("");
                                        courseBean.setcTeacher("");
                                        courseBean.setcPlace("");
                                        courseList.add(courseBean);
                                    }
                                }
                            }else{
                                for(int j = 1;j<td.size();j++){
                                    Elements td1 = td.get(j).getElementsByTag("td");
                                    if((td1.get(0)+"").length()>60){

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                                        String[] b = a.split(">");
                                        String[] c = b[1].split("<");
                                        String course_name = c[0];
                                        String[] d = b[3].split("<");
                                        String course_teacher ="@"+  d[0];
                                        String[] e = b[4].split("<");
                                        String course_place = e[0];
                                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                                        Log.e("view66666666666666",infor);
                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName(course_name);
                                        courseBean.setcTeacher(course_teacher);
                                        courseBean.setcPlace(course_place);
                                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                                        courseBean.setcColor(isExists(courseBean.getcName()));
                                        courseList.add(courseBean);
                                    } else{

                                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                                        CourseBean courseBean = new CourseBean();
                                        courseBean.setcName("");
                                        courseBean.setcTeacher("");
                                        courseBean.setcPlace("");
                                        courseList.add(courseBean);
                                    }
                                }
                            }
                        }

                        course_content = document.getElementsByTag("tr").text();
                        Log.e("Content:",course_content);
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

    private int isExists(String s) {
        for(int i = 0;i<courseList.size();i++){
            if(courseList.get(i).getcName().equals(s)){
                return courseList.get(i).getcColor();
            }
        }
        color++;
        return color;
    }

}
