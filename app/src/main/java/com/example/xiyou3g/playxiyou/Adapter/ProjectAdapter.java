package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.ProjectBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/14.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private List<ProjectBean> projectBeanList = new ArrayList<>();

    public ProjectAdapter(List<ProjectBean> projectBeen){
        projectBeanList = projectBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProjectBean projectBean = projectBeanList.get(position);
        holder.cname.setText(projectBean.getCname());
        holder.cstatue.setText(projectBean.getCstatue());
        holder.cid.setText(projectBean.getCid());
        holder.cscore.setText(projectBean.getCscore());
        holder.cgpa.setText(projectBean.getCgpa());
        holder.cteam.setText(projectBean.getCteam());
    }

    @Override
    public int getItemCount() {
        return projectBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cname;
        TextView cstatue;
        TextView cid;
        TextView cscore;
        TextView cgpa;
        TextView cteam;

        public ViewHolder(View view) {
            super(view);
            cname = (TextView) view.findViewById(R.id.courname);
            cstatue = (TextView) view.findViewById(R.id.courstatus);
            cid = (TextView) view.findViewById(R.id.courid);
            cscore = (TextView) view.findViewById(R.id.courscore);
            cgpa = (TextView) view.findViewById(R.id.courgpa);
            cteam = (TextView) view.findViewById(R.id.courteam);
        }
    }
}
