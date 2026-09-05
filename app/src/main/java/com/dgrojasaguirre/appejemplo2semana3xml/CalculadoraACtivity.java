package com.dgrojasaguirre.appejemplo2semana3xml;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class CalculadoraACtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ly_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // creo mi boton el cual eredara de xml su id btnCalculadora
        Button btnsuma = findViewById(R.id.btnSuma);
        Button btnresta = findViewById(R.id.btnResta);
        Button btnmultiplicar = findViewById(R.id.btnMultiplicacion);
        Button btndividir = findViewById(R.id.btnDivision);
        TextView respuesta= findViewById(R.id.lbrespuesta);
        respuesta.setText("");

        btnsuma.setOnClickListener(view -> {
            respuesta.setText(calcular("+"));
        });
        btnresta.setOnClickListener(view -> {
            respuesta.setText(calcular("-"));
        });
        btndividir.setOnClickListener(view -> {
            respuesta.setText(calcular("/"));
        });
        btnmultiplicar.setOnClickListener(view -> {
            respuesta.setText(calcular("*"));
        });


        }
    private String calcular(String s){
        TextInputEditText numero1 = findViewById(R.id.txtOperando1);
        TextInputEditText numero2 = findViewById(R.id.txtOperando2);

        if (numero1.getText().toString().trim().isEmpty()) {
            numero1.setError("Ingrese el primer operando");
            return "";
        }
        if (numero2.getText().toString().trim().isEmpty()) {
            numero2.setError("Ingrese el segundo operando");
            return "";
        }

        double n1 = Double.parseDouble(numero1.getText().toString());
        double n2 = Double.parseDouble(numero2.getText().toString());
        double resultado = 0;

        switch (s) {
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;
            case "*":
                resultado = n1 * n2;
                break;
            case "/":
                if (n2 == 0) {
                    numero2.setError("No se puede dividir entre 0");
                    return "";
                }
                resultado = n1 / n2;
                break;
        }


        if (resultado == (long) resultado) {
            return String.valueOf((long) resultado);
        } else {
            return String.valueOf(resultado);
        }
    }
}