package com.example.xiyou3g.playxiyou.Utils

import android.util.Log
import okhttp3.Response
<<<<<<< HEAD
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
=======
>>>>>>> f2bde2d30d8fcd813c0c1aed3d1efe72cffa2399

/**
 * Created by Lance on 2017/9/6.
 */
object HandleAttCheck{

    fun hanleAttCheck(respone: Response){
<<<<<<< HEAD
        val responseBody = respone.body().string()
        Log.e("GetAttendCheck",responseBody)
        val jsonObject: JSONObject = JSONObject(responseBody)
        val total = jsonObject.get("total")
        LogUtils.e("total:",total.toString())
=======
        Log.e("GetAttendCheck",respone.body().string())
>>>>>>> f2bde2d30d8fcd813c0c1aed3d1efe72cffa2399
    }
}