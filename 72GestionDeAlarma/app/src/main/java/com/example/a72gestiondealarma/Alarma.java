package com.example.a72gestiondealarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarma extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Alarma", "Me molan las tetas");
    }
}
