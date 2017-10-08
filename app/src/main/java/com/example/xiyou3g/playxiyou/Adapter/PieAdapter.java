package com.example.xiyou3g.playxiyou.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.playxiyou.DataBean.CheckInforBean;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Lance on 2017/10/8.
 */

public class PieAdapter extends RecyclerView.Adapter<PieAdapter.ViewHolder> {

    private List<CheckInforBean> checkInforBeanList = new ArrayList<>();

    private int shouldAttend;
    private int attend;
    private int late;
    private int absence;
    private PieChartData pieChartData;
    private List<SliceValue> sliceValues = new ArrayList<>();

    public PieAdapter(List<CheckInforBean> checkInforBeen){
        checkInforBeanList = checkInforBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charts_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CheckInforBean checkInforBean = checkInforBeanList.get(position);
        shouldAttend = Integer.parseInt(checkInforBean.getShouldAttend());
        attend = Integer.parseInt(checkInforBean.getAttend());
        late = Integer.parseInt(checkInforBean.getLate());
        absence = Integer.parseInt(checkInforBean.getAbsence());

        holder.chartView.setOnValueTouchListener(selectListener);
        setPieChartData(shouldAttend,attend,late,absence);
        initPieCharts();
        holder.chartView.setPieChartData(pieChartData);
        holder.chartView.setValueSelectionEnabled(true);
        holder.chartView.setAlpha(0.9f);
        holder.chartView.setCircleFillRatio(1f);
    }

    private void initPieCharts() {
        pieChartData = new PieChartData();
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(false);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setValues(sliceValues);
        pieChartData.setCenterCircleColor(Color.WHITE);
        pieChartData.setCenterCircleScale(0.5f);
    }

    private void setPieChartData(int shouldAttend, int attend, int late, int absence) {
        sliceValues.clear();

        LogUtils.INSTANCE.e("attend:",attend+"");
        LogUtils.INSTANCE.e("late:",late+"");
        LogUtils.INSTANCE.e("absence:",absence+"");

        SliceValue attendSliceValue = new SliceValue((float) 14, Color.parseColor("#78cc63"));
        sliceValues.add(attendSliceValue);
        SliceValue lateSliceValue = new SliceValue((float) 13, Color.parseColor("#ccb563"));
        sliceValues.add(lateSliceValue);
        SliceValue absenceSliceValue = new SliceValue((float) 0, Color.parseColor("#cc6563"));
        sliceValues.add(absenceSliceValue);
    }

    @Override
    public int getItemCount() {
        return checkInforBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private PieChartView chartView;

        public ViewHolder(View itemView) {
            super(itemView);
            chartView = (PieChartView) itemView.findViewById(R.id.pie_charts);
        }
    }

    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {
        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {

        }

        @Override
        public void onValueDeselected() {

        }
    };

}
