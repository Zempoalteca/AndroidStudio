package com.arduino.capacitacion.gabriel.lista1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView seleccionado;
    ListView lista;

    String [] peliculas = { //Lista de pelicualas a aparecer en el ListView
            "Guardians of the Galaxy",
            "COCO",
            "Forest Gump",
            "Fight Club",
            "Dragons",
            "Star Wars"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seleccionado = findViewById(R.id.tv_selection);
        lista = findViewById(R.id.lv_lista);

        //Adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peliculas);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado.setText(peliculas[position]);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, peliculas[position], Toast.LENGTH_SHORT).show();
                return false;

            }
        });

    }
}
