package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.app.Activity;
import android.content.pm.VersionedPackage;

public class DAOEmergencia {
    private String nombreBD;
    private int version;
    private Activity actividad;

    public DAOEmergencia(Activity actividad){
        nombreBD="BDEmergencia";
        version=1;
        this.actividad=actividad;
    }

}
