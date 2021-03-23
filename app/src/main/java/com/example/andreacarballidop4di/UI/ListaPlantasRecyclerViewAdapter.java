package com.example.andreacarballidop4di.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andreacarballidop4di.R;
import com.example.andreacarballidop4di.core.Planta;
import com.example.andreacarballidop4di.database.PlantaLab;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;


public class ListaPlantasRecyclerViewAdapter extends RecyclerView.Adapter<ListaPlantasRecyclerViewAdapter.ViewHolder> {

    List<Planta> listaPlantas;
    Context context;
    LayoutInflater layoutInflater;

    public ListaPlantasRecyclerViewAdapter(Context context, List<Planta> listaPlantas) {
        this.context = context;
        this.listaPlantas = listaPlantas;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activar_desactivar_plantas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planta planta = listaPlantas.get(position);
        holder.switchMaterial.setText(planta.getNombre());
        holder.switchMaterial.setChecked(planta.isActivada());
        holder.switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            planta.setActivada(isChecked);
            listaPlantas.set(position, planta);
            PlantaLab.get(context).updatePlanta(planta);
        });
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        SwitchMaterial switchMaterial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            switchMaterial = itemView.findViewById(R.id.switch_planta);
        }
    }
}
