package com.example.xiyou3g.playxiyou.HttpRequest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance on 2017/7/20.
 */

public class GetAttendPer {

    public static void getAttendPer(Callback call){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Host","jwkq.xupt.edu.cn:8080")
                .addHeader("Cookie",attenCookie)
                .url("http://jwkq.xupt.edu.cn:8080/User")
                .build();
        client.newCall(request).enqueue(call);
    }
}
