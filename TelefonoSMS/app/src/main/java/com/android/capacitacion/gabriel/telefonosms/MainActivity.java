package com.android.capacitacion.gabriel.telefonosms;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText numero;
    EditText texto;
    Button marcar;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamiento de los elementos
        numero = findViewById(R.id.numero);
        texto = findViewById(R.id.texto);
        marcar = findViewById(R.id.btn_llamar);
        enviar = findViewById(R.id.btn_sendSMS);

        //Manera sencilla de solicitar permisos
        //Esto solo se necesita a partir de la API 23
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 12);


        marcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a_marcar = "tel:" + numero.getText().toString();
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(a_marcar)));
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms = SmsManager.getDefault();   //instancia de este manager
                sms.sendTextMessage(numero.getText().toString(), null, texto.getText().toString(),null, null);
            }
        });

    }
}
