package com.example.xiyou3g.playxiyou.EduFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.xiyou3g.playxiyou.Adapter.TestStackAdapter;
import com.example.xiyou3g.playxiyou.DataBean.ProjectBean;
import com.example.xiyou3g.playxiyou.HttpRequest.GetMajorData;
import com.example.xiyou3g.playxiyou.HttpRequest.GetProjectData;
import com.example.xiyou3g.playxiyou.R;
import com.loopeer.cardstack.CardStackView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/12.
 */

public class ProjectFragment extends Fragment implements CardStackView.ItemExpendListener{

    private CardStackView cardStackView;
    private TestStackAdapter testStackAdapter;

    private static Integer[] item = new Integer[]{R.color.team1,R.color.team2,R.color.team3,
            R.color.team4,R.color.team5,R.color.team6,R.color.team7,R.color.team8};

    private ProgressDialog dialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!stuname.equals("null")){
            getAllProject(ViewStatelist);                        //获取全部培养计划信息;
        }
    }

    private void getAllProject(List<String> list) {
        for(int i =1;i<=8;i++){
            List<ProjectBean> projectBeen = new ArrayList<>();
            proList.add(projectBeen);
            new Thread(new GetProjectData(i,list)).start();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment,container,false);
        initWight(view);
        return view;
    }

    private void initWight(View view) {
        cardStackView = (CardStackView) view.findViewById(R.id.stackview);
        testStackAdapter = new TestStackAdapter(getContext());
        cardStackView.setAdapter(testStackAdapter);
        cardStackView.setItemExpendListener(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testStackAdapter.updateData(Arrays.asList(item));
            }
        },200);
    }

    @Override
    public void onItemExpend(boolean expend) {

    }
}
