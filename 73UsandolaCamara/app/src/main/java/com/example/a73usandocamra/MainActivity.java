package com.example.a73usandocamra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    static final int CAPTURA_IMAGEN_THUMBNAIL = 1;
    static final int CAPTURA_IMAGEN_TAMAÑO_REAL = 2;
    ImageView imageView;
    String fotoPath;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    // Método para el clic del botón para obtener un thumbnail
    public void hacerFotoThumbnail (View view){
        // Boton de la izquierda
        Intent hacerFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hacerFotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(hacerFotoIntent, CAPTURA_IMAGEN_THUMBNAIL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURA_IMAGEN_THUMBNAIL && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
        else if (requestCode == CAPTURA_IMAGEN_TAMAÑO_REAL && resultCode == RESULT_OK) {
            // Mostrar la ruta de la imagen guardada en un Toast
            Toast toast = Toast.makeText(this, "La imagen se ha guardado en: " + fotoPath, Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            // Manejar cualquier otro resultado, por ejemplo, un error
            Toast toast = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // Método para guardar una foto a tamaño real
    public void guardarFotoTamañoReal(View view){
        dispatchTakePictureIntent();
    }

    // Método para crear un archivo de imagen con un nombre resistente a colisiones
    private File createImageFile() throws IOException {
        // Crear un nombre único basado en la fecha y hora actual
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        // Crear el archivo de imagen
        File image = File.createTempFile(
                imageFileName,  /* prefijo */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );

        // Guardar la ruta del archivo para usar con intents ACTION_VIEW
        fotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    // Método para gestionar la captura de una foto a tamaño real
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Asegurarse de que haya una actividad de cámara para manejar el intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crear el archivo donde se debería guardar la foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Manejar errores durante la creación del archivo
                // ...
            }
            // Continuar solo si el archivo se creó con éxito
            if (photoFile != null) {
                // Obtener la URI del archivo utilizando FileProvider
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                // Configurar la salida de la imagen en el intent
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                // Iniciar la actividad de la cámara para la captura de la foto
                startActivityForResult(takePictureIntent, CAPTURA_IMAGEN_TAMAÑO_REAL);
            }
        }
    }
}
