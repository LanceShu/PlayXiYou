package com.example.xiyou3g.playxiyou.HttpRequest

import com.example.xiyou3g.playxiyou.Content.AttenContent
import okhttp3.*

/**
 * Created by Lance on 2017/10/8.
 */
object GetCheckInfor {

    fun getCheckInfor(call: Callback){
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
                .add("json","true")
                .build()
        val request = Request.Builder()
                .header("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding"," gzip, deflate")
                .addHeader("Accept-Language"," zh-CN,zh;q=0.8")
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Cookie",AttenContent.attenCookie)
                .addHeader("Host","jwkq.xupt.edu.cn:8080")
                .url("http://jwkq.xupt.edu.cn:8080/User/GetAttendRepList")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(call)
    }
}