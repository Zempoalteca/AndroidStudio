package com.android.capacitacion.gabriel.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "naat";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLA = "trabajador";
    public static final String NOMBRE = "nombre";
    public static final String CORREO = "correo";
    public static final String PUESTO = "puesto";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta cuando creamos la instancia de la base de datos
        String tabla = "CREATE TABLE trabajador(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, correo TEXT, puesto TEXT)";       //_id el cursor adapter se va moviendo sobre el campo y por convencion que sea la llave primaria
        db.execSQL(tabla);

        ContentValues cv = new ContentValues();
        cv.put(NOMBRE,"Diego Montero");
        cv.put(CORREO, "diego@naat.com");
        cv.put(PUESTO, "Dir. Gral.");
        db.insert(TABLA, NOMBRE, cv);

        cv.put(NOMBRE,"Zaira Calderon");
        cv.put(CORREO, "zcalderon@naat.com");
        cv.put(PUESTO, "Gerente");
        db.insert(TABLA, NOMBRE, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE, "Cesar Gutierrez");
        cv.put(CORREO, "cesar@naat.com");
        cv.put(PUESTO, "CTO");
        db.insert(TABLA, NOMBRE, cv);
    }
}
