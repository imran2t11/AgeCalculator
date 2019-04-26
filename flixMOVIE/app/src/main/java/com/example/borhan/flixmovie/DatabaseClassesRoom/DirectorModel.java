package com.example.borhan.flixmovie.DatabaseClassesRoom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Director")
public class DirectorModel {
    @PrimaryKey(autoGenerate = true)
    private long directorID;
    @ColumnInfo(name = "directorName")
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
}
