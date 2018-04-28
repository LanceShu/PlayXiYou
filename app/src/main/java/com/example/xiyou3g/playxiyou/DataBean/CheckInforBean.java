package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance
 * on 2017/10/8.
 */

//基本信息->考勤信息统计
public class CheckInforBean {

    //课程名字;
    private String CourseName;

    //总计;
    private String Total;

    //应到;
    private String ShouldAttend;

    //正常
    private String Attend;

    //迟到
    private String Late;

    //缺勤;
    private String Absence;

    public void setAbsence(String absence) {
        Absence = absence;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public void setLate(String late) {
        Late = late;
    }

    public void setShouldAttend(String shouldAttend) {
        ShouldAttend = shouldAttend;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public void setAttend(String attend) {
        Attend = attend;
    }

    public String getAttend() {
        return Attend;
    }

    public String getAbsence() {
        return Absence;
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getLate() {
        return Late;
    }

    public String getShouldAttend() {
        return ShouldAttend;
    }

    public String getTotal() {
        return Total;
    }
}
