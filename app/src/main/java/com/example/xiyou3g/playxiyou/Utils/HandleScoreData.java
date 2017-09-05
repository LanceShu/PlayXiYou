package com.example.xiyou3g.playxiyou.Utils;

import android.os.Message;
import android.util.Log;

import com.example.xiyou3g.playxiyou.DataBean.ScoreBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.xiyou3g.playxiyou.Content.EduContent.UPDATE_SCORE;
import static com.example.xiyou3g.playxiyou.Content.EduContent.handler;
import static com.example.xiyou3g.playxiyou.Content.EduContent.scoreBeanList;

/**
 * Created by Lance on 2017/9/5.
 */

public class HandleScoreData {

    public static void handleScore(String s){
        Document document = Jsoup.parse(s);
        Elements tr = document.getElementsByTag("tr");
        for(int i =5;i<tr.size()-7;i++){
            Elements td = tr.get(i).getElementsByTag("td");
            ScoreBean scoreBean = new ScoreBean();
            scoreBean.setsName(td.get(3).text());
            scoreBean.setsChengji(td.get(8).text());
            scoreBean.setsScore(td.get(7).text());
            scoreBean.setsGpa(td.get(6).text());
            scoreBean.setsTeam(td.get(4).text());
            scoreBean.setsPlace(td.get(12).text());
            scoreBeanList.add(scoreBean);
            Log.e("chengji","--"+scoreBean.getsName()+"==="+scoreBean.getsChengji());
        }
        Message message = Message.obtain();
        message.what = UPDATE_SCORE;
        handler.sendMessage(message);
    }
}
