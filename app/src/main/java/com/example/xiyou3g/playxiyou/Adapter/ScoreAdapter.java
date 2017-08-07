package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.ScoreBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/15.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreBean> scoreBeanList = new ArrayList<>();

    public ScoreAdapter(List<ScoreBean> scoreBeen){
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
        ScoreBean scoreBean = scoreBeanList.get(position);
        holder.sname.setText(scoreBean.getsName());
        holder.schengji.setText("成绩："+scoreBean.getsChengji());
        holder.sscore.setText(scoreBean.getsScore());
        holder.sgpa.setText(scoreBean.getsGpa());
        holder.steam.setText(scoreBean.getsTeam());
        holder.splace.setText(scoreBean.getsPlace());
    }

    @Override
    public int getItemCount() {
        return scoreBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView sname;
        private TextView schengji;
        private TextView sscore;
        private TextView sgpa;
        private TextView steam;
        private TextView splace;

        public ViewHolder(View view) {
            super(view);
            sname = (TextView) view.findViewById(R.id.sname);
            schengji = (TextView) view.findViewById(R.id.schengji);
            sscore = (TextView) view.findViewById(R.id.sscore);
            sgpa = (TextView) view.findViewById(R.id.sgpa);
            steam = (TextView) view.findViewById(R.id.steam);
            splace = (TextView) view.findViewById(R.id.splace);
        }
    }
}
