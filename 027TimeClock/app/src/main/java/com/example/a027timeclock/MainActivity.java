package com.example.a027timeclock;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextClock textClock;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textClock= findViewById(R.id.reloj);
        textClock.setOnClickListener(view -> pillHora());
        button=findViewById(R.id.jasonBoton);
        button.setOnClickListener(view -> tokecito());

    }

    private void tokecito() {
        EditText editText= findViewById(R.id.pon);
        String pin= editText.getText().toString();
        Toast.makeText(this, "Ver formato de hora", Toast.LENGTH_SHORT).show();
        textClock.setText("");
    }

    private void pillHora() {
        TextView textView= findViewById(R.id.hora);
        textView.setText(textClock.getText().toString());
    }


}