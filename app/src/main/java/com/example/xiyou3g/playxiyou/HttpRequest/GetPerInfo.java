package com.example.xiyou3g.playxiyou.HttpRequest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.Content.EduContent;
import com.example.xiyou3g.playxiyou.Utils.HandlePerInfo;

import java.util.HashMap;
import java.util.Map;

import static com.example.xiyou3g.playxiyou.Content.EduContent.cookies;
import static com.example.xiyou3g.playxiyou.Content.EduContent.loginName;
import static com.example.xiyou3g.playxiyou.Content.EduContent.mqueue;
import static com.example.xiyou3g.playxiyou.Content.EduContent.student_name;

/**
 * Created by Lance
 * on 2017/7/14.
 */

public class GetPerInfo {

    public GetPerInfo(){
        getPersonInfo();
    }

    private void getPersonInfo() {
        final String url = "http://222.24.62.120/xsgrxx.aspx?xh="+loginName+"&xm="+student_name+"&gnmkdm=N121501";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                HandlePerInfo.handlePer(s);
                EduContent.handler.sendEmptyMessage(EduContent.GET_COURSE_DATA);
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
