package com.example.master.movieflixlite.SQLiteDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME="movie_db";
    static final int DATABASE_VERSION=1;

    static final String TABLE_MOVIE="movies";
    private static final String COL_ID_MOV="movieID";
    private static final String COL_NAME_MOV="moviername";
    private static final String COL_ID="directorID";
    private static final String COL_NAME="directorname";

    static final String TABLE_DIRECTOR="directors";
    private static final String COL_ID_DIR="directorID";
    private static final String COL_NAME_DIR="directorname";

    static final  String CREATE_TABLE_MOVIE="create table"+TABLE_MOVIE+"( "+COL_ID_MOV+"integer primary key,"+COL_NAME_MOV+"text, "
            +COL_ID+"integer foreign key);";

    static final  String CREATE_TABLE_DIRECTOR="create table"+TABLE_DIRECTOR+"( "+COL_ID_DIR+"integer primary key,"+COL_NAME_DIR+"text); ";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_MOVIE + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_DIRECTOR + "'");
    }
}
