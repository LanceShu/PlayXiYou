package com.example.xiyou3g.playxiyou.DataBean;

import java.util.ArrayList;
import java.util.List;

public class StuScoreInfo {

    private String scoreDate;
    private String stuNum;
    private String stuName;
    private String stuCollege;
    private String stuMajor;
    private List<ScoreInfo> scoreInfos = new ArrayList<>();

    public void setScoreInfos(List<ScoreInfo> scoreInfos) {
        this.scoreInfos = scoreInfos;
    }

    public List<ScoreInfo> getScoreInfos() {
        return scoreInfos;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public String getScoreDate() {
        return scoreDate;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuNum() {
        return stuNum;
    }
}
