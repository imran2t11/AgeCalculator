package com.example.videojsonrecylerview.NetworkCalls;

import com.example.videojsonrecylerview.Model.DemoImgModel;
import com.example.videojsonrecylerview.Model.VideoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("sunapp/dashboard/video.json")
    Call<ArrayList<VideoModel>> getAllVideo();

    @GET("sunapp/dashboard/blog.json")
    Call<ArrayList<DemoImgModel>> getAll();
}
