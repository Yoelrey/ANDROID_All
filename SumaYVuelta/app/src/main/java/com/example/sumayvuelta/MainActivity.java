package com.example.sumayvuelta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numero1;
    EditText numero2;
    TextView resultado;

    Button button;
    public static final String Peticion1="Peticion_request";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.boton);
        button.setOnClickListener(view -> onClickSwicht(view));

    }

    public void onClickSwicht(View view){
        numero1 = findViewById(R.id.primerNumero);
        String text= numero1.getText().toString();
        numero2 = findViewById(R.id.segundoNumero);
        String text2= numero2.getText().toString();



        Bundle bundle= new Bundle();
        bundle.putString("n1",text);
        bundle.putString("n2",text2);


        Intent intent=new Intent(this,secondActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent,1);

    }

    //Metodo de regreso
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            resultado=findViewById(R.id.resultado);
            String texto= data.getStringExtra(Peticion1);
            resultado.setText(texto);

        }

    }

}