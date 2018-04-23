package com.example.xiyou3g.playxiyou.Utils;

import android.os.Message;

import com.example.xiyou3g.playxiyou.DataBean.ProjectBean;
import com.example.xiyou3g.playxiyou.MeFragment.ProjectFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.xiyou3g.playxiyou.Content.EduContent.proList;

/**
 * Created by Lance
 * on 2017/9/5.
 */

public class HandleProjectData {

    public static void handleProject(String s,int team){
        Document document = Jsoup.parse(s);
        Elements tr = document.getElementsByTag("tr");
        for(int i =4;i<tr.size()-25;i++){
            Elements td = tr.get(i).getElementsByTag("td");
            ProjectBean projectBean = new ProjectBean();
            projectBean.setCname(td.get(1).text());
            projectBean.setCstatue(td.get(5).text());
            projectBean.setCid(td.get(0).text());
            projectBean.setCscore(td.get(2).text());
            projectBean.setCgpa(td.get(3).text());
            projectBean.setCteam(td.get(4).text());
            proList.get(team-1).add(projectBean);
        }
        Message message = Message.obtain();
        message.what = 6;
        ProjectFragment.projectHandler.sendMessage(message);
    }
}
