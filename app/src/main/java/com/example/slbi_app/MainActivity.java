package com.example.slbi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    String ShowOrHideWebViewInitialUse = "show";
    private WebView mywebView;
    private ProgressBar spinner;
    private String urlOfWeb = "http://catalog.slbi.lk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide app bar
        getSupportActionBar().hide();

        mywebView = (WebView) findViewById(R.id.webview);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        WebSettings webSettings= mywebView.getSettings();
        // Line of Code for opening links in app
        // mywebView.setWebViewClient(new WebViewClient());
        mywebView.setWebViewClient(new CustomWebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        mywebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        mywebView.loadUrl(urlOfWeb);
    }

    // This allows for a splash screen
    // (and hide elements once the page loads)
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            ShowOrHideWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);

            view.setVisibility(mywebView.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

    public void onBackPressed() {
        if(mywebView.canGoBack())
        {
            mywebView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}
