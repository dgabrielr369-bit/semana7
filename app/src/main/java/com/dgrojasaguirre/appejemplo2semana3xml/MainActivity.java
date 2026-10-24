package com.dgrojasaguirre.appejemplo2semana3xml;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView oNavegacion = findViewById(R.id.btnNavegacion);
        oNavegacion.setOnItemSelectedListener(item -> {
            Intent oIntento = null;

            if(item.getItemId() == R.id.itemInicio) { 
                return true;
            }
            if(item.getItemId() == R.id.itemIncertar) {
                oIntento = new Intent(this, InsertarActivity.class);
                startActivity(oIntento);
                return true;
            }
            if(item.getItemId() == R.id.itemListar) {
                oIntento = new Intent(this, activity_Listar_rv.class);
                startActivity(oIntento);
                return true;
            }
            if(item.getItemId() == R.id.itemEditar) {
                oIntento = new Intent(this, EditarActivity.class);
                startActivity(oIntento);
                return true;
            }
            if(item.getItemId() == R.id.itemSalir) {

                finishAffinity(); 

            }

            return false;
        });
    }
}