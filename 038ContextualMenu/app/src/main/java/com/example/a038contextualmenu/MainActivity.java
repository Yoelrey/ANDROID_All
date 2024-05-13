package com.example.a038contextualmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

public class MainActivity extends AppCompatActivity implements ActionMode.Callback, View.OnLongClickListener {

    private ActionMode mActionMode;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        image.setOnLongClickListener(this);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.contextualmenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_coast)
            Toast.makeText(this, "Cast tocado", Toast.LENGTH_LONG).show();
        else if(id == R.id.menu_print)
            Toast.makeText(this, "Print tocado", Toast.LENGTH_LONG).show();
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
    }

    @Override
    public boolean onLongClick(View v) {
        if (mActionMode != null) return false;
        mActionMode = startActionMode(this);
        return true;
    }

    private ActionMode startActionMode(MainActivity mainActivity) {
        return mainActivity.startActionMode(this);
    }
}
