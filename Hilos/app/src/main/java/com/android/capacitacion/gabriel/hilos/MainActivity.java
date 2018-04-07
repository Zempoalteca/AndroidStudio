package com.android.capacitacion.gabriel.hilos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private Handler handler = new Handler(this);

    /************************ Investigar esto ************************/
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //https://static.vix.com/es/sites/default/files/styles/large/public/btg/universos-2.jpg?itok=IpTWZVlD
    //http://www.korosenai.es/wp-content/uploads/2017/12/rick-morty-temporada1.jpg
    //http://co.emedemujer.com/wp-content/uploads/sites/15/2017/10/59d322ad01c2c-770x385.jpeg

    @Override
    public boolean handleMessage(Message msg) {
        ImageView targetImage = findViewById(R.id.img_descargar);
        Bitmap img = (Bitmap) msg.obj;
        String text = msg.getData().getString("status");
        TextView texto = findViewById(R.id.estatus);
        texto.setText(text);
        targetImage.setImageBitmap(img);
        return true;
    }

    private Runnable imgDonwnloader = new Runnable() {

        Bitmap imagen = null;

        private void enviarMensaje(String status){
            Bundle bundle = new Bundle();
            bundle.putString("status", status);
            Message msg = new Message();
            msg.setData(bundle);
            msg.obj = this.imagen;
            handler.sendMessage(msg);
        }

        @Override
        public void run() {
            try {
                URL urlImagen = new URL("https://static.vix.com/es/sites/default/files/styles/large/public/btg/universos-2.jpg?itok=IpTWZVlD");
                imagen = BitmapFactory.decodeStream(urlImagen.openStream());        //Abre el flujo de datos
                Log.i("Hilos",""+Thread.currentThread());
                //Log.wtf("Hilos WTF", ""+Thread.currentThread());
                //Log.e("Hilos E",""+Thread.currentThread());
                if (imagen != null){
                    enviarMensaje("Imagen descargada exitosamente!");
                }else{
                    enviarMensaje("Hubo problemas con la descarga");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                enviarMensaje("Tu URL es inválida!");
            } catch (IOException e) {
                e.printStackTrace();
                enviarMensaje("Hubo un problema de tipo IO!");
            }
        }
    };

    public void descargarImagen(View v){
        for (int i = 0; i<=5; i++) {
            //new Thread(imgDonwnloader, "Hilo de descarga #" + i).start();
            //Para establecer la prioridad
            /*Thread hilo = new Thread(imgDonwnloader,"Hilo de desarga"+i);
            hilo.setPriority(10);
            hilo.start();*/


            executor.execute(imgDonwnloader);

        }
    }

}
