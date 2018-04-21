package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.CheckInforBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance
 * on 2017/10/8.
 */

public class CheckInforAdapter extends RecyclerView.Adapter<CheckInforAdapter.ViewHolder> {

    private List<CheckInforBean> checkInforList = new ArrayList<>();

    public CheckInforAdapter(List<CheckInforBean> checkInforBeen){
        checkInforList = checkInforBeen;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_infor_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CheckInforBean checkInforBean = checkInforList.get(position);
        holder.courseName.setText(checkInforBean.getCourseName());
        holder.total.setText(checkInforBean.getTotal());
        holder.shouldAttend.setText(checkInforBean.getShouldAttend());
        holder.attend.setText(checkInforBean.getAttend());
        holder.late.setText(checkInforBean.getLate());
        holder.absence.setText(checkInforBean.getAbsence());
    }

    @Override
    public int getItemCount() {
        return checkInforList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView courseName;
        private TextView total;
        private TextView shouldAttend;
        private TextView attend;
        private TextView late;
        private TextView absence;

        public ViewHolder(View view) {
            super(view);
            courseName = (TextView) view.findViewById(R.id.CourseName);
            total = (TextView) view.findViewById(R.id.Total);
            shouldAttend = (TextView) view.findViewById(R.id.ShouldAttend);
            attend = (TextView) view.findViewById(R.id.Attend);
            late = (TextView) view.findViewById(R.id.Late);
            absence = (TextView) view.findViewById(R.id.Absence);
        }
    }
}
