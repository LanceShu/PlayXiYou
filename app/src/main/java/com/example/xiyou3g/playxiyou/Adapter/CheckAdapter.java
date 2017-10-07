package com.example.xiyou3g.playxiyou.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.playxiyou.DataBean.CheckBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/10/7.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder> {

    private List<CheckBean> checkBeanList  = new ArrayList<>();
    private Context context;

    public CheckAdapter(Context context,List<CheckBean> checkBeen){
        checkBeanList = checkBeen;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(checkBeanList.size() > 0){
            CheckBean checkBean = checkBeanList.get(position);
            holder.WaterDate.setText(checkBean.getWaterDate());
            holder.S_Name.setText(checkBean.getS_Name());
            holder.S_Code.setText(checkBean.getS_Code());
            holder.JT_No.setText(checkBean.getJT_No());
            holder.RoomNum.setText(checkBean.getRoomNum());
            holder.BName.setText(checkBean.getBName());
            holder.Status.setText(checkBean.getStatus());
            holder.appeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"申诉",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return checkBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView WaterDate;
        private TextView S_Name;
        private TextView S_Code;
        private TextView JT_No;
        private TextView RoomNum;
        private TextView BName;
        private TextView Status;
        private Button appeal;

        public ViewHolder(View view) {
            super(view);
            WaterDate = (TextView) view.findViewById(R.id.WaterDate);
            S_Name = (TextView) view.findViewById(R.id.S_Name);
            S_Code = (TextView) view.findViewById(R.id.S_Code);
            JT_No = (TextView) view.findViewById(R.id.JT_No);
            RoomNum = (TextView) view.findViewById(R.id.RoomNum);
            BName = (TextView) view.findViewById(R.id.BName);
            Status = (TextView) view.findViewById(R.id.Status);
            appeal = (Button) view.findViewById(R.id.appeal);
        }
    }
}
