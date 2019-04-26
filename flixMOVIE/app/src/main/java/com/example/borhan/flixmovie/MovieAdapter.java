package com.example.borhan.flixmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.borhan.flixmovie.DatabaseClassesRoom.DirectorModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.MovieModel;
import com.example.borhan.flixmovie.DatabaseClassesRoom.Movie_Database;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<MovieModel> moviesList;

    private Context context;


    public MovieAdapter(List<MovieModel> moviesList) {
        this.moviesList = moviesList;

    }




    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {

       // moviesList=movie_database.movieDao().getmovieList();
        //directorList=movie_database.directorDao().getdirectorList();
holder.movieTV.setText(moviesList.get(position).getMoviename());
holder.directorTV.setText(moviesList.get(position).getDirectorName());

//holder.directorTV.setText(moviesList.get(position).getDirectorID());



    }

    @Override
    public int getItemCount() {
        return moviesList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder

    {
        TextView movieTV,directorTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            directorTV=itemView.findViewById(R.id.directorTV);
            movieTV=itemView.findViewById(R.id.movieTV);
        }
    }
}
