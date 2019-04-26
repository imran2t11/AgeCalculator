package com.example.borhan.flixmovie.DatabaseClassesRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MovieModel.class,DirectorModel.class},version =8,exportSchema = false)
public abstract class Movie_Database extends RoomDatabase {

   // public static final String DB_NAME = "movies_db";

   public static Movie_Database instanceofmovieDatabaseClass;
    public abstract MovieDao movieDao();

    public abstract DirectorDao directorDao();

    public static Movie_Database getInstanceofmovieDatabaseClass(Context context){

        if(instanceofmovieDatabaseClass==null)
        {
            instanceofmovieDatabaseClass= Room.databaseBuilder(context.getApplicationContext(),Movie_Database.class,"movie_db")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instanceofmovieDatabaseClass;

    }




}
