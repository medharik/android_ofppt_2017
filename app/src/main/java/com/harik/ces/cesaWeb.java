package com.harik.ces;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.harik.lenovo2017.activits.R;

public class cesaWeb extends Activity {
    //declaration d'une webview
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesa_web);
        //liaison entre webview de java et celle de l'interface graphique
        //en se basant su l'id (ALT + ENTREE pour corriger)
        web = (WebView) findViewById(R.id.webcesa);
        //activation javacsript dana la webview
        web.getSettings().setJavaScriptEnabled(true);
        //chargement de la page en spécifiaqnt le lien
        web.loadUrl("http://eurosport.fr");
    }

    //oncreate => onstart => onresume
//ondestroy => onstop => onpause
}
