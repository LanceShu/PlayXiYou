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

import static com.example.xiyou3g.playxiyou.Content.AttenContent.*;

/**
 * Created by Lance
 * on 2017/7/18.
 */

public class AttendUnlogFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Button isUnlogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.atten_unlogin_fragment,container,false);
        initWight(view);

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

    private void initWight(View view) {
        isUnlogin = (Button) view.findViewById(R.id.aunlogin);
        isUnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aunlogin:
                    Intent intent = new Intent(getContext(),AttenLoginActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
        }
    }
}
