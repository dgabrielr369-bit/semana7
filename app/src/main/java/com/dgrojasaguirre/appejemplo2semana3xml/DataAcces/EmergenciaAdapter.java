package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmergenciaAdapter extends RecyclerView.Adapter<EmergenciaAdapter.EmergenciaViewHolder> {
    @NonNull
    @Override
    public EmergenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EmergenciaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EmergenciaViewHolder extends RecyclerView.ViewHolder {

        public EmergenciaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
