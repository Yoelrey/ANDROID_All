package com.example.a011;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.a011.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    EditText editText;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.careto);
        imageButton.setOnClickListener(view -> OnClickSwichtActivity(view));

    }
    /*public void OnClickSwichtActivity(View view){
        editText= (EditText)findViewById(R.id.cajita2);
        String text=editText.getText().toString();
        editText2=(EditText)findViewById(R.id.cagita);
        String text2= editText2.getText().toString();



        Intent intent= new Intent(this, LoginActivity.class);
        //Pasar datos

        startActivity(intent);

    }*/

    public void OnClickSwichtActivity(View view) {
        editText = (EditText) findViewById(R.id.cajita2);
        String text = editText.getText().toString();
        editText2 = (EditText) findViewById(R.id.cagita);
        String text2 = editText2.getText().toString();
        Bundle extras = new Bundle();
        extras.putString("user",text);
        extras.putString("pass",text2);

        Intent intent = new Intent(this, LoginActivity.class);
        //Pasar datos
        intent.putExtra(Intent.EXTRA_TEXT,extras);

        startActivity(intent);

    }
}