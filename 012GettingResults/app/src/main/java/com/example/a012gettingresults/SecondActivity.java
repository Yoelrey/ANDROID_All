package com.example.a012gettingresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    Button button;
    TextView textView;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text= getIntent().getStringExtra(Intent.EXTRA_TEXT);
        textView=findViewById(R.id.num2);
        textView.setText(text);
        button=findViewById(R.id.sumar);
        button.setOnClickListener(view -> sumar(view));
    }


    public void sumar(View view){
    Intent intent = new Intent();
    //pareja clave MainActivity.REQUEST_RESULT valor text.toUpperCase
    intent.putExtra(MainActivity.REQUEST_RESULT,text.toUpperCase());
    //metodo especifico de start activityforresult
    setResult(RESULT_OK,intent);
    finish();


    }

}