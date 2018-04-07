package com.android.capacitacion.gabriel.tiendadeproductos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Se declaran los elementos a usar en la lógica
    Button btnCarrito;
    TextView itemsCarrito;
    ListView listaCursos;
    ArrayList<Curso> cursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se asocia la vista con la lógica
        btnCarrito = findViewById(R.id.btn_irCarrito);
        itemsCarrito = findViewById(R.id.tv_numItemsCarrito);
        listaCursos = findViewById(R.id.listaCursos);
        cursos = new ArrayList<>();
        //Se agregan los cursos
        agregaCursos();


        listaCursos.setAdapter(new CursoAdapter());



        /********************** Se abre una nueva actividad cada que se presiona un Curso ************************/
        listaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Se crea un intento nuevo
                Intent intento = new Intent(MainActivity.this, DescripcionActivity.class);
                //Se añade la información a compartir con el intento, en este caso un objeto
                intento.putExtra("curso", cursos.get(position));        //Por que serializable la clase?
                startActivity(intento);
            }
        });

        /*********************** Se abre una actividad cada que se quiere visualizar el carrito *************************/
        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Se abre el carrito!", Toast.LENGTH_SHORT).show();
                Intent intento = new Intent(MainActivity.this, CarritoActivity.class);
                startActivity(intento);
            }
        });

    }

    class CursoAdapter extends ArrayAdapter<Curso> {

        CursoAdapter(){super(MainActivity.this, R.layout.row_muestra, cursos);}

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.row_muestra, parent, false);//Para que es?

            //Asociamos los elementos visuales con la lógica
            ImageView imagenCurso = row.findViewById(R.id.imagenCurso);
            TextView nombreCurso = row.findViewById(R.id.tv_nombreCurso);
            TextView nombreInstructor = row.findViewById(R.id.tv_nombreInstructor);
            TextView precioCurso = row.findViewById(R.id.tv_precio);
            RatingBar rating = row.findViewById(R.id.rating);

            //
            Curso cursoActual = cursos.get(position);

            imagenCurso.setImageResource(cursoActual.getImagenCurso());
            nombreCurso.setText(cursoActual.getNombreCurso());
            nombreInstructor.setText(cursoActual.getNombreInstructor());
            precioCurso.setText(String.valueOf(cursoActual.getCosto()));
            rating.setRating((float)cursoActual.getPuntuación());


            return row;
        }
    }


    private void agregaCursos() {
        //Agregamos los 22 cursos
        cursos.add(new Curso(R.drawable.curso1, R.drawable.curso2,
                "Nombre Curso 1",
                "Name Instructor Panchito",
                "vfsjnvjknfdjvnijdfbnvjhdfsbn berbviu f re v fv aebnviudnvundfui",
                "vadskjhvbds vbusafiuvbsiudf vdiuabnciunsdb usdo",
                5000, 3.5));
        cursos.add(new Curso(R.drawable.curso2, R.drawable.curso2,
                "Curso 2",
                "Nombre instructor 2",
                "Este es un curso shingon",
                "Estos son los requisitos",
                10000, 4.35));
    }

}
