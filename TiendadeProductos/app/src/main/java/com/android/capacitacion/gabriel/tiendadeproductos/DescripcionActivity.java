package com.android.capacitacion.gabriel.tiendadeproductos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DescripcionActivity extends AppCompatActivity {

    //Se declaran los elementos a usar en la lógica
    ImageView imagenCurso;
    TextView nombreCurso;
    TextView descripcion;
    TextView requisitos;
    TextView precio;
    TextView nombreInstructor;
    ImageView imagenInstructor;
    RatingBar rating;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        //Se recibe el objeto pasado por el intento
        Curso cursoRecibido = (Curso) getIntent().getSerializableExtra("curso");

        //Se asocia los elementos visuales con la lógica
        imagenCurso = findViewById(R.id.imagenCurso);
        nombreCurso = findViewById(R.id.tv_nombreCurso);
        descripcion = findViewById(R.id.tv_descripcion);
        requisitos = findViewById(R.id.tv_descripcion);
        precio = findViewById(R.id.tv_precio);
        nombreInstructor = findViewById(R.id.tv_nombreInstructor);
        imagenInstructor = findViewById(R.id.imagen_instructor);
        rating = findViewById(R.id.rating);
        btnAgregar = findViewById(R.id.btn_Agregar);

        //Cargamos la información recibida por medio del Intent
        imagenCurso.setImageResource(cursoRecibido.getImagenCurso());
        nombreCurso.setText(cursoRecibido.getNombreCurso());
        descripcion.setText(cursoRecibido.getDescripcion());
        requisitos.setText(cursoRecibido.getRequisitos());
        precio.setText(String.valueOf(cursoRecibido.getCosto()));
        nombreInstructor.setText(cursoRecibido.getNombreInstructor());
        imagenInstructor.setImageResource(cursoRecibido.getImagenInstructor());
        rating.setRating((float)cursoRecibido.getPuntuación());

    }
}
