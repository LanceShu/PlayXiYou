package com.example.xiyou3g.playxiyou.HttpRequest;

import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.DataBean.MajorBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/17.
 */

public class GetMajorData implements Runnable{

    private String viewState = "";
    final String url = "http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605";

    @Override
    public void run() {

        getMajorVIEWSTATE();                        //获取VIEWSTATE;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getMajor();
            }
        },2000);
    }

    private void getMajorVIEWSTATE(){

        synchronized (this){
            majorBeanList.clear();
            final String url = "http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605";
            StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Document document1 = Jsoup.parse(s);
                    viewState = document1.select("input[name=__VIEWSTATE]").val();
                    try {
                        viewState = URLEncoder.encode(viewState,"GBK");
                    } catch (UnsupportedEncodingException e) {
                        Log.e("error","123456789");
                    }
                    Log.e("viewstate",viewState);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    header.put("Accept-Encoding","gzip, deflate");
                    header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                    header.put("Cookie",cookies);
                    header.put("Referer","http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605");
                    header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                    return header;
                }
            };
            mqueue.add(stringRequest);
        }
    }

    private void getMajor(){

        synchronized (this){

            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Document document = Jsoup.parse(s);
                            Elements tr = document.getElementsByTag("tr");
                            Log.e("GetMajorSuccess",tr.get(4).getElementsByTag("tr").size()+"");
                            Elements tr1 = tr.get(4).getElementsByTag("tr");
                            if(tr.get(4).getElementsByTag("tr").size() == 15){
                                for(int i = 2;i<4;i++){
                                    Elements td = tr1.get(i).getElementsByTag("td");
                                    MajorBean majorBean = new MajorBean();
                                    majorBean.setmNeedScore(td.get(1).text());
                                    majorBean.setmGetScore(td.get(2).text());
                                    majorBean.setmUngetScore(td.get(3).text());
                                    majorBean.setmWantScore(td.get(4).text());
                                    majorBeanList.add(majorBean);
                                    Log.e("majorbean",majorBean.getmNeedScore()+" "+majorBean.getmGetScore()+" "+majorBean.getmUngetScore()+" "+majorBean.getmWantScore());
                                }
                            }else{
                                for(int i = 2;i<5;i = i+2){
                                    Elements td = tr1.get(i).getElementsByTag("td");
                                    MajorBean majorBean = new MajorBean();
                                    majorBean.setmNeedScore(td.get(1).text());
                                    majorBean.setmGetScore(td.get(2).text());
                                    majorBean.setmUngetScore(td.get(3).text());
                                    majorBean.setmWantScore(td.get(4).text());
                                    majorBeanList.add(majorBean);
                                    Log.e("majorbean",majorBean.getmNeedScore()+" "+majorBean.getmGetScore()+" "+majorBean.getmUngetScore()+" "+majorBean.getmWantScore());
                                }
                            }
                            Message message = Message.obtain();
                            message.what = 5;
                            handler.sendMessage(message);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("GetMajorError",volleyError.toString());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    header.put("Accept-Encoding","gzip, deflate");
                    header.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.8");
                    header.put("Cookie",cookies);
                    header.put("Referer","http://222.24.62.120/xscjcx.aspx?xh="+loginName+"&xm="+stuname+"&gnmkdm=N121605");
                    header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
                    return header;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    return ("__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE="+viewState+"&hidLanguage=&ddlXN=&ddlXQ=&ddl_kcxz=&Button1=%B3%C9%BC%A8%CD%B3%BC%C6").getBytes();
                }
            };
            mqueue.add(stringRequest1);
        }
    }
}
