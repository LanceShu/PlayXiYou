package com.example.xiyou3g.playxiyou.AttendFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xiyou3g.playxiyou.Activity.AttenLoginActivity;
import com.example.xiyou3g.playxiyou.Activity.MainActivity;
import com.example.xiyou3g.playxiyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance
 * on 2017/7/18.
 */

public class AttendUnlogFragment extends Fragment {
    @BindView(R.id.aunlogin)
    Button isUnlogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.atten_unlogin_fragment,container,false);
        ButterKnife.bind(this, view);

//        attenHandler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what){
//                    case 11:
//                        onDestroy();
//                        Log.e("loginsuccess","alogin");
//                        replaceFragment(new AttendLogFragment());
//                        break;
//                }
//            }
//        };
        return view;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container,fragment);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.aunlogin)
    void unLogin() {
        Intent intent = new Intent(getContext(),AttenLoginActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }
}
