package com.harik.ces;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.harik.lenovo2017.activits.R;

public class palestine extends Activity {
    Button bps;
    WebView wbps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestine);
        bps= (Button) findViewById(R.id.buttonps);
        wbps= (WebView) findViewById(R.id.webps);

        wbps.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        wbps.setWebViewClient(new WebClient());
        wbps.setWebChromeClient(new WebChrome());
        wbps.loadUrl("https://eurosport.fr");

    }
    class  WebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
           // wbps.loadUrl("https://kooora.com");

            return false;
        }
    }
    class WebChrome extends  WebChromeClient{

    }
}
