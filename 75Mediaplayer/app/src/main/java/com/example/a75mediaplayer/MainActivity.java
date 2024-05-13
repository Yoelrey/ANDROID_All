package com.example.a75mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {

    // Declaración de un objeto MediaPlayer para reproducir audio
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear un MediaPlayer y asignar el audio desde el recurso raw.adrenalina
        mediaPlayer = MediaPlayer.create(this, R.raw.adrenalina);

        // Configurar un Listener para detectar cuando la reproducción ha terminado
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Al finalizar la reproducción, liberar recursos y actualizar un TextView
                TextView t = (TextView) findViewById(R.id.textView);
                mediaPlayer.release();
                t.setText("La reproducción ha terminado, acabo de hacer un release()");
            }
        });
    }

    // Método para iniciar la reproducción al presionar el botón "Play"
    public void play(View view){
        TextView t = (TextView) findViewById(R.id.textView);
        if (mediaPlayer.isPlaying()){
            // Si ya está reproduciendo, mostrar un mensaje indicando que ya está en reproducción
            t.setText("Ya estás escuchando música, ¿qué más quieres chaval?");
        }
        else {
            // Si no está reproduciendo, iniciar la reproducción y actualizar el TextView
            mediaPlayer.start();
            t.setText("Tu MP está parado, tranqui que le hago un start()");
        }
    }

    // Método para detener la reproducción al presionar el botón "Stop"
    public void stop(View view) throws IOException {
        TextView t = (TextView) findViewById(R.id.textView);
        if (mediaPlayer!=null && mediaPlayer.isPlaying()) {
            // Si está reproduciendo, detener y preparar el MediaPlayer para futuras reproducciones
            mediaPlayer.stop();

            try {
                mediaPlayer.prepare();
                t.setText("La música estaba sonando pero acabas de hacer un stop() y un prepare() a tu MP");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        else {
            // Si no está reproduciendo, mostrar un mensaje indicando que no se puede detener
            t.setText("La música no suena, el MP está parado, ¿por qué haces un stop()?");
        }
    }

    // Método para pausar la reproducción al presionar el botón "Pause"
    public void pause(View view){
        TextView t = (TextView) findViewById(R.id.textView);
        if (mediaPlayer.isPlaying()) {
            // Si está reproduciendo, pausar y actualizar el TextView
            mediaPlayer.pause();
            t.setText("Acabas de pausar tu MP");
        }
        else {
            // Si no está reproduciendo, mostrar un mensaje indicando que no se puede pausar
            t.setText("Tu MP no está en ejecución, luego no lo puedes pausar");
        }
    }
}
