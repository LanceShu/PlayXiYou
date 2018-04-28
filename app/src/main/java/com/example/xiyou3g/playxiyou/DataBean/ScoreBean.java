package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance
 * on 2017/7/15.
 */

public class ScoreBean {

    private String sName;
    private String sChengji;
    private String sScore;
    private String sGpa;
    private String sTeam;
    private String sPlace;

    public void setsChengji(String sChengji) {
        this.sChengji = sChengji;
    }

    public void setsGpa(String sGpa) {
        this.sGpa = sGpa;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsPlace(String sPlace) {
        this.sPlace = sPlace;
    }

    public void setsScore(String sScore) {
        this.sScore = sScore;
    }

    public void setsTeam(String sTeam) {
        this.sTeam = sTeam;
    }

    public String getsChengji() {
        return sChengji;
    }

    public String getsGpa() {
        return sGpa;
    }

    public String getsName() {
        return sName;
    }

    public String getsPlace() {
        return sPlace;
    }

    public String getsScore() {
        return sScore;
    }

    public String getsTeam() {
        return sTeam;
    }
}
