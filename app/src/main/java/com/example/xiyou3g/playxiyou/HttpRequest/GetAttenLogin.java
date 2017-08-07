package com.example.xiyou3g.playxiyou.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Lance on 2017/7/18.
 */

public class GetAttenLogin {

    public static void sendOKHttpRequest(String name, String pass, String code, String cookie, Callback callback) throws JSONException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName",name);
        jsonObject.put("UserPassword",pass);
        jsonObject.put("ValiCode",code);
        RequestBody requestBody = RequestBody.create(mediaType,jsonObject.toString());
        Request request = new Request.Builder()
                .header("Host","jwkq.xupt.edu.cn:8080")
                .addHeader("Cookie",cookie)
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                .url("http://jwkq.xupt.edu.cn:8080/Account/Login")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
