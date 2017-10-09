package com.example.xiyou3g.playxiyou.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private float shouldAttend;
    private float attend;
    private float late;
    private float absence;
    private PieChartData pieChartData;
    private List<SliceValue> sliceValues;

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
        attend = Float.parseFloat(checkInforBean.getAttend());
        late = Float.parseFloat(checkInforBean.getLate());
        absence = Float.parseFloat(checkInforBean.getAbsence());

        LogUtils.INSTANCE.e("position1:","success");

        //LogUtils.INSTANCE.e("sliceValuesSize:",position+"  "+sliceValues.get(2)+"");
        initPieCharts(checkInforBean.getCourseName(),holder.chartView);

        holder.chartView.setPieChartData(pieChartData);
        holder.chartView.setValueSelectionEnabled(false);
        holder.chartView.setAlpha(0.9f);
        holder.chartView.setCircleFillRatio(1f);
        holder.chartView.setOnValueTouchListener(selectListener);

    }

    private void initPieCharts(String courseName,PieChartView chartView) {

        sliceValues = new ArrayList<>();
        sliceValues.clear();

        sliceValues.add(new SliceValue(attend, Color.parseColor("#78cc63")));
        sliceValues.add(new SliceValue(late, Color.parseColor("#ccb563")));
        sliceValues.add(new SliceValue(absence , Color.parseColor("#cc6563")));

        LogUtils.INSTANCE.e("position3:","success");
        pieChartData = new PieChartData();
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(false);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setValues(sliceValues);
        pieChartData.setCenterCircleColor(Color.WHITE);
        pieChartData.setCenterCircleScale(0.5f);
        pieChartData.setCenterText1(courseName);
        pieChartData.setCenterText1FontSize(10);
        pieChartData.setCenterText1Color(Color.GRAY);

//        chartView.setPieChartData(pieChartData);
//        chartView.setValueSelectionEnabled(false);
//        chartView.setAlpha(0.9f);
//        chartView.setCircleFillRatio(1f);
//        chartView.setOnValueTouchListener(selectListener);
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
