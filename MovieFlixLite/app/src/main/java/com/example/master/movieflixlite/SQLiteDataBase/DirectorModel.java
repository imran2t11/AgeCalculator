package com.example.master.movieflixlite.SQLiteDataBase;

public class DirectorModel {
    private int directorID;
    private String directorName;

    public DirectorModel(int directorID, String directorName) {
        this.directorID = directorID;
        this.directorName = directorName;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
