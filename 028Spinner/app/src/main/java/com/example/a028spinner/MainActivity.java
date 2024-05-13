package com.example.a028spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;


    static ArrayList<String> pueblaSpinner(){
        ArrayList<String>lista=new ArrayList<>();
        lista.add("valladares");
        lista.add("Vigo");
        lista.add("vetis");
        lista.add("madrid");
        lista.add("barcelona");

        return lista;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner= findViewById(R.id.spinner);
        ArrayAdapter <String>adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,pueblaSpinner());
        adaptador.setDropDownViewResource( android.R.layout.simple_spinner_item);

        spinner.setAdapter(adaptador);

        //prueba

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView= findViewById(R.id.seleccion);
                textView.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView= findViewById(R.id.seleccion);
        textView.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    /*private AdapterView.OnItemClickListener messageClickListener= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "Tienes un evento", Toast.LENGTH_LONG).show();
        }
    };*/
}