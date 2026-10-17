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
        version=2;
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
            oValue.put("foto",oE.getFoto());

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
                byte[] vfoto = oRegistros.getBlob(5);
                Emergencia oE =new Emergencia(vdescricpcion,vtipo,vnivel,vurgente,vfoto);
                lista.add(oE);
            }while (oRegistros.moveToNext());
            oBD.close();
            oRegistros.close();
        }
        return lista;
    }

    public List<Emergencia> ListarAll(String filtro){
        List<Emergencia> lista = new ArrayList<>();
        SQLiteDatabase oBD = oHelper.getReadableDatabase();
        String seleccion=null;
        String[] argumentoSeleccion=null;
        if(filtro!=null && !filtro.trim().isEmpty())
        {
            seleccion= "descripcion like ?";
            argumentoSeleccion=new String[]{"%"+filtro+"%"};
        }
        Cursor oRegistros = oBD.query("Emergencia",null,
                seleccion,argumentoSeleccion,null,null,null);
        if(oRegistros.moveToFirst()){
            do{
                String vdescripcion = oRegistros.getString(1);
                String vtipo = oRegistros.getString(2);
                String vnivel = oRegistros.getString(3);
                boolean vurgente = oRegistros.getInt(4)==1?true:false;
                byte[] vfoto = oRegistros.getBlob(5);
                Emergencia oE = new Emergencia(vdescripcion,vtipo,vnivel,vurgente,vfoto);
                lista.add(oE);
            }while(oRegistros.moveToNext());
            oBD.close();
            oRegistros.close();
        }
        return lista;
    }

    public List<Emergencia> ListarTodos(){
        return ListarAll();
    }

    public List<Emergencia> ListarTodos(String filtro){
        return ListarAll(filtro);
    }
}
