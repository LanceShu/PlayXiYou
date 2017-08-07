package com.example.xiyou3g.playxiyou.Adapter;

import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.ScoreYearAndTeam;
import com.example.xiyou3g.playxiyou.HttpRequest.GetScoreData;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/15.
 */

public class ScoreTeamAdapter extends RecyclerView.Adapter<ScoreTeamAdapter.ViewHolder> {

    private List<ScoreYearAndTeam> years = new ArrayList<>();

    public ScoreTeamAdapter(List<ScoreYearAndTeam> strings){
        years = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_team_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ScoreYearAndTeam year = years.get(years.size()-1-position);
        Log.e("YearAndTeam length",year.getYear()+"   "+year.getTeam());
        holder.syear.setText(year.getYear());
        holder.steam.setText(year.getTeam());
        holder.scard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.scard.setBackgroundResource(R.color.colorAccent);
                currentScore = year.getYear()+"   第"+year.getTeam()+"学期";
                Message message = new Message();
                message.what = 4;
                handler.sendMessage(message);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Year = year.getYear();
                        Team = year.getTeam();
                        new GetScoreData(year.getYear(),year.getTeam()).run();
                    }
                },500);
            }
        });

    }

    @Override
    public int getItemCount() {
        return years.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView scard;
        private TextView syear;
        private TextView steam;

        public ViewHolder(View view) {
            super(view);
            scard = (CardView) view.findViewById(R.id.scard);
            syear = (TextView) view.findViewById(R.id.team_year);
            steam = (TextView) view.findViewById(R.id.team_num);
        }
    }
}
