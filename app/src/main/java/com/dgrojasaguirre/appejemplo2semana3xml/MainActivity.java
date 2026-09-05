package com.dgrojasaguirre.appejemplo2semana3xml;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // creo mi boton el cual eredara de xml su id btnCalculadora
        Button btnCalculadora = findViewById(R.id.bntCalculadora);

        // este metodo me sirve para poder cambiar de activity de esta (this) a la que quiero ir (CalculadoraActivity) al clicar en el btn creado arriba..
        btnCalculadora.setOnClickListener(b ->{

            Intent oIntento = new Intent(this, CalculadoraACtivity.class);
                startActivity(oIntento);
        } );
    }
}