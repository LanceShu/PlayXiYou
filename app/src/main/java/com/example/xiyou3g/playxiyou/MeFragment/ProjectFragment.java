package com.example.xiyou3g.playxiyou.MeFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.playxiyou.Adapter.TestStackAdapter;
import com.example.xiyou3g.playxiyou.DataBean.ProjectBean;
import com.example.xiyou3g.playxiyou.HttpRequest.GetProjectData;
import com.example.xiyou3g.playxiyou.R;
import com.loopeer.cardstack.CardStackView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.xiyou3g.playxiyou.Content.EduContent.ViewStatelist;
import static com.example.xiyou3g.playxiyou.Content.EduContent.handler;
import static com.example.xiyou3g.playxiyou.Content.EduContent.majorBeanList;
import static com.example.xiyou3g.playxiyou.Content.EduContent.proList;
import static com.example.xiyou3g.playxiyou.Content.EduContent.stuname;

/**
 * Created by Lance
 * on 2017/7/12.
 */

public class ProjectFragment extends Fragment implements CardStackView.ItemExpendListener{

    @BindView(R.id.stackview)
    CardStackView cardStackView;

    private TestStackAdapter testStackAdapter;
    private static int flag = 0;
    private static int i =0;

    private static Integer[] item = new Integer[]{R.color.team1,R.color.team2,R.color.team3,
            R.color.team4,R.color.team5,R.color.team6,R.color.team7,R.color.team8};

    private ProgressDialog dialog;
    public static ProjectHandler projectHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(proList.size() == 0){
            dialog = new ProgressDialog(getContext());
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage("正在努力加载...");
            dialog.show();
            getAllProject(ViewStatelist);                        //获取全部培养计划信息;
        }
    }

    private void getAllProject(List<String> list) {
        for(int i =1;i<=8;i++){
            List<ProjectBean> projectBeen = new ArrayList<>();
            proList.add(projectBeen);
            new GetProjectData(i,list);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment,container,false);
        ButterKnife.bind(this, view);
        initWight();
        if(majorBeanList.size()!=0){
            initWight();
            if(flag == 1){
                dialog.dismiss();
                flag = 0;
            }
        }
        projectHandler = new ProjectHandler(dialog, testStackAdapter);
        return view;
    }

    public static class ProjectHandler extends Handler {
        private WeakReference<ProgressDialog> dialogWeakReference;
        private WeakReference<TestStackAdapter> testStackAdapterWeakReference;

        ProjectHandler(ProgressDialog dialog, TestStackAdapter adapter) {
            dialogWeakReference = new WeakReference<>(dialog);
            testStackAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void handleMessage(Message msg) {
            ProgressDialog dialog = dialogWeakReference.get();
            TestStackAdapter testStackAdapter  = testStackAdapterWeakReference.get();
            if (dialog != null && testStackAdapter != null) {
                switch (msg.what){
                    case 6:
                        i ++;
                        if(i == 8){
                            testStackAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        flag = 0;
                        break;
                }
            }
        }
    }

    private void initWight() {
        testStackAdapter = new TestStackAdapter(getContext());
        cardStackView.setAdapter(testStackAdapter);
        cardStackView.setItemExpendListener(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testStackAdapter.updateData(Arrays.asList(item));
            }
        },150);
    }

    @Override
    public void onItemExpend(boolean expend) {

    }
}
