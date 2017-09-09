package com.example.xiyou3g.playxiyou.AttendFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.HttpRequest.GetAttendCheck;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleAttCheck;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;
import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance on 2017/7/20.
 */

public class ACheckFragment extends Fragment implements View.OnClickListener{

    private View view;
    private TextView startdate;
    private TextView enddata;
    private CheckBox today;
    private CheckBox week;
    private CheckBox mon;
    private CheckBox normal;
    private CheckBox late;
    private CheckBox absence;
    private Button find;

    private int year;
    private int month;
    private int day;
    private String current;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.atten_check_fragment,container,false);
        current = getCurrentDay();

        initWight(view);
        return view;
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
        }
        return year+"-"+month+"-"+day;
    }

    private void initWight(View view) {

        startdate = (TextView) view.findViewById(R.id.startdate);
        enddata = (TextView) view.findViewById(R.id.enddate);
        today = (CheckBox) view.findViewById(R.id.today);
        week = (CheckBox) view.findViewById(R.id.week);
        mon = (CheckBox) view.findViewById(R.id.month);
        normal = (CheckBox) view.findViewById(R.id.anormal);
        late = (CheckBox) view.findViewById(R.id.late);
        absence = (CheckBox) view.findViewById(R.id.absence);
        find = (Button) view.findViewById(R.id.find);

        today.setChecked(true);
        startdate.setText(current);
        enddata.setText(current);

        startdate.setOnClickListener(this);
        enddata.setOnClickListener(this);
        find.setOnClickListener(this);

        today.setOnClickListener(this);
        week.setOnClickListener(this);
        mon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.today:
                startdate.setText(current);
                enddata.setText(current);
                week.setChecked(false);
                mon.setChecked(false);
                break;
            case R.id.week:
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
                            startdate.setText(year+"-0"+month+"-"+"0"+(day-7));
                        }else{
                            startdate.setText(year+"-0"+month+"-"+(day-7));
                        }
                    }else {
                        if(day < 10){
                            startdate.setText(year+"-"+month+"-"+"0"+(day-7));
                        }else{
                            startdate.setText(year+"-"+month+"-"+(day-7));
                        }
                    }
                }
                enddata.setText(current);
                today.setChecked(false);
                mon.setChecked(false);
                break;
            case R.id.month:
                switch (month){
                    case 1:
                        if(day < 10){
                            startdate.setText((year-1)+"-"+12+"-"+"0"+day);
                        }else{
                            startdate.setText((year-1)+"-"+12+"-"+day);
                        }
                        break;
                    default:
                        if(month<10){
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
                break;

            case R.id.find:
                String start = startdate.getText().toString();
                String end = enddata.getText().toString();
                String WaterDate = start+"a"+end;
                int  Status = 1;
                String Flag = "";
                int page = 1;
                int rows = 50;
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
                getAttendCheeck(WaterDate,Status,Flag,page,rows,attenCookie);               //获取考勤信息;
                break;
        }
    }

    private void getAttendCheeck(String waterDate, int status, String flag, int page, int rows, String attenCookie) {

        GetAttendCheck.INSTANCE.getAttendCheck(waterDate, status, flag, page, rows, attenCookie, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GetAttendCheck",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandleAttCheck.INSTANCE.hanleAttCheck(response);
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
}
