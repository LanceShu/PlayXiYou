package com.example.xiyou3g.playxiyou.Utils

import android.util.Log
import okhttp3.Response

/**
 * Created by Lance on 2017/9/6.
 */
object HandleAttCheck{

    fun hanleAttCheck(respone: Response){
        Log.e("GetAttendCheck",respone.body().string())
    }
}