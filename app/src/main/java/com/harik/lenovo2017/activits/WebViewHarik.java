package com.harik.lenovo2017.activits;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
public class WebViewHarik extends Activity {
WebView w;
    private  String URL="http://www.eurosport.fr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_harik);

    w= (WebView) findViewById(R.id.webharik);
        w.setWebViewClient(new WebViewClient(){
            ProgressDialog prd=null;
            long t1;
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(URL);
                return true;
            }
            @Override
            public void onLoadResource(WebView view, String url) {
                t1 = System.currentTimeMillis();
                super.onLoadResource(view, url);
                prd= new ProgressDialog(WebViewHarik.this);
                prd.setTitle("Attente...");
                prd.setMessage("Chargement en cours...");
                prd.show();
                try {
                    Thread.sleep(2000);
                    prd.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(prd.isShowing()){
                    prd.dismiss();
                    prd=null;
                }
            }
        });
        w.getSettings().setJavaScriptEnabled(true);
        w.loadUrl(URL);
    }
    @Override
    public void onBackPressed() {
        if(w.canGoBack()) {
            w.goBack();
        } else {
           super.onBackPressed();
        }
    }

}
