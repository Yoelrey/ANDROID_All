package com.example.a015persistenciadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {


    static final String contador = "contador";
    static  final String KEY_NAME= "NOMBRE";
    private int miContador = 0;
    TextView textView;
    EditText editText;
    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Codigo para recuperar un valor de un fichero de preferencias
        SharedPreferences mias = getSharedPreferences("MIFICHERO",MODE_PRIVATE);
        String defaultCounter="";
        texto= mias.getString("KEY_NAME",defaultCounter);
        miContador= mias.getInt(contador,miContador);
        textView=findViewById(R.id.resultado);
        textView.setText("Contador: "+ Integer.toString(miContador));


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

    @Override
    protected void onPause() {
        super.onPause();
       // SharedPreferences settings = getPreferences(MODE_PRIVATE);

        SharedPreferences misPreferencias = getSharedPreferences("MIFICHERO",MODE_PRIVATE);

        SharedPreferences.Editor editor=misPreferencias.edit();
        editor.putInt(contador,miContador);
        editText = findViewById( R.id.caja);
        texto = editText.getText().toString();

        editor.putString(KEY_NAME,texto);

        //Solo escribe con commit
        editor.commit();

    }
}