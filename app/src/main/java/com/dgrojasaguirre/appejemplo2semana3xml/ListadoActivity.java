package com.dgrojasaguirre.appejemplo2semana3xml;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dgrojasaguirre.appejemplo2semana3xml.DataAcces.DAOEmergencia;
import com.dgrojasaguirre.appejemplo2semana3xml.Models.Emergencia;

public class ListadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Mostrar flecha
        Toolbar tbListar = findViewById(R.id.toolbar);
        setSupportActionBar(tbListar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbListar.setNavigationOnClickListener(click->finish());

        ListView lvLista = findViewById(R.id.lvListaEmergencias);
        DAOEmergencia oDAOEmergencia = new DAOEmergencia(this);
        //Cargar los objetos en la lista
        lvLista.setAdapter(new ArrayAdapter<Emergencia>(this, android.R.layout.simple_list_item_1,
                oDAOEmergencia.ListarAll()));
    }
}