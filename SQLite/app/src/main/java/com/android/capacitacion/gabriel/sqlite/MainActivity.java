package com.android.capacitacion.gabriel.sqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lista;
    ListAdapter adapter;
    Cursor cursor;


    public static final int ACTUALIZAR = Menu.FIRST;
    public static final int BORRAR = Menu.FIRST + 1;
    public static final int INSERTAR = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = new DBHelper(this).getReadableDatabase();      //Solo de lectura
        db = new DBHelper(this).getWritableDatabase();      //De lectura y escritura

        cursor = db.rawQuery("SELECT _id, nombre, puesto, correo FROM trabajador ORDER BY nombre", null);
        //primer arreglo, nombre de los cambpos, seugnod: nombre de las listas donde se mapea
        adapter = new SimpleCursorAdapter(this, R.layout.row, cursor,
                new String[]{"nombre", "correo", "puesto"},
                new int[]{R.id.nombre_trabajador, R.id.correo_trabajador, R.id.puesto_trabajador}, 0);
        lista = findViewById(R.id.lista);

        lista.setAdapter(adapter);
        registerForContextMenu(lista);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, ACTUALIZAR, Menu.NONE, "Actualizar trabajador");
        menu.add(Menu.NONE, BORRAR, Menu.NONE, "Borrar trabajador");
        menu.add(Menu.NONE, INSERTAR, Menu.NONE, "Agregar");
        //super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo register = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case ACTUALIZAR:
                actualizar(register.id);
                break;
            case BORRAR:
                borrar(register.id);
                break;
            case INSERTAR:
                //insertar(register.id);
                break;
        }
        return true;
    }

    private void borrar(final long rowId) {
        if (rowId > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Estas seguro de borrar al trabajador?")
                    .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] args = {String.valueOf(rowId)};
                            db.delete("trabajador", "_id=?", args);
                            cursor = db.rawQuery("SELECT _id, nombre, puesto, correo FROM trabajador ORDER BY nombre", null);
                            ((SimpleCursorAdapter) adapter).changeCursor(cursor);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }

    /*private void insertar(long id) {
        Cursor cActualizar = db.rawQuery("INSERT puesto FROM trabajador WHERE _id=?", new String[]{String.valueOf(rowId)});
        cActualizar.moveToFirst();
    }*/

    public void actualizar(final long rowId) {
        Cursor cActualizar = db.rawQuery("SELECT puesto FROM trabajador WHERE _id=?", new String[]{String.valueOf(rowId)});
        cActualizar.moveToFirst();

        //Obtenemos el dato del cursor
        //String puestoActual = cActualizar.getColumnName(cActualizar.getColumnIndex("puesto"));
        String puestoActual = cActualizar.getString(cActualizar.getColumnIndex("puesto"));

        //Inflar el XML
        LayoutInflater inflater = LayoutInflater.from(this);
        View actualizarView = inflater.inflate(R.layout.actualizar, null);
        final EditText puestoET = actualizarView.findViewById(R.id.nuevo_puesto);
        puestoET.setText(puestoActual);

        if (rowId > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Actualizar puesto")
                    .setView(actualizarView)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ContentValues cv = new ContentValues();
                            cv.put("puesto", puestoET.getText().toString());
                            db.update("trabajador", cv, "_id=?", new String[]{String.valueOf(rowId)});
                            cursor = db.rawQuery("SELECT _id, nombre, puesto, correo FROM trabajador ORDER BY nombre",
                                    null);
                            ((SimpleCursorAdapter) adapter).changeCursor(cursor);
                        }
                    }).show();
        }
    }
}
