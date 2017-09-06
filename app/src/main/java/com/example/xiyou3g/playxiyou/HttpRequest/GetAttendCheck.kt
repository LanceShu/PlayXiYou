package com.example.xiyou3g.playxiyou.HttpRequest

import okhttp3.*
import org.json.JSONObject

/**
 * Created by Lance on 2017/9/6.
 */

object GetAttendCheck{

    fun getAttendCheck(WaterDate: String,Status: Int,Flag: String,page: Int,rows: Int,cookie: String,callback: Callback){

        val client = OkHttpClient()
        val formBody = FormBody.Builder()
                .add("WaterDate",WaterDate)
                .add("Status",Status.toString())
                .add("Flag",Flag)
                .add("page",page.toString())
                .add("rows",rows.toString())
                .build()
        val request = Request.Builder()
                .header("Accept-Encoding","gzip, deflate")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Cookie",cookie)
                .addHeader("Host","jwkq.xupt.edu.cn:8080")
                .url("http://jwkq.xupt.edu.cn:8080/User/GetAttendList")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(callback)
    }
}