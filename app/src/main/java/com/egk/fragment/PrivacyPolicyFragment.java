package com.egk.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.egk.egk.R;


public class PrivacyPolicyFragment extends Fragment {

    private WebView webView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_privacy_policy, container, false);

        webView = (WebView)v. findViewById(R.id.webview);
        webView.loadUrl("https://egknow.com/Privacy.html");

        return v;
    }


}
