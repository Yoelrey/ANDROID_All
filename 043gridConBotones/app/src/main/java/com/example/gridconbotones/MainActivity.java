package com.example.gridconbotones;

import static android.graphics.Color.rgb;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        añadeHijo();

    }

    public void recorrer() {
        View v;
        GridLayout g = findViewById(R.id.rejilla);
        for (int i = 0; i < g.getChildCount(); i++) {
            v = g.getChildAt(i);
            //System.out.println(v.toString() + "objeto");

            Button b = (Button) v;
            b.setBackgroundColor(0x000000);
            // final int i1=Log.i(v.toString()+"objeto");
        }


    }


    @SuppressLint("ResourceType")
    public void añadeHijo() {
        GridLayout g = findViewById(R.id.rejilla);
        Button b;
        for (int i = 0; i < 17; i++) {

            b = new Button(this);

            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            b.setText("btn" + i);
            b.setId(View.generateViewId());

            b.setOnClickListener(this);
            g.addView(b, i);

        }
        b= new Button(this);
        b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b.setText("SET");
        b.setId(18);

        b.setOnClickListener(this);
        g.addView(b);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {


        Button b = (Button) view;
        if (view.getClass().getSimpleName().equals("Button") && b.getId() != 18) {
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();

            b.setBackgroundColor(rgb(r1.nextInt(255), (r2.nextInt(255)), (r3.nextInt(255))));

        } else
            recorrer();
    }
}