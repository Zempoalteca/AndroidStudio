package com.android.capacitacion.gabriel.pasodeobjetos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adaptador;
    ArrayList<Lugar> lugares;
    ListView lista;
    ArrayList<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lugares = new ArrayList<>();
        lugares.add(new Lugar("Mirador del Rio", "Mirador ubicado a un costado del Rio principal de Cataluña.", R.drawable.lugar1));
        lugares.add(new Lugar("Cafetería \"Aroma y Tapas\"", "Tapas y café, pintoresco lugar ubicado al norte de Madrid en Mostones.",R.drawable.lugar2));
        lugares.add(new Lugar("Bar Jirani", "Bar de ambiente bohemio, ubicado en la península Ibérica del sur de España.",R.drawable.lugar3));

        nombres = new ArrayList<>();
        for (Lugar lugar:lugares) {
            nombres.add(lugar.getNombre());
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
        lista = findViewById(R.id.lista_lugares);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this, LugarActivity.class);
                intento.putExtra("lugar", lugares.get(position));
                startActivity(intento);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento2 = new Intent(MainActivity.this, EditarActivity.class);
                intento2.putExtra("descripcion", lugares.get(position));
                lugares.remove(position);
                //adaptador.notifyDataSetChanged();
                startActivityForResult(intento2, 12);//Requesto code: Entero mayor que cero
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12){
            if (resultCode == RESULT_OK){
                Lugar lugarResultado = (Lugar) data.getSerializableExtra("objetoNuevo");
                lugares.add(lugarResultado);
                nombres.clear();
                for (Lugar lugar:lugares) {
                    nombres.add(lugar.getNombre());
                }
                adaptador.notifyDataSetChanged();
            }
        }
    }
}
