package com.example.xiyou3g.playxiyou.Utils

import android.util.Log
import okhttp3.Response
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by Lance on 2017/9/6.
 */
object HandleAttCheck{

    fun hanleAttCheck(respone: Response){
        val responseBody = respone.body().string()
        Log.e("GetAttendCheck",responseBody)
        val jsonObject: JSONObject = JSONObject(responseBody)
        val total = jsonObject.get("total")
        LogUtils.e("total:",total.toString())
    }
}