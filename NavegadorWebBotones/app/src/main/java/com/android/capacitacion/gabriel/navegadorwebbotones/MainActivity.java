package com.android.capacitacion.gabriel.navegadorwebbotones;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    EditText url;
    Button btnBuscar;
    Button btnGoBack;
    Button btnGoForward;
    Button btnRefresh;
    private static final String google = "https://www.google.com.mx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos la conexcion entre los objetos visuales y la lógica
        wv = findViewById(R.id.webView);
        url = findViewById(R.id.URL);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnGoBack = findViewById(R.id.btnBack);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnGoForward = findViewById(R.id.btnForward);

        //Por default iniciamos con una URL
        url.setText(google);
        wv.loadUrl(google);

        //Activamos la ejeciución de código JavaScript
        wv.getSettings().setJavaScriptEnabled(true);
        //Activamos que podamos navegar dentro de nuestra aplicación
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlIngresada = url.getText().toString();
                wv.loadUrl(urlIngresada);
                btnGoBack.setEnabled(true);
                btnGoBack.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnback));
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.reload();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.canGoBack()) {
                    wv.goBack();
                    if (!wv.canGoBack()){
                        btnGoBack.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnbackdisable));
                        btnGoBack.setEnabled(false);
                    }
                    btnGoForward.setEnabled(true);
                    btnGoForward.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnforward));
                }else{
                    btnGoBack.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnbackdisable));
                    btnGoBack.setEnabled(false);
                }
            }
        });

        btnGoForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.canGoForward()) {
                    wv.goForward();
                    if (!wv.canGoForward()){
                        btnGoForward.setEnabled(false);
                        btnGoForward.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnforwarddisable));
                    }
                    btnGoBack.setEnabled(true);
                    btnGoBack.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnback));
                }else{
                    btnGoForward.setEnabled(false);
                    btnGoForward.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnforwarddisable));
                }
            }
        });
        btnGoBack.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnbackdisable));
        btnGoForward.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.btnforwarddisable));
    }
}
