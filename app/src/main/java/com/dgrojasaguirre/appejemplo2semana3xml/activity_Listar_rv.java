package com.dgrojasaguirre.appejemplo2semana3xml;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dgrojasaguirre.appejemplo2semana3xml.DataAcces.DAOEmergencia;
import com.dgrojasaguirre.appejemplo2semana3xml.DataAcces.EmergenciaAdapter;

public class activity_Listar_rv extends AppCompatActivity {

    private RecyclerView rvLista;
    private EditText txtBuscar;
    private DAOEmergencia oDAOEmergencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_rv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvLista = findViewById(R.id.rvListaemergencia);
        txtBuscar = findViewById(R.id.editTextText);

        //Mostrar y ejecutar la flecha de retorno en el toolBar
        Toolbar oTbListar = findViewById(R.id.lb_ListarRV);
        setSupportActionBar(oTbListar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        oTbListar.setNavigationOnClickListener(click->{finish();});
        cargarLista();
    }

    private void cargarLista() {
        oDAOEmergencia = new DAOEmergencia(this);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(new EmergenciaAdapter(this, oDAOEmergencia.ListarTodos()));
    }
}