package com.example.xiyou3g.playxiyou.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiyou3g.playxiyou.R;

/**
 * Created by Lance on 2017/11/7.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(i);
                SplashActivity.this.finish();
            }
        },1500);
    }
}
