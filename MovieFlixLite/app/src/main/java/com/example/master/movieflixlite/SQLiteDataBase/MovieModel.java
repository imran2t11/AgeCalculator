package com.example.master.movieflixlite.SQLiteDataBase;

public class MovieModel {

    private int movieID;
    private String moviename;
    private long directorID;
    private String directorName;

    public MovieModel(int movieID, String moviename, long directorID) {
        this.movieID = movieID;
        this.moviename = moviename;
        this.directorID = directorID;
    }

    public long getDirectorID() {
        return directorID;
    }

    public void setDirectorID(long directorID) {
        this.directorID = directorID;
    }

    public String getDirectorname() {
        return directorName;
    }

    public void setDirectorname(String directorname) {
        this.directorName = directorname;
    }

    public MovieModel(int movieID, String moviename) {
        this.movieID = movieID;
        this.moviename = moviename;
    }
    public MovieModel()
    {

    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
}
