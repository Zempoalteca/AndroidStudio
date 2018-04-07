package com.android.capacitacion.gabriel.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarDescarga(View v){
        new Descargar().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://www.educacionyculturaaz.com/wp-content/uploads/2013/06/UAM_by_ockre.png");
    }


    public class Descargar extends AsyncTask<String, Integer, Bitmap>{

        private ProgressBar barra;

        @Override
        protected void onPreExecute() {
            barra = findViewById(R.id.progress_bar);
            super.onPreExecute();
            ImageView imagen = findViewById(R.id.imagen);
            imagen.setImageBitmap(null);
            barra.setMax(10000);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            barra.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imagen = findViewById(R.id.imagen);
            imagen.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try{
                URL imagenUrl = new URL(strings[0]);
                Bitmap imagen = BitmapFactory.decodeStream(imagenUrl.openStream());
                for (int i=0; i<10000; i++){
                    publishProgress(i);
                }
                return imagen;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
