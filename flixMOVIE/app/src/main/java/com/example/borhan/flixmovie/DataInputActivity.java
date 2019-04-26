package com.example.borhan.flixmovie;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.borhan.flixmovie.DatabaseClassesRoom.DirectorModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.MovieModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.Movie_Database;

public class DataInputActivity extends AppCompatActivity {
EditText movienameET,directornameET;
Button addbtn;
String movie,director;
    Movie_Database movie_databases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        movienameET=findViewById(R.id.movienameET);
        directornameET=findViewById(R.id.directornameET);
        addbtn=findViewById(R.id.addbtn);
        movie_databases=    Room.databaseBuilder(getApplicationContext(),Movie_Database.class,"movies_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    public void addfunction(View view) {
        MovieModel movieModel=new MovieModel();
        DirectorModel directorModel=new DirectorModel();

        movie=movienameET.getText().toString();
        director=directornameET.getText().toString();

        if(movie.length()<=0|| director.length()<=0)
        {
            Toast.makeText(this, "Check your input", Toast.LENGTH_SHORT).show();

        }

        else {

            directorModel.setDirectorName(director);

            long dirID=movie_databases.directorDao().addDirector(directorModel);

            movieModel.setMoviename(movie);
            movieModel.setDirectorID(dirID);
            movie_databases.movieDao().addMovie(movieModel);

            Log.d("NameOfMv", "addfunction: "+movie);
            Log.d("NameOfDirector", "addfunction: "+director);


            //movie_databases.movieDao().addMovie(movieModel);
            // movie_databases.directorDao().addDirector(directorModel);
            Intent intent=new Intent(DataInputActivity.this,MainActivity.class);
            startActivity(intent);

        }



    }

}
