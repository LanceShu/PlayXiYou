package com.example.xiyou3g.playxiyou.HttpRequest

import com.example.xiyou3g.playxiyou.Content.AttenContent
import com.example.xiyou3g.playxiyou.DataBean.CheckBean
import kotlinx.android.synthetic.main.check_item.*
import okhttp3.*
import org.json.JSONObject

/**
 * Created by Lance on 2017/10/10.
 */
object RequestAppeal {

    fun requestAppeal(remark: String,checkBeanList :List<CheckBean> , position: Int,callback: Callback){
        val checkbean  = checkBeanList[position]
        val client = OkHttpClient()

        val mediaTYpe = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8")
        val jsonObeject = JSONObject()
        jsonObeject.put("Class_no",checkbean.class_No)
        jsonObeject.put("S_Date",checkbean.waterDate)
        jsonObeject.put("Jc",checkbean.jT_No)
        jsonObeject.put("S_Code",checkbean.sbh)
        jsonObeject.put("R_BH",checkbean.rbh)
        jsonObeject.put("Term",checkbean.term_No)
        jsonObeject.put("Remark",remark)
        jsonObeject.put("S_Status",checkbean.status)
        jsonObeject.put("A_Status","1")

        val requestBody = RequestBody.create(mediaTYpe,jsonObeject.toString())
        val request: Request? = Request.Builder()
                .header("Host","jwkq.xupt.edu.cn:8080")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Cookie",AttenContent.attenCookie)
                .url("http://jwkq.xupt.edu.cn:8080/Apply/ApplyData")
                .post(requestBody)
                .build()

        client.newCall(request).enqueue(callback)

    }

}