package com.example.xiyou3g.playxiyou.Utils;

import android.util.Log;

import com.example.xiyou3g.playxiyou.DataBean.CourseBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.xiyou3g.playxiyou.Content.EduContent.courseList;
import static com.example.xiyou3g.playxiyou.Content.EduContent.course_content;

/**
 * Created by Lance on 2017/9/5.
 */

public class HandleCourseData {

    public static int color;

    public static void handleCourse(String s){
        Document document = Jsoup.parse(s);
        Elements tr = document.getElementsByTag("tr");
        for(int i = 4;i<tr.size()-12;i=i+2){
            Elements td = tr.get(i).getElementsByTag("td");
            if(i==4||i==8||i==12){
                for(int j = 2;j<td.size();j++){
                    Elements td1 = td.get(j).getElementsByTag("td");

                    if((td1.get(0)+"").length()>60){

                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                        String[] b = a.split(">");
                        String[] c = b[1].split("<");
                        String course_name = c[0];
                        String[] d = b[3].split("<");
                        String course_teacher ="@"+  d[0];
                        String[] e = b[4].split("<");
                        String course_place = e[0];
                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                        Log.e("view66666666666666",infor);
                        CourseBean courseBean = new CourseBean();
                        courseBean.setcName(course_name);
                        courseBean.setcTeacher(course_teacher);
                        courseBean.setcPlace(course_place);
                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                        courseBean.setcColor(isExists(courseBean.getcName()));
                        courseList.add(courseBean);
                    } else{

                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                        CourseBean courseBean = new CourseBean();
                        courseBean.setcName("");
                        courseBean.setcTeacher("");
                        courseBean.setcPlace("");
                        courseList.add(courseBean);
                    }
                }
            }else{
                for(int j = 1;j<td.size();j++){
                    Elements td1 = td.get(j).getElementsByTag("td");
                    if((td1.get(0)+"").length()>60){

                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                        String a = (td1.get(0) + "").substring(25,(td1.get(0)+"").length());
                        String[] b = a.split(">");
                        String[] c = b[1].split("<");
                        String course_name = c[0];
                        String[] d = b[3].split("<");
                        String course_teacher ="@"+  d[0];
                        String[] e = b[4].split("<");
                        String course_place = e[0];
                        String infor = course_name+"\n"+course_teacher+"\n"+course_place;
                        Log.e("view66666666666666",infor);
                        CourseBean courseBean = new CourseBean();
                        courseBean.setcName(course_name);
                        courseBean.setcTeacher(course_teacher);
                        courseBean.setcPlace(course_place);
                        Log.e("current course=====",course_name+" "+course_teacher+" "+course_place);
                        courseBean.setcColor(isExists(courseBean.getcName()));
                        courseList.add(courseBean);
                    } else{

                        Log.e("courese content"+td1.get(0).toString().length()+":",td1.get(0).toString());

                        CourseBean courseBean = new CourseBean();
                        courseBean.setcName("");
                        courseBean.setcTeacher("");
                        courseBean.setcPlace("");
                        courseList.add(courseBean);
                    }
                }
            }
        }

        course_content = document.getElementsByTag("tr").text();
        Log.e("Content:",course_content);
    }

    private static int isExists(String s) {
        for(int i = 0;i<courseList.size();i++){
            if(courseList.get(i).getcName().equals(s)){
                return courseList.get(i).getcColor();
            }
        }
        color++;
        return color;
    }
}
