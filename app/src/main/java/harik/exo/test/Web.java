package harik.exo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.harik.lenovo2017.activits.R;

public class Web extends AppCompatActivity {
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web= (WebView) findViewById(R.id.wb);
        Intent i=getIntent();
        if (i.hasExtra(FilterActivity2.MON_LIEN)){
            web.getSettings().setJavaScriptEnabled(true);
            web.loadUrl(i.getStringExtra(FilterActivity2.MON_LIEN));


        }

    }
}
