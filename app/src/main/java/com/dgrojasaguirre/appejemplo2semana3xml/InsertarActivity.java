package com.dgrojasaguirre.appejemplo2semana3xml;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dgrojasaguirre.appejemplo2semana3xml.DataAcces.DAOEmergencia;
import com.dgrojasaguirre.appejemplo2semana3xml.Models.Emergencia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

public class InsertarActivity extends AppCompatActivity {

    private String[]vtipo={"Accidente","Sismo","Lluvias","Otros"};
    private AutoCompleteTextView listaTipo=null;

    private TextInputEditText txtDescripcion =null;

    private RadioButton rb_baja,rb_media,rb_alta;
    private CheckBox cbxUrgente;
    private ImageView imgFoto;
    private byte[] imagenSeleccionada=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listaTipo=findViewById(R.id.listaTipo);
        listaTipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vtipo));

        //Para mostrar flecha de retroceso
        Toolbar tbInsertar = findViewById(R.id.toolbar2);
        setSupportActionBar(tbInsertar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbInsertar.setNavigationOnClickListener(click->finish());

        FloatingActionButton oBotongrabar = findViewById(R.id.btnGuardar);
        oBotongrabar.setOnClickListener( view -> {

            guardarObjetoEmergencia();

        });
        txtDescripcion=findViewById(R.id.txtDescripcion);
        rb_alta=findViewById(R.id.rbAlta);
        rb_media=findViewById(R.id.rbMedia);
        rb_baja=findViewById(R.id.rbBaja);
        cbxUrgente=findViewById(R.id.cbxUregente);
        imgFoto = findViewById(R.id.imgFoto);
        imgFoto.setOnClickListener(click->{
            Intent oIntento = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            oIntento.setType("image/*");
            startActivityIfNeeded(oIntento,100);
        });

    }

    private void guardarObjetoEmergencia() {
        if(validarcampos()){ //si es true hay campos vacíos

            return;

        }
        // continua si hay campos y continua con el guardado
        String vdescripcion = txtDescripcion.getText().toString().trim();
        String vtipo = listaTipo.getText().toString().trim();
        String vnivel = "";

        if(rb_baja.isChecked())vnivel="baja";
        if(rb_media.isChecked())vnivel="media";
        if(rb_alta.isChecked())vnivel="alta";
        boolean vurgente= cbxUrgente.isChecked();
        Emergencia oE=new Emergencia(vdescripcion,vtipo,vnivel,vurgente,imagenSeleccionada);
        DAOEmergencia oDAOEmergencia = new DAOEmergencia(this);
        if(oDAOEmergencia.Insertar(oE)){
            Toast.makeText(this,"Registrado Correctamente",Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }else
            Toast.makeText(this,"no se ha registrado",Toast.LENGTH_SHORT).show();

    }

    private boolean validarcampos() {
        // Limpiamos errores previos para que no se queden "pegados"
        txtDescripcion.setError(null);
        listaTipo.setError(null);
        
        if(txtDescripcion.getText().toString().trim().isEmpty()){
            txtDescripcion.setError("Campo Descripción es obligatorio");
            txtDescripcion.requestFocus();
            return true;
        }
        if(listaTipo.getText().toString().trim().isEmpty()){
            listaTipo.setError("Selecciona una opcion obligatoriamente");
            listaTipo.requestFocus();
            return true;
        }
        // Si NO está marcada baja Y NO está marcada media Y NO está marcada alta
        if (!rb_baja.isChecked() && !rb_media.isChecked() && !rb_alta.isChecked()) {
            Toast.makeText(this, "Debes marcar un nivel de gravedad", Toast.LENGTH_SHORT).show();
            rb_baja.requestFocus();
            // Retornamos true porque encontramos un error (campos vacíos)
            return true;
        }
        if(imagenSeleccionada==null){
            Toast.makeText(this,"Seleccionar imagen",Toast.LENGTH_SHORT).show();
            return true;
        }

        // Si llega hasta aquí, no hay errores, retornamos false
        return false;
    }
    private void limpiarCampos() {
        // Limpiamos los textos
        txtDescripcion.setText("");
        listaTipo.setText("", false);
        
        // Desmarcamos los radio buttons y el checkbox
        rb_baja.setChecked(false);
        rb_media.setChecked(false);
        rb_alta.setChecked(false);
        cbxUrgente.setChecked(false);
        imagenSeleccionada = null;
        imgFoto.setImageResource(R.drawable.img_1);
        
        // Enfocamos de nuevo la descripción
        txtDescripcion.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){ //si funciona la galeria de imagenes
            if(resultCode==RESULT_OK){ // si ha seleccionado una imagen
                Uri uriFoto = data.getData();
                imgFoto.setImageURI(uriFoto);
                imgFoto.buildDrawingCache();
                Bitmap oImagen = imgFoto.getDrawingCache();
                ByteArrayOutputStream flujoSalida = new ByteArrayOutputStream();
                oImagen.compress(Bitmap.CompressFormat.JPEG,0,flujoSalida);
                imagenSeleccionada = flujoSalida.toByteArray();
            }
        }
    }
}