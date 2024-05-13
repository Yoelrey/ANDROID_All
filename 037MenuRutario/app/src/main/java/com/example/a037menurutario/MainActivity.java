package com.example.a037menurutario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

private final int Menu_Download = 1;
private final int Menu_Settings = 2;
private boolean showDownloadMenu = false;
Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        
        //Donde creamos el menu??
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu = (Menu) getMenuInflater();


    ToggleButton toggleButton = findViewById(R.id.toggleButton);
    toggleButton.setOnClickListener(view -> toggleMenu(view));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, Menu_Download, 0, "Descargas");
        menu.add(0, Menu_Settings, 0, "Ajustes");
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(Menu_Download);
        menuItem.setVisible(showDownloadMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case Menu_Download:
                Toast.makeText(this, R.string.menu_download,Toast.LENGTH_LONG).show();
                break;
            case Menu_Settings:
                Toast.makeText(this,R.string.menu_settings,Toast.LENGTH_SHORT).show();

                break;
            default:
                super.onContextItemSelected(item);

        }
        return true;
    }

    public void toggleMenu(View view){
        showDownloadMenu = !showDownloadMenu;
    }
}