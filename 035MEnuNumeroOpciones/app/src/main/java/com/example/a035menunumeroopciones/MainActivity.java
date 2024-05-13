package com.example.a035menunumeroopciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBarLayout appBarLayout= findViewById(R.id.appbar);
        Toolbar toolbar= new Toolbar(this);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mio,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if(id==R.id.menu_settings){
            Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.menu_settings){
            Toast.makeText(this, "Desajustes", Toast.LENGTH_SHORT).show();
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }






}