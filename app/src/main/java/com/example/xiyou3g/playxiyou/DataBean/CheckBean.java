package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance
 * on 2017/10/7.
 */

public class CheckBean {

    //刷卡时间;
    private String WaterDate;
    //课程名称;
    private String S_Name;
    //课程代码;
    private String S_Code;
    //课程节次;
    private String JT_No;
    //课程教室;
    private String RoomNum;
    //教室所在地;
    private String BName;
    //状态;
    private String Status;

    //课程编号;
    private int Class_No;
    private int SBH;
    private int RBH;
    //学期;
    private String Term_No;

    public void setClass_No(int class_No) {
        Class_No = class_No;
    }

    public void setRBH(int RBH) {
        this.RBH = RBH;
    }

    public void setSBH(int SBH) {
        this.SBH = SBH;
    }

    public void setTerm_No(String term_No) {
        Term_No = term_No;
    }

    public int getClass_No() {
        return Class_No;
    }

    public int getRBH() {
        return RBH;
    }

    public int getSBH() {
        return SBH;
    }

    public String getTerm_No() {
        return Term_No;
    }

    public String getBName() {
        return BName;
    }

    public String getJT_No() {
        return JT_No;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public String getS_Code() {
        return S_Code;
    }

    public String getS_Name() {
        return S_Name;
    }

    public String getStatus() {
        return Status;
    }

    public String getWaterDate() {
        return WaterDate;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public void setJT_No(String JT_No) {
        this.JT_No = JT_No;
    }

    public void setRoomNum(String roomNum) {
        RoomNum = roomNum;
    }

    public void setS_Code(String s_Code) {
        S_Code = s_Code;
    }

    public void setS_Name(String s_Name) {
        S_Name = s_Name;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setWaterDate(String waterDate) {
        WaterDate = waterDate;
    }
}
