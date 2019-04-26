package com.example.videojsonrecylerview.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoModel {
    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("video_name")
    @Expose
    private String videoName;
    @SerializedName("location")
    @Expose
    private String location;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
