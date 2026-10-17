package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgrojasaguirre.appejemplo2semana3xml.Models.Emergencia;
import com.dgrojasaguirre.appejemplo2semana3xml.R;

import java.util.List;

public class EmergenciaAdapter extends RecyclerView.Adapter<EmergenciaAdapter.EmergenciaViewHolder> {

    private Context contexto;
    private List<Emergencia> ListaEmergencia;

    public EmergenciaAdapter(Context context, List<Emergencia> listaEmergencia){
        this.contexto=context;
        this.ListaEmergencia=listaEmergencia;
    }

    @NonNull
    @Override
    public EmergenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(contexto).inflate(R.layout.ly_item_emergencia_card,parent,false);
        return new EmergenciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergenciaViewHolder holder, int position) {
        Emergencia oE = ListaEmergencia.get(position);
        holder.lbItemDescripcion.setText("Descripción: " + oE.getDescripcion());
        holder.lbItemtipo.setText("Tipo: " + oE.getTipo());
        holder.lbItemNivel.setText("Nivel: " + oE.getNivel());
        holder.lbUrgente.setText(oE.isUrgente() ? "Es Urgente" : "No es Urgente");

        if (oE.isUrgente()) {
            holder.itemView.setBackgroundTintList(contexto.getColorStateList(R.color.rojo_500));

            holder.lbUrgente.setTextColor(contexto.getColor(R.color.white));
        } else {
            holder.itemView.setBackgroundTintList(contexto.getColorStateList(R.color.white));

        }

        if (oE.getFoto() != null && oE.getFoto().length > 0) {
            Bitmap oImagen = BitmapFactory.decodeByteArray(oE.getFoto(), 0, oE.getFoto().length);
            holder.imgItemFoto.setImageBitmap(oImagen);
        }
    }

    @Override
    public int getItemCount() {
        return ListaEmergencia.size();
    }

    public class EmergenciaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgItemFoto;
        private TextView lbItemDescripcion,lbItemtipo,lbItemNivel,lbUrgente;
        public EmergenciaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemFoto = itemView.findViewById(R.id.imgItemFoto);
            lbItemDescripcion = itemView.findViewById(R.id.lbItemDescripcion);
            lbItemtipo = itemView.findViewById(R.id.lbItemTipo);
            lbItemNivel = itemView.findViewById(R.id.lbItemNivel);
            lbUrgente = itemView.findViewById(R.id.lbitemUrgente);
        }
    }
}
