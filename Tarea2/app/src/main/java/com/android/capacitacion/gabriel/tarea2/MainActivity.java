package com.android.capacitacion.gabriel.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apPaterno;
    private EditText apMaterno;
    private EditText dia;
    private EditText mes;
    private EditText año;
    private Spinner sexo;
    private Spinner estado;
    private Button limpiar;
    private Button generaRFC;
    private Button generaCURP;
    private TextView resultadoRFC;
    private TextView resultadoCURP;
    private int sexoElegido = 0;
    private int estadoElegido = 0;

    private String nombre_recibido;
    private String apPaterno_recibido;
    private String apMaterno_recibido;
    private String sexo_recibido;
    private String estado_recibido;
    private String dia_recibido;
    private String mes_recibido;
    private String año_recibido;

    private String[] sexoArray = {"Hombre", "Mujer"};
    private String[] estadoArray = {"Aguascalientes",
            "Baja California",
            "Baja California Sur",
            "Campeche",
            "Coahuila de Zaragoza",
            "Colima",
            "Chiapas",
            "Chihuahua",
            "Ciudad de México",
            "Durango",
            "Guanajuato",
            "Guerrero",
            "Hidalgo",
            "Jalisco",
            "México",
            "Michoacán de Ocampo",
            "Morelos",
            "Nayarit",
            "Nuevo León",
            "Oaxaca",
            "Puebla",
            "Querétaro",
            "Quintana Roo",
            "San Luis Potosí",
            "Sinaloa",
            "Sonora",
            "Tabasco",
            "Tamaulipas",
            "Tlaxcala",
            "Veracruz de Ignacio de la Llave",
            "Yucatán",
            "Zacatecas"};
    private String[] renapo = {"AS","BC","BS","CC","CS",
                                "CH","CL","CM","DF","DG",
                                "GT","GR","HG","JC","MC",
                                "MN","MS","NT","NL","OC",
                                "PL","QT","QR","SP","SL",
                                "SR","TC","TS","TL","VZ",
                                "YN","ZS"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.et_nombre);
        apPaterno = findViewById(R.id.et_apPaterno);
        apMaterno = findViewById(R.id.et_apMaterno);
        dia = findViewById(R.id.et_dia);
        mes = findViewById(R.id.et_mes);
        año = findViewById(R.id.et_año);
        sexo = findViewById(R.id.sp_sexo);
        estado = findViewById(R.id.sp_estado);
        limpiar = findViewById(R.id.btn_limpiar);
        generaRFC = findViewById(R.id.btn_rfc);
        generaCURP = findViewById(R.id.btn_curp);
        resultadoRFC = findViewById(R.id.tv_resultadoRFC);
        resultadoCURP = findViewById(R.id.tv_resultadoCURP);


        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                apPaterno.setText("");
                apMaterno.setText("");
                dia.setText("");
                mes.setText("");
                año.setText("");
                resultadoRFC.setText("Tu RFC:");
                resultadoCURP.setText("Tu CURP:");
            }
        });

        generaRFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valida()){
                    nombre_recibido = nombre.getText().toString();
                    apPaterno_recibido = apPaterno.getText().toString();
                    apMaterno_recibido = apMaterno.getText().toString();
                    dia_recibido = dia.getText().toString();
                    mes_recibido = mes.getText().toString();
                    año_recibido = año.getText().toString();
                    String RFC = calculaRFC(apPaterno_recibido,apMaterno_recibido,nombre_recibido,año_recibido,mes_recibido,dia_recibido);
                    resultadoRFC.setText(RFC);
                }else{
                    Toast.makeText(MainActivity.this,"Datos faltantes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        generaCURP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valida()){
                    nombre_recibido = nombre.getText().toString();
                    apPaterno_recibido = apPaterno.getText().toString();
                    apMaterno_recibido = apMaterno.getText().toString();
                    dia_recibido = dia.getText().toString();
                    mes_recibido = mes.getText().toString();
                    año_recibido = año.getText().toString();
                    String RFC = calculaRFC(apPaterno_recibido,apMaterno_recibido,nombre_recibido,año_recibido,mes_recibido,dia_recibido);
                    sexo_recibido = sexoArray[sexoElegido];
                    estado_recibido = renapo[estadoElegido];
                    String CURP = RFC + calculaCURP(sexo_recibido,estado_recibido,apPaterno_recibido,apMaterno_recibido,nombre_recibido);
                    resultadoCURP.setText(CURP);
                }else{
                    Toast.makeText(MainActivity.this,"Datos faltantes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sexoElegido = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                estadoElegido = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterSexo = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexoArray);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setAdapter(adapterSexo);
        ArrayAdapter<String> adapterEstado = new ArrayAdapter(this,android.R.layout.simple_spinner_item,estadoArray);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado.setAdapter(adapterEstado);



    }

    private String calculaCURP(String sexo, String estado, String apPaterno, String apMaterno, String nombre) {
        StringBuilder CURP = new StringBuilder();
        CURP.append(sexo.substring(0,1));
        CURP.append(estado);
        for (int i=2; i<apPaterno_recibido.length();i++){
            char caracter = apPaterno_recibido.charAt(i);
            if (caracter != 'a' && caracter != 'e' && caracter != 'i' && caracter != 'o' && caracter != 'u'){
                CURP.append(caracter);
                break;
            }
        }
        for (int i=1; i<apMaterno_recibido.length(); i++){
            char caracter = apMaterno_recibido.charAt(i);
            if (caracter != 'a' && caracter != 'e' && caracter != 'i' && caracter != 'o' && caracter != 'u'){
                CURP.append(caracter);
                break;
            }
        }
        for (int i=1; i<nombre.length(); i++){
            char caracter = nombre.charAt(i);
            if (caracter != 'a' && caracter != 'e' && caracter != 'i' && caracter != 'o' && caracter != 'u'){
                CURP.append(caracter);
                break;
            }
        }
        return CURP.toString().toUpperCase();
    }

    private String calculaRFC(String apPaterno_recibido, String apMaterno_recibido, String nombre_recibido, String año_recibido, String mes_recibido, String dia_recibido) {
        StringBuilder RFC = new StringBuilder();
        RFC.append(apPaterno_recibido.substring(0,2));
        RFC.append(apMaterno_recibido.charAt(0));
        RFC.append(nombre_recibido.charAt(0));
        String añoRecortado = String.valueOf(año_recibido);
        RFC.append(añoRecortado.substring(añoRecortado.length()-2,añoRecortado.length()));
        RFC.append(mes_recibido);
        RFC.append(dia_recibido);
        return RFC.toString().toUpperCase();
    }

    private boolean valida() {
        if (nombre.getText().toString().equals("") ||
                apPaterno.getText().toString().equals("") ||
                apMaterno.getText().toString().equals("") ||
                dia.getText().toString().equals("") ||
                mes.getText().toString().equals("") ||
                año.getText().toString().equals("")){
            return false;
        }
        return true;
    }
}
