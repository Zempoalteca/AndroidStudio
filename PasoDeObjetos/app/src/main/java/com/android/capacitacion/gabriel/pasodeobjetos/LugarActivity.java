package com.android.capacitacion.gabriel.pasodeobjetos;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        Lugar lugarRecibido = (Lugar) getIntent().getSerializableExtra("lugar");
        setTitle(lugarRecibido.getNombre());

        TextView nombre = findViewById(R.id.nombre_lugar);
        TextView descripcion = findViewById(R.id.descrpcion_lugar);
        ImageView imagen = findViewById(R.id.imagen_lugar);

        nombre.setText(lugarRecibido.getNombre());
        descripcion.setText(lugarRecibido.getDescripcion());
        imagen.setImageResource(lugarRecibido.getImagen());
    }
}
