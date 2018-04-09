package zempoalteca.com.pendientesconmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO = Menu.FIRST;
    public static final int OPCION_DOS = Menu.FIRST+1;
    public static final int OPCION_TRES = Menu.FIRST+2;
    public static final int OPCION_CUATRO = Menu.FIRST+3;

    //Se agregar los elementos visuales
    ListView listaPendientes;
    ListView listaTerminandos;
    ArrayList<Pendiente> pendientes;
    ArrayList<Pendiente> terminados;
    public static final int AGREGAR_PENDIENTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se asocia los elementos visuales con la logica
        pendientes = new ArrayList<>();
        terminados = new ArrayList<>();
        listaPendientes = findViewById(R.id.lista_pendientes);
        listaTerminandos = findViewById(R.id.lista_terminadas);

        //Se agregan algunos pendientes
        agregaPendientes();

        listaPendientes.setAdapter(new PendienteAdapter());
        registerForContextMenu(listaPendientes);

    }

    /******************** Menu ********************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(OPCION_UNO,OPCION_UNO,Menu.NONE,"Nuevo pendiente");
        menu.add(OPCION_DOS,OPCION_DOS,Menu.NONE,"Salir");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case OPCION_UNO:
                //Se crea un nuevo intento
                Intent intento = new Intent(MainActivity.this, AgregarPendienteActivity.class);
                //Se inicia una actividad con espera de un resultado, en este caso un objeto Pendiente
                startActivityForResult(intento, AGREGAR_PENDIENTE);
                break;
            case OPCION_DOS:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /******************** Menu contextual ********************/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE,OPCION_TRES,Menu.NONE, "Editar elemento");
        menu.add(Menu.NONE,OPCION_CUATRO,Menu.NONE, "Eliminar elemento");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo  elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case OPCION_TRES:
                break;
            case OPCION_CUATRO:
                //caricaturas.remove(elemento.position);
                //adaptador.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    class PendienteAdapter extends ArrayAdapter<Pendiente>{

        PendienteAdapter(){
            super(MainActivity.this, R.layout.row_tarea, pendientes);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View row = getLayoutInflater().inflate(R.layout.row_tarea, parent, false);

            //Se asocian los elementos visuales con la logica
            ImageView imagenCategoria = row.findViewById(R.id.iv_categoria);
            TextView textoTitulo = row.findViewById(R.id.tv_titulo);
            TextView textoFecha = row.findViewById(R.id.tv_fecha);
            final CheckBox hecho = row.findViewById(R.id.cb_hecha);

            Pendiente pendiente_actual = pendientes.get(position);

            imagenCategoria.setImageResource(pendiente_actual.getImagenPendiente());
            textoTitulo.setText(pendiente_actual.getTitulo());
            textoFecha.setText(pendiente_actual.getFecha().toString());
            //hecho.setSelected(true);
            hecho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hecho.isChecked()){
                        pendientes.remove(position);
                        //notifyDataSetChanged();
                    }
                }
            });

            return row;
        }
    }

    private void agregaPendientes() {
        pendientes.add(new Pendiente(R.drawable.categoria_salud,"Cita con el doctor","Revision anual de mi higado",
                new Date(2018,05,17), "Salud", 30));
        pendientes.add(new Pendiente(R.drawable.categoria_amigos,"Ver a Salomon","Hablar con el acerca de la fiesta de fin de curso",
                new Date(5422L),"Amigos",20));
        pendientes.add(new Pendiente(R.drawable.categoria_contactar_a,"LLamar a la Profesora Roman","LLamar a la doctora romar para cambiar la cita de trabajo",
                new Date(10418L), "Contactar a",7));
        pendientes.add(new Pendiente(R.drawable.categoria_trabajo, "Terminar la aplicación de Simulación","Terminar la aplicacion del curso de capacitacion de Android",
                new Date(10518L),"Trabajo",33));
    }

}
