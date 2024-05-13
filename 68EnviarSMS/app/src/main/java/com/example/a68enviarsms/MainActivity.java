package com.example.a68enviarsms;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización del botón y asignación del evento de clic
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> EnviarSMS(v));
    }

    // Método para enviar un SMS
    public void EnviarSMS(View v) {
        // Obtener referencias a los campos de texto para el número de teléfono y el mensaje
        EditText txtTelefono = findViewById(R.id.numero);
        EditText txtMensaje = findViewById(R.id.mensaje);

        // Imprimir un mensaje de información en el log
        Log.i("Ojo", "Enviando SMS");

        // Obtener el número de teléfono y el mensaje de los campos de texto
        String telefono = txtTelefono.getText().toString();
        String message = txtMensaje.getText().toString();

        try {
            // Utilizar SmsManager para enviar el mensaje de texto
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefono, null, message, null, null);

            // Mostrar un mensaje de éxito en un Toast
            Toast.makeText(getApplicationContext(),
                    "SMS enviado.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // Mostrar un mensaje de error en un Toast y registrar la excepción en el log
            Toast.makeText(getApplicationContext(),
                    "SMS no enviado, por favor, inténtalo otra vez.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
