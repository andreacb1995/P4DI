package com.example.andreacarballidop4di.UI;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
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

public class FragmentListaPlantas extends Fragment {
    public ListaPlantasRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    List<Planta> listaPlantas;
    PlantaLab plantaLab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_plantas_activadas, container, false);

        plantaLab = PlantaLab.get(getActivity().getApplicationContext());
        listaPlantas = plantaLab.getPlantas();

        recyclerView = rootview.findViewById(R.id.rvPlantasActivadas);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext(), RecyclerView.VERTICAL, false));
        adapter = new ListaPlantasRecyclerViewAdapter(getContext(), listaPlantas);
        recyclerView.setAdapter(adapter);
        return rootview;
    }

}
