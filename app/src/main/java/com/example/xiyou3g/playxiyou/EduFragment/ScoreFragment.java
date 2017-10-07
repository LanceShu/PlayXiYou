package com.example.xiyou3g.playxiyou.EduFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.xiyou3g.playxiyou.Adapter.ScoreAdapter;
import com.example.xiyou3g.playxiyou.Adapter.ScoreTeamAdapter;
import com.example.xiyou3g.playxiyou.DataBean.ScoreYearAndTeam;
import com.example.xiyou3g.playxiyou.HttpRequest.GetScoreData;
import com.example.xiyou3g.playxiyou.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/12.
 *
 */

public class ScoreFragment extends Fragment {

    private RecyclerView scoreRecyc;
    private TextView isData;

    private View view;
    private LinearLayoutManager linearLayoutManager2;
    private TextView current;

    private List<ScoreYearAndTeam> yearList;
    private ScoreAdapter scoreAdapter;

    private ProgressDialog dialog;

    private ImageView bselect;
    private List<String> select;
    private CoordinatorLayout main_layout;

    private SwipeRefreshLayout swipeRefreshLayout;
    private int isrefresh = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dialog = new ProgressDialog(getContext());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在努力加载...");
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.score_fragment,container,false);
        yearList = new ArrayList<>();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 2:
                            initWight(view);
                            Log.e("accept success","666666666666");
                            dialog.dismiss();
                        break;
                    case 4:
                        dialog = new ProgressDialog(getContext());
                        dialog.setMessage("正在努力加载...");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        break;
                }
            }
        };
        if(sYear != 0){
            dialog.dismiss();
        }
        if(yearList.size() == 0 && !stuname.equals("null")){
            getYearAndTeam();
        }
        initWight(view);

        return view;
    }

    //学期的各学期的成绩;
    private void getYearAndTeam() {
        yearList.clear();
        sYear = Integer.parseInt(stuYear);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        if(month >= 9){
            for(int i =0;i<year - sYear;i++){
                sYear = sYear + i;
                ScoreYearAndTeam scoreYearAndTeam1 = new ScoreYearAndTeam();
                scoreYearAndTeam1.setYear(sYear+"-"+(sYear+1));
                scoreYearAndTeam1.setTeam("1");
                yearList.add(scoreYearAndTeam1);
                ScoreYearAndTeam scoreYearAndTeam2 = new ScoreYearAndTeam();
                scoreYearAndTeam2.setYear(sYear+"-"+(sYear+1));
                scoreYearAndTeam2.setTeam("2");
                yearList.add(scoreYearAndTeam2);
            }
//            sYear = sYear + 1;
//            ScoreYearAndTeam scoreYearAndTeam1 = new ScoreYearAndTeam();
//            scoreYearAndTeam1.setYear(sYear+"-"+(sYear+1));
//            scoreYearAndTeam1.setTeam("1");
//            yearList.add(scoreYearAndTeam1);
        }else{
            for(int i =0;i<year - sYear;i++){
                sYear = sYear + i;
                ScoreYearAndTeam scoreYearAndTeam1 = new ScoreYearAndTeam();
                scoreYearAndTeam1.setYear(sYear+"-"+(sYear+1));
                scoreYearAndTeam1.setTeam("1");
                yearList.add(scoreYearAndTeam1);
                ScoreYearAndTeam scoreYearAndTeam2 = new ScoreYearAndTeam();
                scoreYearAndTeam2.setYear(sYear+"-"+(sYear+1));
                scoreYearAndTeam2.setTeam("2");
                yearList.add(scoreYearAndTeam2);
            }
        }
    }

    private void initWight(View view) {

        scoreRecyc = (RecyclerView) view.findViewById(R.id.score_recycler);
        current = (TextView) view.findViewById(R.id.score_time);
        isData = (TextView) view.findViewById(R.id.score_tv);

        bselect = (ImageView) view.findViewById(R.id.bttest);
        main_layout = (CoordinatorLayout) view.findViewById(R.id.score_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.days,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isrefresh = 1;
                new GetScoreData(Year,Team);              //获取成绩信息;
            }
        });

        current.setText(currentScore);

        if(stuname.equals("null")){
            isData.setVisibility(View.VISIBLE);
        }else{
            linearLayoutManager2 = new LinearLayoutManager(getContext());
            linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
            scoreRecyc.setLayoutManager(linearLayoutManager2);

            scoreAdapter = new ScoreAdapter(scoreBeanList);
            scoreRecyc.setAdapter(scoreAdapter);

            select = new ArrayList<>();
            for(int i =yearList.size()-1;i>=0;i--){
                select.add(yearList.get(i).getYear()+"   第"+yearList.get(i).getTeam()+"学期");
            }
            bselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(popupWindow != null &&popupWindow.isShowing()){
                        popupWindow.dismiss();
                        popupWindow = null;
                    }else {
                        showPopupWindow();
                    }
                }
            });
            if(isrefresh == 1){
                swipeRefreshLayout.setRefreshing(false);
                isrefresh = 0;
            }
        }
    }

    private void showPopupWindow() {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow,null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ScoreTeamAdapter scoreTeamAdapter = new ScoreTeamAdapter(yearList);
        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(main_layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.showAsDropDown(bselect);
    }

}
