package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance on 2017/7/16.
 */

public class CourseBean {

    String cName;
    String cTeacher;
    String cPlace;
    int cColor;

    public void setcColor(int cColor) {
        this.cColor = cColor;
    }

    public int getcColor() {
        return cColor;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcPlace(String cPlace) {
        this.cPlace = cPlace;
    }

    public void setcTeacher(String cTeacher) {
        this.cTeacher = cTeacher;
    }


    public String getcName() {
        return cName;
    }

    public String getcPlace() {
        return cPlace;
    }

    public String getcTeacher() {
        return cTeacher;
    }
}
