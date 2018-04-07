package com.android.capacitacion.gabriel.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO = Menu.FIRST;
    public static final int OPCION_DOS = Menu.FIRST+1;
    public static final int OPCION_TRES = Menu.FIRST+2;
    public static final int OPCION_CUATRO = Menu.FIRST+3;

    ListView lista;
    ArrayAdapter<String> adaptador;
    ArrayList<String> caricaturas;
    TextView textoVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);
        textoVacio = findViewById(R.id.texto_vacio);
        lista.setEmptyView(textoVacio);         //Para mostrar algo cuando no hay elementos
        caricaturas = new ArrayList<>();
        caricaturas.add("Los padrinos mágicos");
        caricaturas.add("Bob esponja");
        caricaturas.add("Cowboy Depo");
        caricaturas.add("Rick y Morty");
        caricaturas.add("Hey Arnold!");

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,caricaturas);
        lista.setAdapter(adaptador);
        registerForContextMenu(lista);      //Pierde el OnLongClick


    }

    /********************** MENU CONTEXTUAL ****************/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {       //Menu contextual
        menu.add(Menu.NONE, OPCION_TRES,Menu.NONE, "Editar elemento");
        menu.add(Menu.NONE,OPCION_CUATRO,Menu.NONE, "Eliminar elemento");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo  elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();        //Este elemneto nos da la informacion de que se presioano

        switch (item.getItemId()){
            case OPCION_TRES:
                break;
            case OPCION_CUATRO:
                caricaturas.remove(elemento.position);
                adaptador.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    /*********************** MENU ************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.FIRST+0, OPCION_UNO,Menu.NONE,"Agregar nueva tarea");
        menu.add(Menu.FIRST+1, OPCION_DOS,Menu.NONE,"Salir del App");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case OPCION_UNO:
                Toast.makeText(this,"Oprimiste el primer menu!", Toast.LENGTH_SHORT).show();
                break;
            case OPCION_DOS:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
