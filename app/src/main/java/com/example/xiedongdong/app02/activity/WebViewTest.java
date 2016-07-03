package com.example.xiedongdong.app02.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.xiedongdong.app02.R;

/**
 * Created by xiedongdong on 16/6/12.
 */
public class WebViewTest extends Activity {
    WebView wv_webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_test);

        Intent intent=getIntent();
        String url=intent.getStringExtra("Url");
        Log.e("WebViewTest",url);

        wv_webView=(WebView)findViewById(R.id.wv_webView);
        wv_webView.setWebViewClient(new WebViewClient());
        wv_webView.getSettings().setJavaScriptEnabled(true);
        wv_webView.loadUrl(url);

    }

}
