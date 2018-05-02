package com.example.xiyou3g.playxiyou.Utils

/**
 * Created by Lance
 * on 2017/9/10.
 */
object LogUtils{
    val VERBOSE = 1
    val DEBUG = 2
    val INFO = 3
    val WARN = 4
    val ERROR = 5
    val NOTHING = 6
    var level = VERBOSE

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) {
            android.util.Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG) {
            android.util.Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO) {
            android.util.Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN) {
            android.util.Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR) {
            android.util.Log.e(tag, msg)
        }
    }
}