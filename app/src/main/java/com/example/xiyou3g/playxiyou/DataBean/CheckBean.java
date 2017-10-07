package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance on 2017/10/7.
 */

public class CheckBean {

    private String WaterDate;
    private String S_Name;
    private String S_Code;
    private String JT_No;
    private String RoomNum;
    private String BName;
    private String Status;

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
