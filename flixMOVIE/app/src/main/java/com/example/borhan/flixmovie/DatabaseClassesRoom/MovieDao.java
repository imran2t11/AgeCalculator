package com.example.borhan.flixmovie.DatabaseClassesRoom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addMovie(MovieModel movieModel) ;

    @Query("Select * from Movies ORDER BY moviename ASC")
    public List<MovieModel> getmovieList();

    @Update
    public void updateMovie(MovieModel movieModel);

    @Delete
    public void DeleteMovie(MovieModel movieModel);



}
