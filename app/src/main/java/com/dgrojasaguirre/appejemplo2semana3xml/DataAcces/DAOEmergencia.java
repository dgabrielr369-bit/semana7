package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.app.Activity;
import android.content.pm.VersionedPackage;

import com.dgrojasaguirre.appejemplo2semana3xml.Models.Emergencia;


public class DAOEmergencia {
    private String nombreBD;
    private int version;
    private Activity actividad;
    private BDHelper oHelper;

    public DAOEmergencia(Activity actividad){
        nombreBD="BDEmergencia";
        version=1;
        this.actividad=actividad;
        oHelper =new BDHelper(actividad,nombreBD,null,version);
    }
    public boolean Insertar(Emergencia oe){
        return false;
    }
}
