package com.teamDroiders.ladybuddy;



import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class webactivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_webactivity);
        webView=findViewById (R.id.webview);
        progressBar=findViewById (R.id.webprog);

        webView.loadUrl ("https://serveseek.online/community/");
        webView.setWebViewClient (new WebViewClient ());
        WebSettings webSettings =webView.getSettings ();
        webSettings.setJavaScriptEnabled (true);
        webView.setWebViewClient (new WebViewClient () {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility (View.VISIBLE);
                setTitle ("Loading...");
                super.onPageStarted (view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility (View.GONE);
                setTitle (view.getTitle ());
                super.onPageFinished (view, url);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack ()){
            webView.goBack ();
        }else{
            super.onBackPressed ();
        }

    }
}
