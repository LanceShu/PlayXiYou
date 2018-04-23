package com.example.xiyou3g.playxiyou.HttpRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.R;

import java.io.UnsupportedEncodingException;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance
 * on 2017/7/18.
 */

public class GetAttendCode implements Runnable {

    private final String codeUrl = "http://jwkq.xupt.edu.cn:8080/Common/GetValidateCode?time=" + System.currentTimeMillis();
    private byte[] imageBtye ;
    private ImageView acodeImage;

    public GetAttendCode(ImageView imageView){
        acodeImage = imageView;
    }

    @Override
    public void run() {
        getUser();
    }

    private void getUser() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, codeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBtye,0,imageBtye.length);
                acodeImage.setImageBitmap(bitmap);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                acodeImage.setImageResource(R.mipmap.codeerror);
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                imageBtye = response.data;
                attenCookie = response.headers.get("Set-Cookie");
                Log.e("Attendance :",attenCookie);
                String dataString;
                try {
                    dataString = new String(response.data,"UTF-8");
//                    Log.e("Attendance User:"+System.currentTimeMillis(),dataString);
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        mqueue.add(stringRequest);
    }
}
