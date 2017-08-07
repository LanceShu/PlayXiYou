package com.example.xiyou3g.playxiyou.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.R;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/14.
 */

public class TestStackAdapter extends StackAdapter<Integer> {

    public TestStackAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
        }
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.card_item, parent, false);
        return new ColorItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.card_item;
    }

    static class ColorItemViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle;
        RecyclerView recyclerView;
        TextView isData;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            isData = (TextView) view.findViewById(R.id.card_tv);
        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            if(position == 0){
                mTextTitle.setText("第一学期");
            }else if(position == 1){
                mTextTitle.setText("第二学期");
            }else if(position == 2){
                mTextTitle.setText("第三学期");
            }else if(position == 3){
                mTextTitle.setText("第四学期");
            }else if(position == 4){
                mTextTitle.setText("第五学期");
            }else if(position == 5){
                mTextTitle.setText("第六学期");
            }else if(position == 6){
                mTextTitle.setText("第七学期");
            }else if(position == 7){
                mTextTitle.setText("第八学期");
            }

            if(stuname.equals("null")){
                isData.setVisibility(View.VISIBLE);
            }else{
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                ProjectAdapter projectAdapter = new ProjectAdapter(proList.get(position));
                recyclerView.setAdapter(projectAdapter);
            }

        }

    }


}
