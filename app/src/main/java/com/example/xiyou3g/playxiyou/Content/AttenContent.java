package com.example.xiyou3g.playxiyou.Content;

import android.os.Handler;

import com.example.xiyou3g.playxiyou.DataBean.AttendPerBean;
import com.example.xiyou3g.playxiyou.DataBean.CheckBean;
import com.example.xiyou3g.playxiyou.DataBean.CheckInforBean;
import com.example.xiyou3g.playxiyou.DataBean.ClassroomBean;
import com.example.xiyou3g.playxiyou.DataBean.SiliuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/18.
 */

public class AttenContent {

    static public String attenCookie;
    static public int islogin = 0;

    static public Handler attenHandler = new Handler();
    static public AttendPerBean attendPerBean = new AttendPerBean();
    static public List<ClassroomBean> classroomBeanList = new ArrayList<>();
    static public String classname = "教室情况";

    static public SiliuBean siliuBean = new SiliuBean();
    static public int isGet = 0;

    //考勤情况的记录;
    public static List<CheckBean> checkBeanList = new ArrayList<>();

    //基本信息->考勤统计表;
    public static List<CheckInforBean> CheckList = new ArrayList<>();

    //判断是否第一次进入智慧教室;
    public static boolean isFirst = true;
}
