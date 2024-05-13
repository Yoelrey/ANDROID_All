package com.example.spinnerexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.List;

public class Adaptador extends ArrayAdapter<Datos> {

    public Adaptador(Context context, List<Datos> datosList) {
        super(context, R.layout.elemento, datosList);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return obtenerVistaPersonalizada(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return obtenerVistaPersonalizada(position, convertView, parent);
    }

    @NonNull
    private View obtenerVistaPersonalizada(int position, View convertView, ViewGroup parent) {
        Datos datos = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento, parent, false);
        }

        TextView textView1 = convertView.findViewById(R.id.nombreEquipo);
        TextView textView2 = convertView.findViewById(R.id.paisEquipo);
        TextView textView3 = convertView.findViewById(R.id.ciudadEquipo);
        TextView textView4 = convertView.findViewById(R.id.añoEquipo);
        ImageView imageView = convertView.findViewById(R.id.imagen);

        if (datos != null) {
            textView1.setText(datos.getNombreEquipo());
            textView2.setText(datos.getPaisEquipo());
            textView3.setText(datos.getCiudadEquipo());
            textView4.setText(datos.getAñoEquipo());
            imageView.setImageResource(datos.getImagenResId());
        }

        return convertView;
    }
}
