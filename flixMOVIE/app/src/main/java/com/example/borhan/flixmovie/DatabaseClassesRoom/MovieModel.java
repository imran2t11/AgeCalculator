package com.example.borhan.flixmovie.DatabaseClassesRoom;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Movies",foreignKeys = @ForeignKey(entity = DirectorModel.class,parentColumns = "directorID",childColumns = "directorID"))

public class MovieModel {
@PrimaryKey(autoGenerate = true)
    private int movieid;
@ColumnInfo (name = "moviename")
    private String moviename;

    //@ForeignKey(entity = DirectorModel.class,parentColumns = directorID)
    @ColumnInfo(name = "directorID")
    private  long directorID;

    private String directorName;



    public long getDirectorID() {
        return directorID;
    }

    public void setDirectorID(long directorID) {
        this.directorID = directorID;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
}
