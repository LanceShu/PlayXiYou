package com.example.xiyou3g.playxiyou.Utils;

import android.os.Message;
import android.util.Log;

import com.example.xiyou3g.playxiyou.DataBean.MajorBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.xiyou3g.playxiyou.Content.EduContent.handler;
import static com.example.xiyou3g.playxiyou.Content.EduContent.majorBeanList;

/**
 * Created by Lance on 2017/9/5.
 */

public class HandleMajorData {

    public static void handleMajor(String s){
        Document document = Jsoup.parse(s);
        Elements tr = document.getElementsByTag("tr");
        Log.e("GetMajorSuccess",tr.get(4).getElementsByTag("tr").size()+"");
        Elements tr1 = tr.get(4).getElementsByTag("tr");
        if(tr.get(4).getElementsByTag("tr").size() == 15){
            for(int i = 2;i<4;i++){
                Elements td = tr1.get(i).getElementsByTag("td");
                MajorBean majorBean = new MajorBean();
                majorBean.setmNeedScore(td.get(1).text());
                majorBean.setmGetScore(td.get(2).text());
                majorBean.setmUngetScore(td.get(3).text());
                majorBean.setmWantScore(td.get(4).text());
                majorBeanList.add(majorBean);
                Log.e("majorbean",majorBean.getmNeedScore()+" "+majorBean.getmGetScore()+" "+majorBean.getmUngetScore()+" "+majorBean.getmWantScore());
            }
        }else{
            for(int i = 2;i<5;i ++){
                Elements td = tr1.get(i).getElementsByTag("td");
                Log.e("majorbean111", td+"");
                MajorBean majorBean = new MajorBean();
                majorBean.setmNeedScore(td.get(1).text());
                majorBean.setmGetScore(td.get(2).text());
                majorBean.setmUngetScore(td.get(3).text());
                majorBean.setmWantScore(td.get(4).text());
                majorBeanList.add(majorBean);
                Log.e("majorbean",majorBean.getmNeedScore()+" "+majorBean.getmGetScore()+" "+majorBean.getmUngetScore()+" "+majorBean.getmWantScore());
            }
        }
        Message message = Message.obtain();
        message.what = 5;
        handler.sendMessage(message);
    }
}
