package com.android.capacitacion.gabriel.pasodeobjetos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    EditText campo;
    Button actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //String descripcionRecibida = getIntent().getStringExtra("lugar");
        final Lugar lugarRecibido = (Lugar)getIntent().getSerializableExtra("descripcion");
        String descripcionRecibida = lugarRecibido.getDescripcion();
        campo = findViewById(R.id.et_descripcion);
        actualizar = findViewById(R.id.btn_actualizar);

        campo.setText(descripcionRecibida);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();

                //data.setData(Uri.parse(campo.getText().toString()));      //Objeto URI
                lugarRecibido.setDescripcion(campo.getText().toString());
                data.putExtra("objetoNuevo", lugarRecibido);
                setResult(RESULT_OK, data);
                finish();           //Para finalizar una actividad
            }
        });

    }
}
