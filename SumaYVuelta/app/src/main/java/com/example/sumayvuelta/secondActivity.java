package com.example.sumayvuelta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.sumayvuelta.R;
public class secondActivity extends AppCompatActivity  {


    TextView textView1;
    TextView textView2;
    Integer resultadoDeLaSuma=0;
    Button sumar;
    String texto1;
    String texto2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        sumar= findViewById(R.id.sumar);
        sumar.setOnClickListener(view -> sumarView(view));

    }

    private void sumarView(View view) {


        Integer res =0;
        Intent returnIntent= new Intent();
        int bot =view.getId();

    switch (bot){
        case (R.id.sumar):
            res=Integer.parseInt(texto1)+Integer.parseInt(texto2);

            break;
        case (R.id.resta):
            res=Integer.parseInt(texto1)-Integer.parseInt(texto2);

            break;

        case (R.id.multiplicacion):
            res=Integer.parseInt(texto1)*Integer.parseInt(texto2);

            break;
    }


        returnIntent.putExtra(MainActivity.Peticion1,String.valueOf(resultadoDeLaSuma));

        setResult(RESULT_OK,returnIntent);
        finish();


    }
}