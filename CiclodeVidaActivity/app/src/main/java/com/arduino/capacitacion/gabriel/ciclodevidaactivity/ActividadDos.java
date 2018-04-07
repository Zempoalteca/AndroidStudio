package com.arduino.capacitacion.gabriel.ciclodevidaactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by gabriel on 28/03/18.
 */

public class ActividadDos extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity 2","-----> Método onCreate()");
        setContentView(R.layout.activity_dos); //Asociamos la actividad con un xml

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity 2", "-----> Método onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity 2", "-----> Método onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity 2", "-----> Método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity 2", "-----> Método onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity 2", "-----> Método onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity 2", "-----> Método onDestroy()");
    }
}
