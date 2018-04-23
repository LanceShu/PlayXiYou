package com.example.xiyou3g.playxiyou.HttpRequest;

import org.json.JSONException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.attenCookie;

/**
 * Created by Lance
 * on 2017/7/20.
 */

public class GetAttendClass{

    public static void getAttendClass(int id,Callback call) throws JSONException {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("id",id+"")
                .add("json","true")
                .build();
        Request request = new Request.Builder()
                .header("Cookie",attenCookie)
                .addHeader("Host","jwkq.xupt.edu.cn:8080")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding"," gzip, deflate")
                .addHeader("Accept-Language"," zh-CN,zh;q=0.8")
                .url("http://jwkq.xupt.edu.cn:8080/Room/GetListByBuild")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(call);
    }
}
