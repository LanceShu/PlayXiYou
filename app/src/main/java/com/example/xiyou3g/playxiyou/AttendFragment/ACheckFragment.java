package com.example.xiyou3g.playxiyou.AttendFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.Adapter.CheckAdapter;
import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendCheck;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleAttCheck;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.attenCookie;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.checkBeanList;

/**
 * Created by Lance
 * on 2017/7/20.
 */

public class ACheckFragment extends Fragment {

    @BindView(R.id.startdate)
    TextView startdate;

    @BindView(R.id.enddate)
    TextView enddata;

    @BindView(R.id.today)
    CheckBox today;

    @BindView(R.id.week)
    CheckBox week;

    @BindView(R.id.month)
    CheckBox mon;

    @BindView(R.id.anormal)
    CheckBox normal;

    @BindView(R.id.late)
    CheckBox late;

    @BindView(R.id.absence)
    CheckBox absence;

    @BindView(R.id.find)
    Button find;

    @BindView(R.id.isCheckData)
    TextView isCheckData;

    @BindView(R.id.check_recycler)
    RecyclerView checkRecycler;

    private int year;
    private int month;
    private int day;
    private String current;

    private CheckAdapter checkAdapter ;
    private ProgressDialog progressDialog;
    public static ACheckHandler checkHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.atten_check_fragment,container,false);
        current = getCurrentDay();
        ButterKnife.bind(this, view);
        initWight();
        checkHandler = new ACheckHandler(isCheckData, checkAdapter);
        return view;
    }

    public static class ACheckHandler extends Handler {
        private WeakReference<TextView> checkData;
        private WeakReference<CheckAdapter> adapter;

        ACheckHandler (TextView checkData, CheckAdapter adapter) {
            this.checkData = new WeakReference<>(checkData);
            this.adapter = new WeakReference<>(adapter);
        }

        @Override
        public void handleMessage(Message msg) {
            TextView isCheckData = checkData.get();
            CheckAdapter checkAdapter = adapter.get();
            if (isCheckData != null && checkAdapter != null) {
                switch (msg.what){
                    case 91:
                        LogUtils.INSTANCE.e("getAttendCheck1:","success");
                        if(checkBeanList.size() > 0){
                            isCheckData.setVisibility(View.GONE);
                        }else{
                            isCheckData.setVisibility(View.VISIBLE);
                        }
                        checkAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        if(month <10){
            if(day < 10){
                return year+"-"+"0"+month+"-"+"0"+day;
            }else{
                return year+"-"+"0"+month+"-"+day;
            }
        }else{
            if(day < 10){
                return year+"-"+month+"-"+"0"+day;
            }else{
                return year+"-"+month+"-"+day;
            }
        }
    }

    private void initWight() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        checkRecycler.setLayoutManager(linearLayoutManager);
        checkAdapter = new CheckAdapter(getContext(),checkBeanList);
        checkRecycler.setAdapter(checkAdapter);

        today.setChecked(true);
        startdate.setText(current);
        enddata.setText(current);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");

    }

    @OnClick(R.id.today)
    void today() {
        startdate.setText(current);
        enddata.setText(current);
        week.setChecked(false);
        mon.setChecked(false);
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.week)
    void week() {
        if(day<7){
            switch (month){
                case 2:
                case 4:
                case 6:
                case 9:
                    startdate.setText(year+"-0"+(month -1)+"-"+(31 - (7 - day)));
                    break;
                case 11:
                    startdate.setText(year+"-"+(month -1)+"-"+(31 - (7 - day)));
                    break;
                case 3:
                case 5:
                case 7:
                case 8:
                    startdate.setText(year+"-0"+(month -1)+"-"+(30 - (7 - day)));
                    break;
                case 10:
                case 12:
                    startdate.setText(year+"-"+(month -1)+"-"+(30 - (7 - day)));
                    break;
                case 1:
                    startdate.setText((year-1)+"-"+12+"-"+(31 - (7 - day)));
                    break;
            }
        }else{
            if(month<10){
                if(day < 10){
                    if(day == 7){
                        startdate.setText(year+"-0"+month+"-"+"01");
                    }else{
                        startdate.setText(year+"-0"+month+"-"+"0"+(day-7));
                    }
                }else{
                    startdate.setText(year+"-0"+month+"-"+(day-7));
                }
            }else {
                if(day < 10){
                    if(day == 7){
                        startdate.setText(year+"-"+month+"-"+"01");
                    }else{
                        startdate.setText(year+"-"+month+"-"+"0"+(day-7));
                    }
                }else{
                    if(day >= 17){
                        startdate.setText(year+"-"+month+"-"+(day-7));
                    }else{
                        startdate.setText(year+"-"+month+"-0"+(day-7));
                    }

                }
            }
        }
        enddata.setText(current);
        today.setChecked(false);
        mon.setChecked(false);
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.month)
    void month() {
        switch (month){
            case 1:
                if(day < 10){
                    startdate.setText((year-1)+"-"+12+"-"+"0"+day);
                }else{
                    startdate.setText((year-1)+"-"+12+"-"+day);
                }
                break;
            default:
                if(month<=10){
                    if(day < 10){
                        startdate.setText(year+"-0"+(month-1)+"-"+"0"+day);
                    }else{
                        startdate.setText(year+"-0"+(month-1)+"-"+day);
                    }
                }else {
                    if(day <10){
                        startdate.setText(year+"-"+(month-1)+"-"+"0"+day);
                    }else{
                        startdate.setText(year+"-"+(month-1)+"-"+day);
                    }
                }
                break;
        }
        enddata.setText(current);
        today.setChecked(false);
        week.setChecked(false);
    }

    @OnClick(R.id.find)
    void find() {
        String start = startdate.getText().toString();
        String end = enddata.getText().toString();
        String WaterDate = start+"a"+end;
        int  Status = 1;
        String Flag = "";
        int page = 1;
        int rows = 30;
        if(today.isChecked()){
            Status = 1;
        }else if(week.isChecked()){
            Status = 2;
        }else if(mon.isChecked()){
            Status = 3;
        }
        Flag = setFlag(Flag);
        String data = "WaterDate="+WaterDate+"&Status="+Status+"&Flag="+Flag+"&page="+page+"&rows="+rows;
        Log.e("attend",data);
        progressDialog.show();
        getAttendCheeck(WaterDate, Status, Flag, page, rows, attenCookie);               //获取考勤信息;
    }

    private void getAttendCheeck(String waterDate, int status, String flag, int page, int rows, String attenCookie) {

        LogUtils.INSTANCE.e("2:","success");

        GetAttendCheck.INSTANCE.getAttendCheck(waterDate, status, flag, page, rows, attenCookie, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GetAttendCheck",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandleAttCheck.INSTANCE.hanleAttCheck(response);
                progressDialog.dismiss();
            }
        });
    }

    private String setFlag(String flag) {
        if(normal.isChecked()){
            flag = "1";
        }else if(late.isChecked()){
            flag = "2";
        }else if(absence.isChecked()){
            flag = "3";
        }
        if(normal.isChecked()&&late.isChecked()){
            flag = "1a2";
        }else if(normal.isChecked()&&absence.isChecked()){
            flag = "1a3";
        }else if(late.isChecked()&&absence.isChecked()){
            flag = "2a3";
        }
        if(normal.isChecked()&&late.isChecked()&&absence.isChecked()){
            flag = "1a2a3";
        }
        if(!normal.isChecked()&&!late.isChecked()&&!absence.isChecked()){
            flag = "";
        }
        return flag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        checkBeanList.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        checkHandler.removeCallbacksAndMessages(0);
    }
}
