package com.android.capacitacion.gabriel.cursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mixup";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabla = "CREATE TABLE disco (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, banda TEXT, imagen TEXT)";
        db.execSQL(tabla);

        ContentValues cv = new ContentValues();
        cv.put("nombre", "To Be Loved");
        cv.put("banda", "Michael Bublé");
        cv.put("imagen", "https://upload.wikimedia.org/wikipedia/en/0/04/Michael_Bubl%C3%A9-_To_Be_Loved_Album_Cover.jpg");

        db.insert("disco", "nombre", cv);

        cv.put("nombre", "Rammstein");
        cv.put("banda", "Live aus Berlin");
        cv.put("imagen", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/RammsteinLiveAusBerlin.jpg/220px-RammsteinLiveAusBerlin.jpg");
        db.insert("disco", "nombre", cv);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
