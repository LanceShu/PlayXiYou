package com.example.xiyou3g.playxiyou.AttendFragment;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiyou3g.playxiyou.Activity.AttenLoginActivity;
import com.example.xiyou3g.playxiyou.Activity.MainActivity;
import com.example.xiyou3g.playxiyou.Adapter.CheckInforAdapter;
import com.example.xiyou3g.playxiyou.Adapter.PieAdapter;
import com.example.xiyou3g.playxiyou.Content.AttenContent;
import com.example.xiyou3g.playxiyou.HttpRequest.GetCheckInfor;
import com.example.xiyou3g.playxiyou.R;
import com.example.xiyou3g.playxiyou.Utils.HandleCheckInfor;
import com.example.xiyou3g.playxiyou.Utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/20.
 */

public class APerFragment extends Fragment {

    private View view;
    private ImageView aimage;
    private TextView aname;
    private TextView asex;
    private TextView anum;
    private TextView aacadem;
    private TextView aphone;
    private TextView amajor;
    private TextView aclass;
    private TextView aidenti;
    private Button aexit;

    private TextView isHasCheckInfor;
    private RecyclerView checkList;
    private CheckInforAdapter adapter;
    public static Handler checkHandler;

    private RecyclerView chartsRecycler;
    private PieAdapter pieAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.INSTANCE.e("Attend:","success");
        if(CheckList.size() == 0){
            GetCheckInfor.INSTANCE.getCheckInfor(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    HandleCheckInfor.INSTANCE.handleCheckInfor(response);
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.atten_per_fragment,container,false);
        initWight(view);

        checkHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 72:
                        //LogUtils.INSTANCE.e("checkInfor:",CheckList.size()+"  "+CheckList.get(2).getCourseName());
                        if(CheckList.size() > 0){
                            isHasCheckInfor.setVisibility(View.GONE);
                            checkList.setVisibility(View.VISIBLE);
                        }
                        adapter = new CheckInforAdapter(CheckList);
                        checkList.setAdapter(adapter);
                        pieAdapter = new PieAdapter(CheckList);
                        chartsRecycler.setAdapter(pieAdapter);
                        break;
                }
            }
        };

        return view;
    }

    private void initWight(View view) {
        aimage = (ImageView) view.findViewById(R.id.attend_image);
        aname = (TextView) view.findViewById(R.id.aname);
        asex = (TextView) view.findViewById(R.id.asex);
        anum = (TextView) view.findViewById(R.id.anum);
        aacadem = (TextView) view.findViewById(R.id.aacade);
        aphone = (TextView) view.findViewById(R.id.aphone);
        amajor = (TextView) view.findViewById(R.id.amajor);
        aclass = (TextView) view.findViewById(R.id.aclass);
        aidenti = (TextView) view.findViewById(R.id.aidentify);
        aexit = (Button) view.findViewById(R.id.aexit);
        isHasCheckInfor = (TextView) view.findViewById(R.id.isCheckInfor);

        if(CheckList.size() > 0){
            isHasCheckInfor.setVisibility(View.GONE);
        }else{
            isHasCheckInfor.setVisibility(View.VISIBLE);
        }

        //考勤信息统计RecycleView;
        checkList = (RecyclerView) view.findViewById(R.id.checkRec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        checkList.setLayoutManager(linearLayoutManager);

        //图表的考勤信息统计Charts;
        chartsRecycler = (RecyclerView) view.findViewById(R.id.chartsRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        chartsRecycler.setLayoutManager(gridLayoutManager);

        if(CheckList.size()>0){
            adapter = new CheckInforAdapter(CheckList);
            checkList.setAdapter(adapter);
            pieAdapter = new PieAdapter(CheckList);
            chartsRecycler.setAdapter(pieAdapter);
        }


        aexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islogin = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("温馨提示：");
                builder.setMessage("注销成功！");
                builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        attenHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e("exitsuccess","click");
//                                FragmentManager manager = getActivity().getSupportFragmentManager();
//                                FragmentTransaction transaction = manager.beginTransaction();
//                                transaction.replace(R.id.main_container,new AttendUnlogFragment());
//                                transaction.commit();
//                            }
//                        },500);
                        Intent intent = new Intent(getContext(),AttenLoginActivity.class);
                        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                        getActivity().finish();
                    }
                }).create().show();
            }
        });

        Glide.with(getContext())
                .load("http://jwkq.xupt.edu.cn:8080/Common/GetPhotoByBH?xh="+attendPerBean.getNum())
                .error(R.drawable.schoollogo)
                .into(aimage);
        aname.setText(attendPerBean.getName());
        asex.setText(attendPerBean.getSex());
        anum.setText(attendPerBean.getNum());
        aacadem.setText(attendPerBean.getAcademy());
        aphone.setText(attendPerBean.getPhone());
        amajor.setText(attendPerBean.getMajor());
        aclass.setText(attendPerBean.getClassroom());
        aidenti.setText(attendPerBean.getIdentity());
    }
}
