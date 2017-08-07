package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.DataBean.GuideBean;
import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;
import static com.example.xiyou3g.playxiyou.Content.MeContent.*;

/**
 * Created by Lance on 2017/8/2.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {

    private List<GuideBean> guideBeanList = new ArrayList<>();

    public GuideAdapter(List<GuideBean> guideBeen){
        guideBeanList = guideBeen;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_item,parent,false);
        GuideViewHolder viewHolder = new GuideViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        GuideBean guideBean = guideBeanList.get(position);
        if(guideBean.getType() == SEND_TYPE){
            holder.leftlayout.setVisibility(View.GONE);
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.rightmsg.setText(guideBean.getGuideMsg());
        }else{
            holder.rightlayout.setVisibility(View.GONE);
            holder.leftlayout.setVisibility(View.VISIBLE);
            holder.leftmsg.setText(guideBean.getGuideMsg());
        }
    }

    @Override
    public int getItemCount() {
        return guideBeanList.size();
    }

    static class GuideViewHolder extends RecyclerView.ViewHolder{

        private TextView leftmsg;
        private TextView rightmsg;
        private LinearLayout leftlayout;
        private LinearLayout rightlayout;

        public GuideViewHolder(View view) {
            super(view);
            leftmsg = (TextView) view.findViewById(R.id.left_msg);
            rightmsg = (TextView) view.findViewById(R.id.right_msg);
            leftlayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightlayout = (LinearLayout) view.findViewById(R.id.right_layout);
        }
    }
}
