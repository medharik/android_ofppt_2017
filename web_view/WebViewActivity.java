package androids.ofppt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.harik.lenovo2017.di2_android.R;

public class WebViewActivity extends Activity {
WebView wv;
    ProgressDialog prd;
    public static final String URL="http://www.kooora.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
 wv= (WebView) findViewById(R.id.wv);
        prd=new ProgressDialog(WebViewActivity.this);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.setWebViewClient(new NotreWebClient());

wv.loadUrl(URL);
    }


    class NotreWebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
      //  Toast.makeText(getApplicationContext(),"debut du chargement ....",Toast.LENGTH_SHORT).show();
            try{

                prd.setTitle("Etat du chargement");
                prd.setMessage("chargement en cours....");
                prd.show();
            }catch (Exception e ){
                Log.e("Erreur page started",e.getMessage());
            }

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            prd.show();

            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
          //  Toast.makeText(getApplicationContext(),"fin du chargement ....",Toast.LENGTH_SHORT).show();
           // prd.setMessage("fin du chargement");
            if(prd!=null  && prd.isShowing()){
             prd.dismiss();
            }
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(URL);
            return true;
        }
    }
}
