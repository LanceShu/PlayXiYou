package com.example.xiyou3g.playxiyou.Utils;

import android.os.Message;
import android.util.Base64;
import android.util.Log;

import com.example.xiyou3g.playxiyou.DataBean.ScoreInfo;
import com.example.xiyou3g.playxiyou.DataBean.StuScoreInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.xiyou3g.playxiyou.Content.EduContent.UPDATE_SCORE;
import static com.example.xiyou3g.playxiyou.Content.EduContent.handler;
import static com.example.xiyou3g.playxiyou.Content.EduContent.scoreInfos;

/**
 * Created by Lance on 2017/9/5.
 */

public class HandleScoreData {

    public static void handleScore(String s){
        Document document = Jsoup.parse(s);
        String scoreInfo = document.select("input[name=__VIEWSTATE]").val();
        String str = new String(Base64.decode(scoreInfo.getBytes(), Base64.DEFAULT));
        handleDecodeFile(str);
        Message message = Message.obtain();
        message.what = UPDATE_SCORE;
        handler.sendMessage(message);
    }

    private static void handleDecodeFile(String str) {
        StuScoreInfo stuScoreInfo = new StuScoreInfo();
        String[] array = str.split("<Text;>;l<");
        stuScoreInfo.setScoreDate(handleContent(array[3]) + "  " + "第 " + handleContent(array[4]) + " 学期");
        for (int i = 4; i < array.length;i += 29 ) {
//            System.out.println(array[i]);
            ScoreInfo scoreInfo = new ScoreInfo();
            scoreInfo.setScoNum(handleContent(array[i+1]));
            scoreInfo.setScoName(handleContent(array[i+2]));
            scoreInfo.setScoType(handleContent(array[i+3]));
            scoreInfo.setScoCredit(handleContent(array[i+5]));
            scoreInfo.setScoGPA(handleContent(array[i+6]).trim());
            if(handleContent(array[i+7]).equals("&nbsp\\;")) {
                scoreInfo.setScoCommonScore("");
            }else {
                scoreInfo.setScoCommonScore(handleContent(array[i+7]));
            }
            if(handleContent(array[i+8]).equals("&nbsp\\;")) {
                scoreInfo.setScoMidtermScore("");
            }else {
                scoreInfo.setScoMidtermScore(handleContent(array[i+8]));
            }
            if(handleContent(array[i+9]).equals("&nbsp\\;")) {
                scoreInfo.setScoEndtermScore("");
            }else {
                scoreInfo.setScoEndtermScore(handleContent(array[i+9]));
            }
            if(handleContent(array[i+11]).equals("&nbsp\\;")) {
                scoreInfo.setScoFinalScore("");
            }else {
                scoreInfo.setScoFinalScore(handleContent(array[i+11]));
            }
            if(handleContent(array[i+17]).equals("&nbsp\\;")) {
                scoreInfo.setScoFinalScore("");
            }else {
                scoreInfo.setScoCollege(handleContent(array[i+17]));
            }
            scoreInfos.add(scoreInfo);
            Log.e("score:", scoreInfo.getScoName() + ":" + scoreInfo.getScoMidtermScore());
            if(array[i+27].contains("至今未通过课程成绩")) {
                break;
            }
        }
    }

    private static String handleContent(String s) {
        return s.split(";>>;>;;>;t<p<p<l")[0];
    }
}
