package com.example.a039reciclerviewcontextual;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> implements selectMode {

    MiViewHolder holder;

    
    private List<Datos> nameList;
    public SparseArray<Boolean> selectedList = new SparseArray<>();
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


    public void onBindViewHolder(@NonNull MiViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Datos cadenas = nameList.get(position);
        holder.textView.setText(cadenas.getNombre());
        holder.textView.setText(cadenas.getDescripcion());
        holder.imagen.setImageResource(cadenas.getImagen());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (selectedList.get(position, false)) {
                  selectedList.put(position,true);
              } else {
                  selectedList.removeAt(position);
              }
              notifyItemChanged(position);
            }
        });
    }


    public void deleteAllSelected(){
        if (selectedList.size() ==0){
            return;
        }
        for (int index=nameList.size();index>=0;index--){
            if (selectedList.get(index,false)){
                selectedList.removeAt(index);
            }
        }
        selectedList.clear();
    }




    @Override
    public int getItemCount() {
        if (nameList==null){
            return 0;
        }else {
            return nameList.size();
        }
    }
public void remove(int position){
    nameList.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, nameList.size());
}

    @Override
    public void Onselec() {

    }


    public class MiViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TextView detalle;

        public ImageView imagen;

        public MiViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texto);
            detalle = itemView.findViewById(R.id.detalle);
            imagen = itemView.findViewById(R.id.imagen);


        }
    }



    }
