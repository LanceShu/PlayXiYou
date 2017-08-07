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

/**
 * Created by Lance on 2017/7/23.
 */

public class SportFragment extends Fragment {

    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sport_fragment,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        webView = (WebView) view.findViewById(R.id.sport_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://yddx.boxkj.com/wx/loginout");
    }
}
