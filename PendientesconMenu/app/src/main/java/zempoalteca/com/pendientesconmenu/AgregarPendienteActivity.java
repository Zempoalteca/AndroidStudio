package zempoalteca.com.pendientesconmenu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AgregarPendienteActivity extends AppCompatActivity {

    private Spinner sp_categorias;
    private EditText fecha;
    private ImageView imagenCategoria;
    String[] categorias = {"Amigos", "Compras", "Contactar a...", "Escuela", "Pareja",
            "Personal", "Salud", "Trabajo", "Tramites", "Familia"};
    int[] listaCategorias = {R.drawable.categoria_amigos,R.drawable.categoria_compras,R.drawable.categoria_contactar_a,R.drawable.categoria_escuela,R.drawable.categoria_pareja,
                                R.drawable.categoria_personal,R.drawable.categoria_salud,R.drawable.categoria_trabajo,R.drawable.categoria_tramites,R.drawable.categorias_familia};
    private EditText titulo;
    private EditText descripcion;
    private Button agregarPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pendiente);

        //Asociar los elementos visuales con la logica
        titulo = findViewById(R.id.et_titulo);
        descripcion = findViewById(R.id.et_descripcion);
        agregarPendiente = findViewById(R.id.btn_agregar);
        imagenCategoria = findViewById(R.id.img_categorias);
        sp_categorias = findViewById(R.id.sp_categorias);
        sp_categorias.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categorias));
        sp_categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imagenCategoria.setImageResource(listaCategorias[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        fecha = findViewById(R.id.et_fecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.et_fecha:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        final Pendiente nuevoPendiente = new Pendiente(R.drawable.categoria_trabajo,"Titulo","descripcion",new Date(23456L),"Trabajo",20);//QUITAR

        agregarPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AgregarPendienteActivity.this, "Se agregara a pendientes!", Toast.LENGTH_SHORT).show();
                //Se crea el intento para pasar la información al Carrito
                Intent pendiente = new Intent();
                //Pasar el pendiente como parametro
                pendiente.putExtra("agregar_pendiente",nuevoPendiente);
                setResult(RESULT_OK, pendiente);
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }
}
