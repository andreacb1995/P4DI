package com.example.andreacarballidop4di.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.andreacarballidop4di.core.Planta;

@Database(entities = {Planta.class}, version = 1)
public abstract class PlantaDataBase extends RoomDatabase {
    public abstract PlantaDao getPlantaDao();
}