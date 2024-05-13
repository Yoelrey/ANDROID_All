package com.example.a21recycledview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {

    
    private List<Datos> nameList;
    public MiAdaptador(List<Datos> list) {
        nameList = list;
    }



    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.elemento, parent, false);
        MiViewHolder myViewHolder = new MiViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {

    final Datos cadenas = nameList.get(position);
        holder.textView.setText(cadenas.getNombre());
        holder.textView.setText(cadenas.getDescripcion());
        holder.imagen.setImageResource(cadenas.getImagen());
    }

    @Override
    public int getItemCount() {
        if (nameList==null){
            return 0;
        }else {
            return nameList.size();
        }
    }

    //clase interna
    public class MiViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TextView detalle;

        public ImageView imagen;

        public MiViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texto);
            detalle= itemView.findViewById(R.id.detalle);
            imagen= itemView.findViewById(R.id.imagen);
        }
    }

}
