package com.arduino.capacitacion.gabriel.ciclodevidaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Activity 1", "Método onCreate()");

        btnPrincipal = findViewById(R.id.btnIrActivity);
        btnPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, ActividadDos.class);
                startActivity(intento);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity 1", "Método onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity 1", "Método onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity 1", "Método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity 1", "Método onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity 1", "Método onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity 1", "Método onDestroy()");
    }

}
