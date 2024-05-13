package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lista);
        ArrayAdapter<String>adaptador= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,pueblaLista());
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(this);

    }

    public static List<String> pueblaLista(){
        List<String> clubs= new ArrayList<>();
        clubs.add("Americo");
        clubs.add("Lugo");
        clubs.add("Celta B");
        clubs.add("Depor");
        clubs.add("Corujo");
        clubs.add("Compos");
        clubs.add("Americo");
        return clubs;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView;
        textView= findViewById(R.id.textView3);
        textView.setText(parent.getItemAtPosition(position).toString());
    }
}