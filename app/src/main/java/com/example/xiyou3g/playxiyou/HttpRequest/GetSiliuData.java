package com.example.xiyou3g.playxiyou.HttpRequest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.isGet;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.siliuBean;
import static com.example.xiyou3g.playxiyou.Content.EduContent.mqueue;

/**
 * Created by Lance
 * on 2017/7/24.
 */

public class GetSiliuData {

    public static void getSiliu(final String id, final String name) {

        final String url = "http://cet.99sushe.com/getscore";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("siliu ssss",s.length()+"");
                if(s.length()>1){
                    siliuBean.setSid(s.split(",")[1]);
                    siliuBean.setWlisten(s.split(",")[2]);
                    siliuBean.setWread(s.split(",")[3]);
                    siliuBean.setWrite(s.split(",")[4]);
                    siliuBean.setWtotal(s.split(",")[5]);
                    siliuBean.setSchool(s.split(",")[6]);
                    siliuBean.setSname(s.split(",")[7]);
                    siliuBean.setLid(s.split(",")[9]);
                    siliuBean.setLlevel(s.split(",")[10].substring(0,1));
                    isGet = 1;
                    Log.e("siliu success",siliuBean.getLlevel());
                }else{
                    isGet = 0;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("siliu faliure",volleyError.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map map = new HashMap();
                map.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)");
                map.put("Content-Type","application/x-www-form-urlencoded");
                map.put("Referer","http://cet.99sushe.com/");
                map.put("Accept-Encoding","gzip, deflate");
                map.put("Accept-Language","zh-CN,zh;q=0.8");
                return map;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String sname = name.substring(0,2);
                try {
                    sname = URLEncoder.encode(sname,"GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ("id="+id+"&name="+sname).getBytes();
            }
        };
        mqueue.add(stringRequest);
    }
}
