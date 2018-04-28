package com.example.xiyou3g.playxiyou.DataBean;

/**
 * Created by Lance
 * on 2017/7/17.
 */

public class MajorBean {

    private String mNeedScore;      //学分要求;
    private String mGetScore;       //获得学分;
    private String mUngetScore;     //未通过学分;
    private String mWantScore;      //还需学分;

    public void setmGetScore(String mGetScore) {
        this.mGetScore = mGetScore;
    }

    public void setmNeedScore(String mNeedScore) {
        this.mNeedScore = mNeedScore;
    }

    public void setmUngetScore(String mUngetScore) {
        this.mUngetScore = mUngetScore;
    }

    public void setmWantScore(String mWantScore) {
        this.mWantScore = mWantScore;
    }

    public String getmGetScore() {
        return mGetScore;
    }

    public String getmNeedScore() {
        return mNeedScore;
    }

    public String getmUngetScore() {
        return mUngetScore;
    }

    public String getmWantScore() {
        return mWantScore;
    }
}
