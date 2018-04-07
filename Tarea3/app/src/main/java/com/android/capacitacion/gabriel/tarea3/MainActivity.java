package com.android.capacitacion.gabriel.tarea3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayAdapter<String> adaptadorListaPendientes;
    ArrayAdapter<String> adaptadorListaTerminados;

    ListView listaPendientes;
    ListView listaterminados;

    ArrayList<String> tareasPendientes;
    ArrayList<String> tareasTerminadas;

    Button btnAgregar;
    EditText tareaNueva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Pendientes");

        //Creo las conexiones entre los elementos visuales y la lógica
        btnAgregar = findViewById(R.id.btn_Agregar);
        tareaNueva = findViewById(R.id.et_tareaNueva);
        listaPendientes = findViewById(R.id.listaPendientes);
        listaterminados = findViewById(R.id.listaTerminados);

        //Inicializo las listas y agrego algunos datos
        tareasPendientes = new ArrayList<>();
        tareasPendientes.add("Terminar tarea 3");
        tareasPendientes.add("Dar de alta cuenta de nomina en Bancomer");
        tareasPendientes.add("Hablarle por telefono a Andrea");
        tareasPendientes.add("Sacar a pasear al perro");

        tareasTerminadas = new ArrayList<>();
        tareasTerminadas.add("Mandar dimensiones de la placa Raspberry 3 a Carla");

        //Se crean los adaptadores para el manejo del contenido de las vistas
        adaptadorListaPendientes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareasPendientes);
        adaptadorListaTerminados = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareasTerminadas);

        //Se agrega el adaptador a la lista para que se llena de información
        listaPendientes.setAdapter(adaptadorListaPendientes);
        listaterminados.setAdapter(adaptadorListaTerminados);


        /*********************** Funcionalidad de los Elementos ***********************/
        //Se añade funcionalidad al botón agregar lista
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevaTarea = tareaNueva.getText().toString();
                if (!nuevaTarea.equals("")){    //Se verifica si hay texto para la nueva tarea a agregar
                    tareasPendientes.add(nuevaTarea);
                    adaptadorListaPendientes.notifyDataSetChanged();
                    tareaNueva.setText("");
                    Toast.makeText(MainActivity.this, "Nueva tarea agregada a Pendientes!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese datos para crear la tarea!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Se añade funcionalidad a los elementos de la lista
        listaPendientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Se mueve la tarea seleccionada de la lista de Pendientes a la lista de Terminadas
                tareasTerminadas.add(tareasPendientes.get(position));
                //Se elimina la tarea seleccionada de la lista de Pendientes
                tareasPendientes.remove(position);
                //Actualizamos la información de ambas listas
                adaptadorListaPendientes.notifyDataSetChanged();
                adaptadorListaTerminados.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Tarea realizada!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }
}
