package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance
 * on 2017/7/24.
 */

public class SiliuBean {

    private String sid;
    private String wlisten;
    private String wread;
    private String write;
    private String wtotal;
    private String school;
    private String sname;
    private String lid;
    private String llevel;

    public void setLid(String lid) {
        this.lid = lid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setLlevel(String llevel) {
        this.llevel = llevel;
    }

    public void setWlisten(String wlisten) {
        this.wlisten = wlisten;
    }

    public void setWread(String wread) {
        this.wread = wread;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public void setWtotal(String wtotal) {
        this.wtotal = wtotal;
    }

    public String getSchool() {
        return school;
    }

    public String getLid() {
        return lid;
    }

    public String getLlevel() {
        return llevel;
    }

    public String getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getWlisten() {
        return wlisten;
    }

    public String getWread() {
        return wread;
    }

    public String getWrite() {
        return write;
    }

    public String getWtotal() {
        return wtotal;
    }
}
