package com.example.xiyou3g.playxiyou.HttpRequest;

import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.DataBean.ProjectBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/14.
 */

public class GetProjectData implements Runnable {

    private int team;
    private List<String> list = new ArrayList<>();

    public GetProjectData(int team, List<String> list){
        this.team = team;
        this.list = list;
    }

    @Override
    public void run() {
        get_Project();
    }

    private void get_Project() {
        synchronized (this){
            proList.get(team-1).clear();
            String url = "http://222.24.62.120/pyjh.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121607";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Document document = Jsoup.parse(s);
                    Elements tr = document.getElementsByTag("tr");
                    for(int i =4;i<tr.size()-25;i++){
                        Elements td = tr.get(i).getElementsByTag("td");
                        ProjectBean projectBean = new ProjectBean();
                        projectBean.setCname(td.get(1).text());

                        Log.e("project name   "+ team,td.get(1).text());

                        projectBean.setCstatue(td.get(5).text());
                        projectBean.setCid(td.get(0).text());
                        projectBean.setCscore(td.get(2).text());
                        projectBean.setCgpa(td.get(3).text());
                        projectBean.setCteam(td.get(4).text());
                        proList.get(team-1).add(projectBean);

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("failure555555",volleyError+"");
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
                    header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0");
                    return header;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    return ("__EVENTTARGET=xq&__EVENTARGUMENT=&__VIEWSTATE="+list.get(0)+"&xq="+team+"&kcxz=%C8%AB%B2%BF&dpDBGrid%3AtxtChoosePage=1&dpDBGrid%3AtxtPageSize=20").getBytes();
                }
            };
            mqueue.add(stringRequest);

        }
    }
}
