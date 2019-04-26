package com.example.borhan.flixmovie.DatabaseClassesRoom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DirectorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addDirector(DirectorModel directorModel) ;

    @Query("Select * from Director")
    public List<DirectorModel> getdirectorList();
    @Update
    public void updateDirector(DirectorModel directorModel);

    @Delete
    public void DeleteDirector(DirectorModel directorModel);



}
