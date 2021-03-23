package com.example.andreacarballidop4di.core;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "plantas")
public class Planta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    protected int id;
    @NonNull
    private int imagen;
    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;
    private String nombreCientifico;
    private boolean activada;
    String url;


    public Planta(int imagen, @NonNull String nombre,  String nombreCientifico,@NonNull String descripcion, boolean activada, String url) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreCientifico = nombreCientifico;
        this.activada = activada;
        this.url = url;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCientifico() {

        return nombreCientifico;
    }


}
