package com.example.xiyou3g.playxiyou.Utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static com.example.xiyou3g.playxiyou.Content.EduContent.stuSex;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuYear;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuacademy;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuclass;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stueducation;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuid;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stumajor;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuname;

/**
 * Created by Lance
 * on 2017/9/5.
 */

public class HandlePerInfo {

    public static void handlePer(String s){
        Log.e("noteacher",s.contains("你还没有进行本学期的课堂教学质量评价")+"");
        if(!s.contains("你还没有进行本学期的课堂教学质量评价")){
            Document document = Jsoup.parse(s);
            stuname = document.getElementById("xm").text();
            stuid = document.getElementById("xh").text();
            stuacademy = document.getElementById("lbl_xy").text();
            stumajor = document.getElementById("lbl_zymc").text();
            stuclass = document.getElementById("lbl_xzb").text();
            stueducation = document.getElementById("lbl_CC").text();
            stuSex = document.getElementById("lbl_xb").text();
            stuYear = document.getElementById("lbl_dqszj").text();
            Log.e("person2222222",stuname+"--"+stuid+"--"+stuacademy+"--"+stumajor+"--"+stuclass+"--"+stueducation);
        }else{
            stuname = "null";
        }
    }
}
