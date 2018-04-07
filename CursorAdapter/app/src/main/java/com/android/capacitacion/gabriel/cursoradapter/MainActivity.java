package com.android.capacitacion.gabriel.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lista;
    Cursor cursor;
    DiscoCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this).getWritableDatabase();

        lista = findViewById(R.id.lista);

        cursor = db.rawQuery("SELECT _id, nombre, banda, imagen FROM disco", null);
        adapter = new DiscoCursorAdapter(this, cursor);
        lista.setAdapter(adapter);

    }

    private class DiscoCursorAdapter extends CursorAdapter {

        public DiscoCursorAdapter(Context context, Cursor cursor){
            super(context, cursor,0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.disco_row, parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView nombre_disco = view.findViewById(R.id.nombre_disco);
            TextView banda_disco = view.findViewById(R.id.banda_disco);

            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String banda = cursor.getString(cursor.getColumnIndex("banda"));

            nombre_disco.setText(nombre);
            banda_disco.setText(banda);

            ImageView imagen = view.findViewById(R.id.imagen_disco);
            TareaSegundoPlano downloader = new TareaSegundoPlano(imagen);
            downloader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, cursor.getString(cursor.getColumnIndex("imagen")));
            //new TareaSegundoPlano().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, cursor.getString(cursor.getColumnIndex("imagen")));

        }

        private class TareaSegundoPlano extends AsyncTask<String, Void, Bitmap>{
            ImageView imagen;

            public TareaSegundoPlano(ImageView imagen){
                this.imagen = imagen;
            }


           @Override
            protected void onPreExecute() {

                super.onPreExecute();
                //imagen = findViewById(R.id.imagen_disco);
                imagen.setImageBitmap(null);

            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL imagenURL = new URL(strings[0]);
                    Bitmap image = BitmapFactory.decodeStream(imagenURL.openStream());
                    return image;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imagen.setImageBitmap(bitmap);
            }
        }


        /*
        AsyncTask<String, Integer, Bitmap> asyncTask = new AsyncTask<String, Integer, Bitmap>() {



                @Override
                protected void onPreExecute() {

                    super.onPreExecute();
                    ImageView imagen = findViewById(R.id.imagen_disco);
                    imagen.setImageBitmap(null);

                }

                @Override
                protected Bitmap doInBackground(String... strings) {
                    try {
                        URL imagenURL = new URL(strings[0]);
                        Bitmap image = BitmapFactory.decodeStream(imagenURL.openStream());
                        return image;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    ImageView imagen = findViewById(R.id.imagen_disco);
                    imagen.setImageBitmap(bitmap);
                }
            };
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://www.educacionyculturaaz.com/wp-content/uploads/2013/06/UAM_by_ockre.png");

        *
        * */
    }
}
