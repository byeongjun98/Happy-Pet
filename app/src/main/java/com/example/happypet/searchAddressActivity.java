package com.example.happypet;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class searchAddressActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchaddress);

        webView = findViewById(R.id.daum_webview);

        // JavaScript 활성화
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);

        // WebViewClient 설정
        webView.setWebViewClient(new WebViewClient());

        // URL 로드
        webView.loadUrl("file:///android_asset/postcode.html");
    }
}