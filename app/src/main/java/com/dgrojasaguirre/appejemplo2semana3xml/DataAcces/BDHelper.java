package com.dgrojasaguirre.appejemplo2semana3xml.DataAcces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    private String tabla_Emergencia="CREATE TABLE Emergencia(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "descripcion Varchar(150) NOT NULL,"+
            "tipo VARCHAR(20) NOT NULL,"+
            "nive VARCHAR(20) NOT NULL,"+
            "urgente INTEGER NOT NULL)";

    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_Emergencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
