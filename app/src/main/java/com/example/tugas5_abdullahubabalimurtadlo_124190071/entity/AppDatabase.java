package com.example.tugas5_abdullahubabalimurtadlo_124190071.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataMahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataMahasiswaDAO dao();
    public static AppDatabase appDatabase;

    public static AppDatabase inidb (Context context) {
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbMahasiswa").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
