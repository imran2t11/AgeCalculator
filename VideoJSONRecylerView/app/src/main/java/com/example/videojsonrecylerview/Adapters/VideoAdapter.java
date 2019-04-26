package com.example.videojsonrecylerview.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.videojsonrecylerview.Model.VideoModel;
import com.example.videojsonrecylerview.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<VideoModel> videoModelArrayList;

    public VideoAdapter(Context context, ArrayList<VideoModel> videoModelArrayList) {
        this.context = context;
        this.videoModelArrayList = videoModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoModel videoModel = videoModelArrayList.get(position);
        holder.videoIDTV.setText(videoModel.getVideoId());
        holder.videoname.setText(videoModel.getVideoName());

        holder.locationTV.setVideoURI(Uri.parse("https://sundarbantourist.com/sunapp/dashboard/video/201904231555995994.mp4"));

        Log.e("checkVDO", "onBindViewHolder: " + videoModel.getLocation());

    }

    @Override
    public int getItemCount() {
        return videoModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView videoname;
        TextView videoIDTV;
        VideoView locationTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoIDTV = itemView.findViewById(R.id.videoIDTV);
            locationTV = itemView.findViewById(R.id.locationTV);
            videoname = itemView.findViewById(R.id.videonameTV);

        }
    }
}
