package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/24.
 */

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpViewHolder> {

    private List<String> helplist = new ArrayList<>();

    public HelpAdapter(List<String> strings){
        helplist = strings;
    }

    @Override
    public HelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item,parent,false);
        HelpViewHolder helpViewHolder = new HelpViewHolder(view);
        return helpViewHolder;
    }

    @Override
    public void onBindViewHolder(HelpViewHolder holder, int position) {
        String s = helplist.get(position);
        holder.helptv.setText(s);
    }

    @Override
    public int getItemCount() {
        return helplist.size();
    }

    static class HelpViewHolder extends RecyclerView.ViewHolder{

        private TextView helptv;

        public HelpViewHolder(View view) {
            super(view);
            helptv = (TextView) view.findViewById(R.id.help_tv);
        }
    }
}
