package com.android.capacitacion.gabriel.adaptadorpersonalizado;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Lugar> lugares;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lugares = new ArrayList<>();
        lugares.add(new Lugar("Mirador del Rio", "Mirador ubicado a un costado del Rio principal de Cataluña.", R.drawable.lugar1));
        lugares.add(new Lugar("Cafetería \"Aroma y Tapas\"", "Tapas y café, pintoresco lugar ubicado al norte de Madrid en Mostones.",R.drawable.lugar2));
        lugares.add(new Lugar("Bar Jirani", "Bar de ambiente bohemio, ubicado en la península Ibérica del sur de España.",R.drawable.lugar3));

        lista = findViewById(R.id.lista_lugares);

        lista.setAdapter(new LugarAdaptador());


    }

    class LugarAdaptador extends ArrayAdapter<Lugar>{

        LugarAdaptador(){
            super(MainActivity.this,R.layout.row_lugar,lugares);
        }



        //Inflado de vistas
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.row_lugar, parent , false);

            ImageView imagenLugar = row.findViewById(R.id.imagen_lugar);
            TextView nombreLugar = row.findViewById(R.id.nombre_lugar);
            TextView descripcionLugar = row.findViewById(R.id.descripcion_lugar);

            Lugar lugarActual = lugares.get(position);

            imagenLugar.setImageResource(lugarActual.getImagen());
            nombreLugar.setText(lugarActual.getNombre());
            descripcionLugar.setText(lugarActual.getDescripcion());

            Button dislike = row.findViewById(R.id.dislike_lugar);
            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, ":(", Toast.LENGTH_SHORT).show();
                }
            });

            return row;
        }
    }

}
