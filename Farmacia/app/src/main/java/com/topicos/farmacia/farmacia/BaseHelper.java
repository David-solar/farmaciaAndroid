package com.topicos.farmacia.farmacia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 25/05/16.
 */
public class BaseHelper extends SQLiteOpenHelper
{

    String tabla = "CREATE TABLE Medicamentos(id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", Nombre TEXT" +
            ", ValorCompra TEXT" +
            ", ValorVenta TEXT" +
            ", Cantidad TEXT" +
            ", Descripcion TEXT)";

    String tabla2 = "CREATE TABLE Ventas(id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", Cliente TEXT" +
            ", Fecha TEXT" +
            ", Total TEXT)";

    String tabla3 = "CREATE TABLE Empleados(id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", Nombre  TEXT" +
            ", FechaNac TEXT" +
            ", FechaIngreso TEXT" +
            ", Sueldo TEXT" +
            ", Puesto TEXT)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase farma)
    {


        farma.execSQL(tabla);
        farma.execSQL(tabla2);
        farma.execSQL(tabla3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
