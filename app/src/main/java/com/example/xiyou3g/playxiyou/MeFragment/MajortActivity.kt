package com.example.xiyou3g.playxiyou.MeFragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.xiyou3g.playxiyou.R
import kotlinx.android.synthetic.main.major_activity.*

/**
 * Created by Lance on 2017/10/7.
 */

class MajortActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.major_activity)
        mback!!.setOnClickListener {
            finish()
        }
    }
}