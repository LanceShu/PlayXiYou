package com.example.xiyou3g.playxiyou.MeFragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.example.xiyou3g.playxiyou.R
import kotlinx.android.synthetic.main.project_activity.*
import kotlinx.android.synthetic.main.siliu_info.*
import java.util.*

/**
 * Created by Lance on 2017/10/7.
 */

class ProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_activity)
        pback!!.setOnClickListener {
            finish()
        }
    }
}
