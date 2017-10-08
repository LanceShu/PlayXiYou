package com.example.xiyou3g.playxiyou.Utils

import android.os.Message
import com.example.xiyou3g.playxiyou.AttendFragment.APerFragment
import com.example.xiyou3g.playxiyou.Content.AttenContent
import com.example.xiyou3g.playxiyou.DataBean.CheckInforBean
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Lance on 2017/10/8.
 */
object HandleCheckInfor{

    fun handleCheckInfor(response: Response){
        AttenContent.CheckList.clear()
        val res = response.body().string()
        LogUtils.e("checkInfor:",res)
        val jsonObject = JSONObject(res)

        if(jsonObject.get("IsSucceed") == true){
            //LogUtils.e("checkInfor:",jsonObject.get("IsSucceed").toString())
            val obj = jsonObject.get("Obj") as JSONArray
            //LogUtils.e("checkInfor:",obj.length().toString())
            for(i in 0 until obj.length()){
                //LogUtils.e("checkInfor:",obj.get(i).toString())
                val check = JSONObject(obj.get(i).toString())

                val checkInfor = CheckInforBean()
                checkInfor.courseName = check.get("CourseName").toString()
                checkInfor.total = check.get("Total").toString()
                checkInfor.shouldAttend = check.get("ShouldAttend").toString()
                checkInfor.attend = check.get("Attend").toString()
                checkInfor.late = check.get("Late").toString()
                checkInfor.absence = check.get("Absence").toString()
                AttenContent.CheckList.add(checkInfor)
                //LogUtils.e("checkInfor:",checkInfor.courseName)
            }
            //LogUtils.e("checkInfor:",AttenContent.CheckInforList.size.toString())
            val mes = Message()
            mes.what = 72
            APerFragment.checkHandler.sendMessage(mes)
        }
    }
}