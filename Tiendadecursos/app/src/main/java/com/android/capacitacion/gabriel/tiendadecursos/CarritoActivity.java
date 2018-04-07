package com.android.capacitacion.gabriel.tiendadecursos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {

    //Se declaran los elementos a usar en la lógica
    ListView lista;
    ArrayAdapter<CursoAdapter> cursosElegidos;
    ImageView imagen;
    TextView nombreCurso;
    TextView nombreInstructor;
    TextView precioCurso;
    Button quitarDeCarrito;
    ArrayList<Curso> cursos_carrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        //Se cambia el titulo a la actividad
        setTitle("Carrito de compra");

        //Se recibe el arreglo que pasa por el intento
        cursos_carrito = (ArrayList<Curso>) getIntent().getSerializableExtra("elegidos");

        //Se asocia la vista con la lógica
        imagen = findViewById(R.id.imagen_curso);
        nombreCurso = findViewById(R.id.nombre_curso);
        nombreInstructor = findViewById(R.id.nombre_instructor);
        precioCurso = findViewById(R.id.precio_curso);
        quitarDeCarrito = findViewById(R.id.btn_quitar);

        lista = findViewById(R.id.listaCarrito);

        lista.setAdapter(new CursoAdapter(cursos_carrito));

    }


    /*public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(MainActivity.this,ActividadDos.class));
        }
        return false;
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent main = new Intent();
            main.putExtra("actualizarCarrito", cursos_carrito);
            setResult(RESULT_OK,main);
            finish();
        }
        return false;
    }

    private class CursoAdapter extends ArrayAdapter<Curso>{

        ArrayList<Curso> cursosEnCarrito;

        CursoAdapter(ArrayList<Curso> cursos_carrito){
            super(CarritoActivity.this, R.layout.row_carrito, cursos_carrito);
            cursosEnCarrito = cursos_carrito;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //Inflamos la vista
            View row = getLayoutInflater().inflate(R.layout.row_carrito,parent,false);
            //Asociamos los elementos visuales con la lógica
            ImageView imagenCurso = row.findViewById(R.id.imagen_curso);
            TextView nombreCurso = row.findViewById(R.id.nombre_curso);
            TextView nombreInstructor = row.findViewById(R.id.nombre_instructor);
            TextView precioCurso = row.findViewById(R.id.precio_curso);
            Button btnQuitar = row.findViewById(R.id.btn_quitar);

            //Se obtiene el objeto
            Curso cursoActual = cursosEnCarrito.get(position);

            //Se agrega la información del curso elegido (a comprar) en el item de la lista del carrito
            imagenCurso.setImageResource(cursoActual.getImagenCurso());
            nombreCurso.setText(cursoActual.getNombreCurso());
            nombreInstructor.setText(cursoActual.getNombreInstructor());
            precioCurso.setText(String.format("$ %1$.2f", cursoActual.getCosto()));

            btnQuitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursosEnCarrito.remove(position);
                    notifyDataSetChanged();
                }
            });


            return row;
        }

    }
}
