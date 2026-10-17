package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.VersionedPackage;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dgrojasaguirre.appejemplo2semana3xml.Models.Emergencia;

import java.util.ArrayList;
import java.util.List;


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
    public boolean Insertar(Emergencia oE){
        //usar la base de datos en modo escritura
        SQLiteDatabase oBD= oHelper.getWritableDatabase();
        if(oBD!=null){
            ContentValues oValue = new ContentValues();
            //columnas
            oValue.put("descripcion",oE.getDescripcion());
            oValue.put("tipo",oE.getTipo());
            oValue.put("nivel",oE.getNivel());
            oValue.put("urgente",oE.isUrgente()?1:0);// es integer por lo que necesitamos convertir el bolean a 1 o 0 (integers)

            long fila =oBD.insert("Emergencia",null,oValue);
            oBD.close();

            if(fila>0){
                return  true;
            }else return false;
        }
        return false;

    }
    public List<Emergencia> ListarAll(){
        List<Emergencia> lista =new ArrayList<>();
        SQLiteDatabase oBD =oHelper.getReadableDatabase();
        String sql="SELECT * FROM Emergencia";

        Cursor oRegistros = oBD.rawQuery(sql,null);
        if(oRegistros.moveToFirst()){
            do{
                String vdescricpcion = oRegistros.getString(1);
                String vtipo = oRegistros.getString(2);
                String vnivel = oRegistros.getString(3);
                boolean vurgente = oRegistros.getInt(4)==1?true:false;
                Emergencia oE =new Emergencia(vdescricpcion,vtipo,vnivel,vurgente);
                lista.add(oE);
            }while (oRegistros.moveToNext());
            oBD.close();
            oRegistros.close();
        }
        return lista;
    }
}
