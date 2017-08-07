package com.example.xiyou3g.playxiyou.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.playxiyou.R;
import static com.example.xiyou3g.playxiyou.Content.EduContent.*;

/**
 * Created by Lance on 2017/7/14.
 */

public class PersInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ImageView sImag;
    private TextView sname;
    private TextView snum;
    private TextView sclass;
    private TextView sacade;
    private TextView smajor;
    private TextView sedu;
    private TextView syear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.person_activity);
        initWight();
    }

    private void initWight() {
        back = (ImageView) findViewById(R.id.back);
        sImag = (ImageView) findViewById(R.id.stusex);
        sname = (TextView) findViewById(R.id.sname);
        snum = (TextView) findViewById(R.id.snum);
        sclass = (TextView) findViewById(R.id.sclass);
        sacade = (TextView) findViewById(R.id.sacade);
        smajor = (TextView) findViewById(R.id.smajor);
        sedu = (TextView) findViewById(R.id.sedu);
        syear = (TextView) findViewById(R.id.syear);

        if(stuSex.equals("男")){
            sImag.setImageResource(R.mipmap.man);
        }else{
            sImag.setImageResource(R.mipmap.women);
        }
        sname.setText(stuname);
        snum.setText(stuid);
        sclass.setText(stuclass);
        sacade.setText(stuacademy);
        smajor.setText(stumajor);
        sedu.setText(stueducation);
        syear.setText(stuYear);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
