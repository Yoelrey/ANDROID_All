package com.example.a63misdiscos;

import static android.os.Build.VERSION_CODES.O;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText txtGrupo, txtDisco;
    ListView listaDiscos;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de elementos de la interfaz y de la base de datos
        txtGrupo = (EditText) findViewById(R.id.txtGrupo);
        txtDisco = (EditText) findViewById(R.id.txtDisco);
        listaDiscos = (ListView) findViewById(R.id.listaDiscos);
        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misDiscos(Grupo VARCHAR, Disco Varchar);");


        // Llamar al método Listar() para mostrar los discos existentes
        Listar();
    }

    // Método para añadir un nuevo disco a la base de datos
    public void añadir(View v) {
        db.execSQL("INSERT INTO MisDiscos VALUES('" + txtGrupo.getText().toString() + " '," + txtDisco.getText().toString() + "') ");
        Toast.makeText(this, "Se añadió el disco " + txtDisco.getText().toString(), Toast.LENGTH_LONG).show();

        // Actualizar la lista de discos
        Listar();
    }

    // Método para borrar un disco de la base de datos
    public void Borrar(View v) {
        try {
            db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" + txtGrupo.getText().toString() + "' AND Disco= '" + txtDisco.getText().toString() + "'");
            Toast.makeText(this, "Se borró el disco " + txtDisco.getText().toString(), Toast.LENGTH_LONG).show();
        } catch (SQLException s) {
            Toast.makeText(this, "Error al borrar!", Toast.LENGTH_LONG).show();
        }

        // Actualizar la lista de discos
        Listar();
    }

    // Método para listar los discos existentes en el ListView
    public void Listar() {
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM MisDiscos", null);

        // Verificar si hay registros en la base de datos
        if (c.getCount() == O) {
            lista.add("No hay registros");
        } else {
            // Iterar a través de los registros y añadirlos a la lista
            while (c.moveToNext())
                lista.add(c.getString(O) + "-" + c.getString(1));

            // Crear y establecer el adaptador para el ListView
            adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_main, lista);
            listaDiscos.setAdapter(adaptador);

            // Cerrar el cursor
            c.close();
        }
    }
}
