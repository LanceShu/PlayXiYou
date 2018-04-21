package com.example.xiyou3g.playxiyou.Utils

import android.os.Message
import android.util.Log
import com.example.xiyou3g.playxiyou.AttendFragment.AClassFragment

import com.example.xiyou3g.playxiyou.DataBean.ClassroomBean

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException

import okhttp3.Response

import com.example.xiyou3g.playxiyou.Content.AttenContent.attenHandler
import com.example.xiyou3g.playxiyou.Content.AttenContent.classroomBeanList

/**
 * Created by Lance
 * on 2017/9/6.
 */

object HandleAttClass {

    @Throws(IOException::class)
    fun handleAttClass(response: Response) {
        try {
            val jsonObject = JSONObject(response.body().string())
            if (jsonObject.getBoolean("IsSucceed")) {
                val jsonArray = jsonObject.getJSONArray("Obj")
                for (i in 0..jsonArray.length() - 1) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val classroomBean = ClassroomBean()
                    classroomBean.cname = jsonObject1.getString("ROOMNUM")
                    classroomBean.cplace = jsonObject1.getJSONObject("Build").getString("NAME")
                    classroomBean.cflow = jsonObject1.getInt("ROOMFLOW").toString() + ""
                    classroomBean.ccount = jsonObject1.getInt("USERCOUNT").toString() + ""
                    //Log.e("attend classroom success", classroomBean.cname + " " + classroomBean.cplace + " " + classroomBean.cflow + " " + classroomBean.ccount)
                    classroomBeanList.add(classroomBean)
                }
                val message = Message()
                message.what = 9
                AClassFragment.aClassHandler.sendMessage(message)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}
