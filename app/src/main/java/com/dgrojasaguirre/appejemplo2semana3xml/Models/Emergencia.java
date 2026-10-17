package com.dgrojasaguirre.appejemplo2semana3xml.Models;

public class Emergencia {
    private int IdEmergencia;
    private String descripcion;
    private String tipo;
    private String nivel;
    private boolean urgente;

    public Emergencia(String descripcion, String tipo, String nivel, boolean urgente) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.nivel = nivel;
        this.urgente = urgente;
    }

    public int getIdEmergencia() {
        return IdEmergencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public boolean isUrgente() {
        return urgente;
    }

    @Override
    public String toString() {
        return "Emergencia: " + descripcion +
                " de tipo='" + tipo  +
                "' y nivel='"  + nivel + "'" + (isUrgente()?" y es URGENTE":" y no es urgente");
    }
}

