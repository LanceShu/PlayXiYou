package com.example.xiyou3g.playxiyou.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/7/25.
 */

public class QueAdpater extends RecyclerView.Adapter<QueAdpater.QueViewHolder> {

    private List<String> quesList = new ArrayList<>();
    private List<String> answer = new ArrayList<>();

    public QueAdpater(List<String> quesList,List<String> answer){
        this.quesList = quesList;
        this.answer = answer;
    }

    @Override
    public QueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_item,parent,false);
        QueViewHolder viewHolder = new QueViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QueViewHolder holder, int position) {
        holder.que.setText(quesList.get(position));
        holder.answer.setText(answer.get(position));
    }

    @Override
    public int getItemCount() {
        return answer.size();
    }

    static class QueViewHolder extends RecyclerView.ViewHolder{

        private TextView que;
        private TextView answer;

        public QueViewHolder(View view) {
            super(view);
            que = (TextView) view.findViewById(R.id.left_msg);
            answer = (TextView) view.findViewById(R.id.right_msg);
        }
    }
}
