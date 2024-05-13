package com.example.a68enviarsms;



import static androidx.core.content.ContextCompat.registerReceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SMSActivity extends AppCompatActivity implements ReceptorSMS.onRecibeSMS {

    private static final String TAG = "DemoSMS";
    private ReceptorSMS receptor;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creación e inicialización del receptor de SMS
        receptor = new ReceptorSMS();
        registerReceiver(receptor, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));

        // Mostrar un mensaje Toast de saludo
        Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();

        // Establecer la actividad actual como oyente para eventos de recepción de SMS
        receptor.setOnRecibeSMSListener(this);

        // Inicialización del botón
        button = findViewById(R.id.button);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Desregistro del receptor de SMS al destruir la actividad
        unregisterReceiver(receptor);
        receptor = null;
    }

    // Método para enviar un SMS al número ingresado
    public void EnviarSMS(View view) {
        EditText txtTelefono = findViewById(R.id.numero);
        EnviaSMS(txtTelefono.getText().toString(), "Te felicito la navidad automáticamente");
    }

    // Método para enviar un SMS con el número y mensaje especificados
    public void EnviaSMS(String telefono, String mensaje) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefono, null, mensaje, null, null);
            Toast.makeText(getApplicationContext(), "SMS enviado.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS no enviado, por favor, inténtalo otra vez.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Método implementado de la interfaz onRecibeSMS
    @Override
    public void onRecibeSMS(String origen, String mensaje) {
        // Mostrar el mensaje de SMS recibido en un TextView
        TextView t = findViewById(R.id.mensaje);
        t.setText("Mensaje de " + origen + ": " + mensaje);
    }
}
