package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.ClassroomBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/21.
 */

public class AClassAdapter extends RecyclerView.Adapter<AClassAdapter.ViewHolder> {

    List<ClassroomBean> classroomBeanList = new ArrayList<>();

    public AClassAdapter(List<ClassroomBean> classroomBeen){
        classroomBeanList = classroomBeen;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.att_class_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassroomBean classroomBean = classroomBeanList.get(position);
        holder.name.setText(classroomBean.getCname());
        holder.place.setText(classroomBean.getCplace());
        holder.flow.setText(classroomBean.getCflow());
        holder.count.setText(classroomBean.getCcount());
    }

    @Override
    public int getItemCount() {
        return classroomBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView place;
        private TextView flow;
        private TextView count;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cname);
            place = (TextView) view.findViewById(R.id.cplace);
            flow = (TextView) view.findViewById(R.id.cflow);
            count = (TextView) view.findViewById(R.id.cnum);
        }
    }
}
