package com.example.borhan.flixmovie;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.borhan.flixmovie.DatabaseClassesRoom.DirectorModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.MovieDao;
import com.example.borhan.flixmovie.DatabaseClassesRoom.MovieModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.Movie_Database;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView listRV;
FloatingActionButton fab;
RecyclerView.Adapter adapter;

List<MovieModel> movieList;
List<DirectorModel>directorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        listRV=findViewById(R.id.listRV);
movieList=new ArrayList<>();
directorList=new ArrayList<>();

        final Movie_Database movie_database= Room.databaseBuilder(getApplicationContext(),Movie_Database.class,"movies_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
//movieList=movie_database.movieDao().getmovieList();
//directorList=movie_database.directorDao().getdirectorList();

//for (int i=0;i<movieList.size();i++)
//{
//    Log.d("dsdsdsdsdoooooooo", "onCreate: "+movieList.get(i).getMoviename());
//
//}
//
//        for (int i=0;i<directorList.size();i++)
//        {
//            Log.d("dsdsdsdsdoooooooo", "onCreate: "+directorList.get(i).getDirectorName());
//
//        }




movieList=movie_database.movieDao().getmovieList();


directorList=movie_database.directorDao().getdirectorList();



//  need to use runnable or Asynctask before this
//    listRV.setLayoutManager(new LinearLayoutManager(this));
//    adapter=new MovieAdapter(movieList);
//    listRV.setAdapter(adapter);



        new Thread(new Runnable() {
            @Override
            public void run() {

                Movie_Database movieDatabase = Room.databaseBuilder(getApplicationContext(),Movie_Database.class,"movie_db").
                        fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build();
                movieList=movie_database.movieDao().getmovieList();

                directorList=movie_database.directorDao().getdirectorList();

                runOnUiThread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < movieList.size(); i++) {
                            for (int j = 0; j < directorList.size(); j++) {
                                if (movieList.get(i).getDirectorID() == directorList.get(j).getDirectorID()) {
                                    movieList.get(i).setDirectorName(directorList.get(j).getDirectorName());
                                }
                            }
                        }


                        listRV.setLayoutManager(new LinearLayoutManager(getApplication()));
                        adapter=new MovieAdapter(movieList);
                        listRV.setAdapter(adapter);          //setting adapter
                    }

                });
            }
        }).start();


        ///////////////////////


    }



    public void goToDataInputAcitvty(View view) {

        Intent i=new Intent(MainActivity.this,DataInputActivity.class);
        startActivity(i);

    }
}
