package com.example.a76mediacontroler;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.MediaController;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener instancias de MediaPlayer, MediaController y Handler
        mediaPlayer = new MediaPlayer();
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.constraint_layout)); // Establecer el anclaje de MediaController al diseño de restricciones
        handler = new Handler();

        // Configurar el origen de los datos para MediaPlayer
        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.metodo_para_escapar));
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostrar los controles y arrancar el MediaPlayer cuando esté preparado
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Mostrar el controlador de medios
                        // Después de 20 segundos de inactividad, se oculta
                        mediaController.show(20000);
                        mediaPlayer.start();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener y liberar el MediaPlayer al destruir la actividad
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pasar el evento al controlador de medios al tocar la pantalla
        mediaController.show();
        return false;
    }

    // Implementación de MediaController.MediaPlayerControl

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }
}
