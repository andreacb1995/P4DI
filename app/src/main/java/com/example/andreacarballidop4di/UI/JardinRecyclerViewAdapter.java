package com.example.andreacarballidop4di.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andreacarballidop4di.R;
import com.example.andreacarballidop4di.core.Planta;

import java.util.ArrayList;
import java.util.List;

public class JardinRecyclerViewAdapter extends RecyclerView.Adapter<JardinRecyclerViewAdapter.ViewHolder> {
    List<Planta> listaPlantas;
    Context context;
    ItemClickListener itemClickListener;
    LayoutInflater layoutInflater;

    public JardinRecyclerViewAdapter(Context context, List<Planta> listaPlantas, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.layoutInflater = LayoutInflater.from(context);
        this.listaPlantas = listaPlantas;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.planta_card, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planta planta = listaPlantas.get(position);
        holder.imageView.setImageResource(planta.getImagen());
        holder.textView.setText(planta.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemCLick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagenViewPlanta);
            textView = itemView.findViewById(R.id.tvNombrePlanta);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemCLick(getAdapterPosition());
            }
        }
    }
}
