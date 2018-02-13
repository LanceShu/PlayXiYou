package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.ScoreBean;
import com.example.xiyou3g.playxiyou.DataBean.ScoreInfo;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/15.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreInfo> scoreBeanList = new ArrayList<>();

    public ScoreAdapter(List<ScoreInfo> scoreBeen){
        scoreBeanList = scoreBeen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_iteam,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScoreInfo scoreBean = scoreBeanList.get(position);
        holder.sName.setText(scoreBean.getScoName());
        holder.sFinalScore.setText("最终成绩： "+scoreBean.getScoFinalScore());
        holder.sNum.setText(scoreBean.getScoNum());
        holder.sEndScore.setText(scoreBean.getScoEndtermScore());
        holder.sComScore.setText(scoreBean.getScoCommonScore());
        holder.sMidScore.setText(scoreBean.getScoMidtermScore());
        holder.sCredit.setText(scoreBean.getScoCredit());
        holder.sGPA.setText(scoreBean.getScoGPA());
        holder.sType.setText(scoreBean.getScoType());
        holder.sCollege.setText(scoreBean.getScoCollege());
    }

    @Override
    public int getItemCount() {
        return scoreBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView sName;
        private TextView sFinalScore;
        private TextView sNum;
        private TextView sEndScore;
        private TextView sComScore;
        private TextView sMidScore;
        private TextView sGPA;
        private TextView sCredit;
        private TextView sType;
        private TextView sCollege;

        public ViewHolder(View view) {
            super(view);
            sName = (TextView) view.findViewById(R.id.sname);
            sFinalScore = (TextView) view.findViewById(R.id.sfinalscore);
            sNum = (TextView) view.findViewById(R.id.snum);
            sEndScore = (TextView) view.findViewById(R.id.sendscore);
            sComScore = (TextView) view.findViewById(R.id.scomscore);
            sMidScore = (TextView) view.findViewById(R.id.smidscore);
            sGPA = (TextView) view.findViewById(R.id.sgpa);
            sCredit = (TextView) view.findViewById(R.id.scredit);
            sType = (TextView) view.findViewById(R.id.stype);
            sCollege = (TextView) view.findViewById(R.id.scollege);
        }
    }
}
