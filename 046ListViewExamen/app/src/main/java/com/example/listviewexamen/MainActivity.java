package com.example.listviewexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Datos> list;
    Adaptador adaptador;
    ListView listView;
    TextView textView;
    ImageView imageView;
    ArrayAdapter<Datos> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lista);
        textView = findViewById(R.id.selecccion);
        imageView = findViewById(R.id.escudo);
        list = poblarDatos();

        adaptador = new Adaptador(this, list);
        listView.setAdapter(adaptador);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Datos datos = (Datos) parent.getItemAtPosition(position);
                textView.setText(obtenerInformacion(datos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("");
            }
        });
    }


    @NonNull
    private String obtenerInformacion(@NonNull Datos datos) {
        String informacionDetallada = "Nombre del Equipo:\n" + datos.getNombreEquipo();
        imageView = findViewById(R.id.escudo);
        imageView.setImageResource(datos.getImagenResId());

        return informacionDetallada;  }

    @NonNull
    private List<Datos> poblarDatos() {
        int imagenResId = R.drawable.avatar_1;
        String [] nombres = {"Košarkaški Klub Crvena Zvezda","Maccabi Basketball Club","Basketball Club Žalgiris","Club Deportivo Baskonia","PanathinaikosBasketball Club"};
        String [] pais = {"Serbia","Israel","Lituania","España","Grecia"};
        String [] ciudad = {"Belgrado","Tel Aviv","Kaunas","Vitoria","Atenas"};
        String [] años = {"1945","1932","1944","1959","1919"};
        List<Datos> list = new ArrayList<>();
        Datos datos;
        String lugar = "R.drawable.";
        for (int i = 0; i < 5; i++) {
            datos = new Datos(nombres[i], pais[i],años[i],ciudad[i],R.drawable.avatar_1+i);
            list.add(datos);
        }
        return list;
    }

}
