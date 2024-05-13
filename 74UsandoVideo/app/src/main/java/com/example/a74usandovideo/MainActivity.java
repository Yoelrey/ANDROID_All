package com.example.a74usandovideo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    static final int CAPTURA_IMAGEN_THUMBNAIL = 1;
    static final int CAPTURA_IMAGEN_TAMAÑO_REAL = 2;
    VideoView videoView;
    String fotoPath;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
    }

    // Método para el clic del botón para obtener un thumbnail
    public void hacerFotoThumbnail(View view) {
        // Boton de la izquierda
        Intent hacerFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hacerFotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(hacerFotoIntent, CAPTURA_IMAGEN_THUMBNAIL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURA_IMAGEN_THUMBNAIL && resultCode == RESULT_OK) {
            // Obtener la URI del video capturado
            Uri video = data.getData();
            // Configurar la URI del video en el VideoView y reproducirlo
            videoView.setVideoURI(video);
            videoView.start();
        } else if (requestCode == CAPTURA_IMAGEN_TAMAÑO_REAL && resultCode == RESULT_OK) {
            // Mostrar la ruta de la imagen guardada en un Toast
            Toast toast = Toast.makeText(this, "La imagen se ha guardado en: " + fotoPath, Toast.LENGTH_LONG);
            toast.show();
        } else {
            // Manejar cualquier otro resultado, por ejemplo, un error
            Toast toast = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // Método para crear un archivo de video con un nombre resistente a colisiones
    private File createImageFile() throws IOException {
        // Crear un nombre único basado en la fecha y hora actual
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ALMO" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);

        // Crear el archivo de video
        File videoFile = File.createTempFile(
                imageFileName,  /* prefijo */
                ".mp4",         /* sufijo */
                storageDir      /* directorio */
        );

        // Guardar la ruta del archivo para usar con ACTION_VIEW intents
        fotoPath = "file:" + videoFile.getAbsolutePath();
        return videoFile;
    }

    // Método para guardar un video a tamaño real
    public void guardarFotoTamañoReal(View view) {
        dispatchTakeVideoIntent();
    }

    // Método para gestionar la captura de un video a tamaño real
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // Asegurarse de que haya una actividad de cámara para manejar el intent
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            // Crear el archivo donde se debería guardar el video
            File videoFile = null;
            try {
                videoFile = createImageFile();
            } catch (IOException ex) {
                // Manejar errores durante la creación del archivo
                // ...
            }
            // Continuar solo si el archivo se creó con éxito
            if (videoFile != null) {
                // Obtener la URI del archivo utilizando FileProvider
                Uri videoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", videoFile);
                // Configurar la salida del video en el intent
                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                // Iniciar la actividad de la cámara para la captura del video
                startActivityForResult(takeVideoIntent, CAPTURA_IMAGEN_TAMAÑO_REAL);
            }
        }
    }
}
