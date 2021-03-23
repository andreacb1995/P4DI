package com.example.andreacarballidop4di.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.andreacarballidop4di.R;
import com.example.andreacarballidop4di.core.Planta;
import com.example.andreacarballidop4di.database.PlantaLab;
import com.google.android.material.tabs.TabLayout;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Planta> listaPlantas = new ArrayList<>();
    private PlantaLab plantaLab;
    private FragmentPlantasActivadas fragmentPlantasActivadas = new FragmentPlantasActivadas();
    private FragmentListaPlantas fragmentListaPlantas = new FragmentListaPlantas();
    private TabLayout tabLayout;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTheme(R.style.ActivityTheme_Primary_Base_Dark);

        if (obtenerPreferencias()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        MainActivity ma= MainActivity.this;
        SharedPreferences sp= ma.getSharedPreferences("SP",ma.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        tabLayout = findViewById(R.id.tabLayout);


    }

    private void añadirPlantasBD() {
        plantaLab = PlantaLab.get(this);
        Planta aloeVera = new Planta(R.drawable.aloevera, getString(R.string.nombreAloe),getString(R.string.nombreCientificoAloe), getString(R.string.descripcionAloe),false,getString(R.string.aloeUrl));
        Planta crisantemo = new Planta(R.drawable.crisantemo, getString(R.string.nombreCrisantemo),getString(R.string.nombreCientificoCrisantemo), getString(R.string.descripcionCrisantemo),false,getString(R.string.crisantemoUrl));
        Planta coladeBurro = new Planta(R.drawable.colaburro, getString(R.string.nombreColadeBurro),getString(R.string.nombreCientificoColadeBurro), getString(R.string.descripcionColadeBurro),false,getString(R.string.azaleaUrl));
        Planta calatea = new Planta(R.drawable.calatea, getString(R.string.nombreCatalea),getString(R.string.nombreCientificoCatalea), getString(R.string.descripcionCalatea),false,getString(R.string.calateaUrl));
        Planta calendula = new Planta(R.drawable.calendula, getString(R.string.nombreCalendula),getString(R.string.nombreCientificoCalendula), getString(R.string.descripcionCalendula),false,getString(R.string.calendulaUrl));
        Planta campanilla = new Planta(R.drawable.campanillas, getString(R.string.nombreCampanilla),getString(R.string.nombreCientificoCampanilla), getString(R.string.descripcionCampanilla),false,getString(R.string.campanillaUrl));
        Planta viola = new Planta(R.drawable.viola, getString(R.string.nombreViola),getString(R.string.nombreCientificoViola), getString(R.string.descripcionViola),false,getString(R.string.violaUrl));
        Planta girasol = new Planta(R.drawable.girasol, getString(R.string.nombreGirasol),getString(R.string.nombreCientificoGirasol), getString(R.string.descripcionGirasol),false,getString(R.string.girasolUrl));

        listaPlantas.add(aloeVera);
        listaPlantas.add(crisantemo);
        listaPlantas.add(coladeBurro);
        listaPlantas.add(calatea);
        listaPlantas.add(calendula);
        listaPlantas.add(campanilla);
        listaPlantas.add(viola);
        listaPlantas.add(girasol);


        if (plantaLab.getPlantas().isEmpty()) {
            for (Planta planta : listaPlantas) {
                plantaLab.addPlanta(planta);

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        añadirPlantasBD();
        añadirPlantasActividad();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, fragmentPlantasActivadas);
        ft.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = fragmentPlantasActivadas;
                        break;
                    case 1:
                        fragment = fragmentListaPlantas;
                        break;
                }
                assert fragment != null;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                menu.getItem(0).setIcon(R.drawable.ic_baseline_mode_night_24);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                menu.getItem(0).setIcon(R.drawable.ic_baseline_wb_sunny_24);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;

        if (id == R.id.cambiarTema) {
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                item.setIcon(R.drawable.ic_baseline_wb_sunny_24);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                guardarPreferencias(false);

            } else if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                item.setIcon(R.drawable.ic_baseline_mode_night_24);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                guardarPreferencias(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void guardarPreferencias(boolean theme) {
        SharedPreferences sp = this.getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("theme", theme);
        editor.apply();
    }


    public boolean obtenerPreferencias() {
        SharedPreferences sp = this.getSharedPreferences("SP", Context.MODE_PRIVATE);
        return sp.getBoolean("theme", false);
    }

    public void añadirPlantasActividad() {
        List<Planta> plantasDialogo = new ArrayList<>();
        listaPlantas = plantaLab.getPlantas();
        for (Planta planta : listaPlantas) {
            if (!planta.isActivada()){
                plantasDialogo.add(planta);
            }
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir plantas");
        if(plantasDialogo.isEmpty()){
            return;
        }

        String[] stringPosts = new String[plantasDialogo.size()];
        final boolean[] postsseleccion = new boolean[plantasDialogo.size()];
        for (int i = 0; i < plantasDialogo.size(); i++) {
            stringPosts[i] = plantasDialogo.get(i).getNombre();
        }
        builder.setMultiChoiceItems(stringPosts, postsseleccion, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                postsseleccion[i] = isChecked;
            }
        });
        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
//                for (int i = 0 ; i<plantasDialogo.size() ; i--){
                        for (int i = plantasDialogo.size() - 1; i >= 0; i--){
                            if (postsseleccion[i]){
                                plantasDialogo.get(i).setActivada(true);
                                plantaLab.updatePlanta(plantasDialogo.get(i));
                                fragmentPlantasActivadas.actualizarPlantas();
                            }
                        }


            }


        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }
}