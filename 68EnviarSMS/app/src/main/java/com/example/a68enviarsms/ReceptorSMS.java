package com.example.a68enviarsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceptorSMS extends BroadcastReceiver {

    private final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    // Interfaz (Listener) para comunicarnos
// con la actividad que creó al Broadcast Receiver
    private onRecibeSMS respuesta;

    public void setOnRecibeSMSListener(onRecibeSMS listener) {
        respuesta = listener;
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            // Esto aborta notificaciones otros ...
            this.abortBroadcast();

            // ---obtener el mensaje de SMS---
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String origen = null;
            String msg = null;

            if (bundle != null) {
                // obtener el mensaje original SMS:
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];

                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    origen = msgs[i].getOriginatingAddress();
                    msg = msgs[i].getMessageBody().toString();

                    // informar a la actividad de la llegada del mensaje
                    respuesta.onRecibeSMS(origen, msg);

                    Toast.makeText(context,
                            "SMS Recibido!",
                            Toast.LENGTH_LONG).show();

                    // continuar el proceso normal de broadcast
                    // es decir, llega el SMS y se almacena
                    // en la bandeja de entrada
                    this.clearAbortBroadcast();
                }
            }
        }
    }

    public interface onRecibeSMS {
        void onRecibeSMS(String origen, String mensaje);
    }
}
