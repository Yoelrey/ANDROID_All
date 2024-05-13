package com.example.a023reciclerdelete;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Datos> list = Datos.poblardatos();


        
        RecyclerView recyclerView = findViewById(R.id.reciclaje);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        //para cargar la lista en cada elemento.xml
        MiAdaptador miAdaptador = new MiAdaptador(list);

        recyclerView.setAdapter(miAdaptador);



        button= findViewById(R.id.boton);
        button.setOnClickListener(View ->miAdaptador.añadir());



    }


}