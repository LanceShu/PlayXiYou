package com.example.xiyou3g.playxiyou.SportFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.xiyou3g.playxiyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lance
 * on 2017/7/23.
 */

public class SportFragment extends Fragment {

    @BindView(R.id.sport_web)
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sport_fragment,container,false);
        ButterKnife.bind(this, view);
        initWight();
        return view;
    }

    private void initWight() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://yddx.boxkj.com/wx/loginout");
    }
}
