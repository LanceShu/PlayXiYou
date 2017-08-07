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

import com.chaychan.viewlib.NumberRunningTextView;
import com.example.xiyou3g.playxiyou.HttpRequest.GetMajorData;
import com.example.xiyou3g.playxiyou.R;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/12.
 */

public class MajorFragment extends Fragment {

    private NumberRunningTextView bneed;
    private NumberRunningTextView bget;
    private NumberRunningTextView bunget;
    private NumberRunningTextView bwant;
    private NumberRunningTextView xneed;
    private NumberRunningTextView xget;
    private NumberRunningTextView xunget;
    private NumberRunningTextView xwant;

    private View view;
    private ProgressDialog progressDialog ;
    private  int flag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("MajorFragment","onCreateView");
        view = inflater.inflate(R.layout.major_fragment,container,false);
        if(majorBeanList.size()!=0){
            initWight(view);
            if(flag == 1){
                progressDialog.dismiss();
                flag = 0;
            }
        }
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 5:
                        initWight(view);
                        progressDialog.dismiss();
                        flag = 0;
                        break;
                }
            }
        };
        return view;
    }

    private void initWight(View view) {
        bneed = (NumberRunningTextView) view.findViewById(R.id.bneed);
        bget = (NumberRunningTextView) view.findViewById(R.id.bget);
        bunget = (NumberRunningTextView) view.findViewById(R.id.bunget);
        bwant = (NumberRunningTextView) view.findViewById(R.id.bwant);
        xneed = (NumberRunningTextView) view.findViewById(R.id.xneed);
        xget = (NumberRunningTextView) view.findViewById(R.id.xget);
        xunget = (NumberRunningTextView) view.findViewById(R.id.xunget);
        xwant = (NumberRunningTextView) view.findViewById(R.id.xwant);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bneed.setContent(majorBeanList.get(0).getmNeedScore());
                bget.setContent(majorBeanList.get(0).getmGetScore());
                bunget.setContent(majorBeanList.get(0).getmUngetScore());
                bwant.setContent(majorBeanList.get(0).getmWantScore());
                xneed.setContent(majorBeanList.get(1).getmNeedScore());
                xget.setContent(majorBeanList.get(1).getmGetScore());
                xunget.setContent(majorBeanList.get(1).getmUngetScore());
                xwant.setContent(majorBeanList.get(1).getmWantScore());
            }
        },500);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("MajorFragment","onAttach");
        if(!stuname.equals("null")){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("正在努力加载...");
            progressDialog.show();
            flag = 1;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new GetMajorData().run();
                }
            },800);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MajorFragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("MajorFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("MajorFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("MajorFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("MajorFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("MajorFragment","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("MajorFragment","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MajorFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("MajorFragment","onDetach");
    }

}
