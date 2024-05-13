package com.example.a014salvarestado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String contador = "contador";
    private int miContador=0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void contador (View view){

        miContador++;
        textView= findViewById(R.id.resultado);
        textView.setText("Contador: "+ Integer.toString(miContador));
    }


    public void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putInt(contador,miContador);

    }


    public void onRestoreInstanceState(Bundle saveInstanceState){

        super.onRestoreInstanceState(saveInstanceState);
        miContador=saveInstanceState.getInt(contador);
        textView =findViewById(R.id.resultado);
        textView.setText("Contador: "+ Integer.toString(miContador));

    }

}