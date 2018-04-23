package com.example.xiyou3g.playxiyou.MeFragment;

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

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance
 * on 2017/7/12.
 */

public class MajorFragment extends Fragment {

    @BindView(R.id.bneed)
    NumberRunningTextView bneed;

    @BindView(R.id.bget)
    NumberRunningTextView bget;

    @BindView(R.id.bunget)
    NumberRunningTextView bunget;

    @BindView(R.id.bwant)
    NumberRunningTextView bwant;

    @BindView(R.id.xneed)
    NumberRunningTextView xneed;

    @BindView(R.id.xget)
    NumberRunningTextView xget;

    @BindView(R.id.xunget)
    NumberRunningTextView xunget;

    @BindView(R.id.xwant)
    NumberRunningTextView xwant;

    private ProgressDialog progressDialog ;
    private static int flag = 0;
    public static MajorHandler majorHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("MajorFragment","onCreateView");
        View view = inflater.inflate(R.layout.major_fragment,container,false);
        ButterKnife.bind(this, view);
        if(majorBeanList.size()!=0){
            initWight();
            if(flag == 1){
                progressDialog.dismiss();
                flag = 0;
            }
        }
        majorHandler = new MajorHandler(progressDialog);
        return view;
    }

    public class MajorHandler extends Handler {
        private WeakReference<ProgressDialog> dialogWeakReference;

        MajorHandler(ProgressDialog dialog) {
            dialogWeakReference = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            ProgressDialog dialog = dialogWeakReference.get();
            if (dialog != null) {
                switch (msg.what){
                    case 5:
                        initWight();
                        dialog.dismiss();
                        flag = 0;
                        break;
                }
            }
        }
    }

    private void initWight() {
        majorHandler.postDelayed(new Runnable() {
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
            new GetMajorData();
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
