package com.example.xiyou3g.playxiyou.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.xiyou3g.playxiyou.R
import kotlinx.android.synthetic.main.siliu_info.*

/**
 * Created by Lance
 * on 2017/9/5.
 */

class SiliuActivitySe : AppCompatActivity(){

    var webView :WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.siliuactivity_se)
        initWight()
    }

    private fun initWight(){
        webView = findViewById(R.id.siliu) as WebView
        webView!!.setWebViewClient(WebViewClient())
        val webSettings = webView!!.settings
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
        webView!!.loadUrl("http://weixiao.qq.com/apps/public/cet/index.html")

        sback.setOnClickListener({
            finish()
        })

    }

}