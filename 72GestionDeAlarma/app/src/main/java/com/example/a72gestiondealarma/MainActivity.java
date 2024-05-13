package com.example.a72gestiondealarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén el contexto de la actividad
        mContext = this;

        // Configura la alarma para las 8:00 AM (por ejemplo)
        int hora = 8;
        int minutos = 0;
        setAlarma(hora, minutos);
    }

    private void setAlarma(int hora, int minutos) {
        // Configurar el calendario
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minutos);

        // Crear la intención para la alarma
        Intent intent = new Intent(mContext, Alarma.class);
        alarmIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);

        // Obtener el servicio de la alarma
        alarmMgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

        // Configurar la alarma para que se repita cada día
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);
    }
}
