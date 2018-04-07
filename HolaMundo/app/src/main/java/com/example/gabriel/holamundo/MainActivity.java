package com.example.gabriel.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Mñetodo de la clase appcompact activity, se le pasa nuestro layout

        final EditText peso = (EditText) findViewById(R.id.et_peso);  //El metodo recibe una vista y debe hacer un cast
        final EditText altura = findViewById(R.id.et_altura);     //A partir de la ultima versión de Android Studio el cast ya no es necesario.
        //EditText sexo = findViewById(R.id.et_sexo);
        final TextView resultado = findViewById(R.id.tv_resultado);
        Button calcular = findViewById(R.id.btn_calcular);

        calcular.setOnClickListener(new View.OnClickListener() {    //Programación Orientada a Eventos
            @Override
            public void onClick(View v) {
                double pesoIngresado = Double.parseDouble(peso.getText().toString());
                double pesoAltura = Double.parseDouble(altura.getText().toString());
                //String txtSexo = sexo.getText().toString();
                double imc = pesoIngresado / (pesoAltura * pesoAltura);
                resultado.setText("Tu IMC es: "+imc);
                peso.setText("");
                altura.setText("");

            }
        });


    }
}
