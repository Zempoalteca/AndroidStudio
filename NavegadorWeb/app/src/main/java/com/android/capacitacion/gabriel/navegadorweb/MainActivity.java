package com.android.capacitacion.gabriel.navegadorweb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    //Tiene desactivado la ejecución de código JavaScript
    //Tiene desactivado la sobreescritura de la URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = findViewById(R.id.webView);

        wv.loadUrl("https://www.google.com.mx/");


        //Para activar JavaScript
        wv.getSettings().setJavaScriptEnabled(true);
        //Para sobreescribir la URL
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //Si ponemos false no aparece.... Desea abrir con...
                //return super.shouldOverrideUrlLoading(view, request);
                return false;
            }
        });

    }
}
