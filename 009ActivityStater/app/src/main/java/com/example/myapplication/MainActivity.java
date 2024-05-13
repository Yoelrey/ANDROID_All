package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    Button miBoton2;
//Añadir boton para bajar imagen de una pagina web
    //Mandar el mensaje pero a un numero concreto
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.boton);
        mButton= View.setOnClickListener(View->LanzarAplicacion(view));
        miBoton2= findViewById(R.id.boton2);
        miBoton2= View.setOnClickListener(View->mandarMensaje(view));
    }
    public void LanzarAplicacion(View view){
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("https:\\www.cebem.net"));
    startActivity(intent);
    }


    public void mandarMensaje(View view) {
        CharSequence textMessage = "esto es un mensaje";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra("address","123456789");
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
        sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }
}