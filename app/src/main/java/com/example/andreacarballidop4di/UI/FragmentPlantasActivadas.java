package com.example.andreacarballidop4di.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.andreacarballidop4di.R;
import com.example.andreacarballidop4di.core.Planta;
import com.example.andreacarballidop4di.database.PlantaLab;

import java.util.ArrayList;
import java.util.List;

public class FragmentPlantasActivadas extends Fragment   implements JardinRecyclerViewAdapter.ItemClickListener {
    public List<Planta> listaPlantas;
    public List<Planta> listaPlantasActivadas;
    public RecyclerView recyclerViewPlantasActivadas;
    JardinRecyclerViewAdapter adapter;
    public PlantaLab plantaLab;
    View rootview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plantaLab = PlantaLab.get(requireActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_activar_desactivas_plantas, container, false);
        listaPlantasActivadas = new ArrayList<>();
        listaPlantas = plantaLab.getPlantas();
        for (Planta planta : listaPlantas) {
            if (planta.isActivada()) listaPlantasActivadas.add(planta);
        }
        recyclerViewPlantasActivadas = rootview.findViewById(R.id.recyclerListaPlantas);
        recyclerViewPlantasActivadas.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewPlantasActivadas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlantasActivadas.setClickable(true);
        adapter = new JardinRecyclerViewAdapter(getContext(), listaPlantasActivadas, this);
        adapter.setClickListener(this);
        recyclerViewPlantasActivadas.setAdapter(adapter);

        return rootview;
    }
      @Override
      public void onItemCLick(int position) {
          Planta planta = listaPlantasActivadas.get(position);
          Intent intent = new Intent(getActivity(), InformacionPlanta.class);
          intent.putExtra("id", String.valueOf(planta.getId()) );
          startActivity(intent);
      }


      public void actualizarPlantas(){
          listaPlantasActivadas = new ArrayList<>();
          listaPlantas = plantaLab.getPlantas();
          for (Planta planta : listaPlantas) {
              if (planta.isActivada()) {
                  listaPlantasActivadas.add(planta);
              }
          }
          adapter = new JardinRecyclerViewAdapter(getContext(), listaPlantasActivadas, this);
          adapter.setClickListener(this);
          recyclerViewPlantasActivadas.setAdapter(adapter);
      }

}
