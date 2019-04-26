package com.example.videojsonrecylerview.Activities;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.videojsonrecylerview.Adapters.DemoImgAdapter;
import com.example.videojsonrecylerview.Adapters.VideoAdapter;
import com.example.videojsonrecylerview.Model.DemoImgModel;
import com.example.videojsonrecylerview.Model.VideoModel;
import com.example.videojsonrecylerview.NetworkCalls.RetrofitClient;
import com.example.videojsonrecylerview.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView videoRV, demoRV;
    private ArrayList<VideoModel> videoModelArrayList = new ArrayList<>();
    private ArrayList<DemoImgModel> imgModelArrayList = new ArrayList<>();
    VideoAdapter videoAdapter;
    DemoImgAdapter demoImgAdapter;
    ExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoRV = findViewById(R.id.videoRV);
        demoRV = findViewById(R.id.demoRV);
        exoPlayerView = findViewById(R.id.exo_player_view);
        videoRV.setLayoutManager(new LinearLayoutManager(this));
        demoRV.setLayoutManager(new LinearLayoutManager(this));

        //test
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        Uri videoURI = Uri.parse("https://sundarbantourist.com/sunapp/dashboard/video/201904231555995994.mp4");

        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

        exoPlayerView.setPlayer(exoPlayer);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);


        Call<ArrayList<VideoModel>> call = RetrofitClient.getInstance().getApi().getAllVideo();
        call.enqueue(new Callback<ArrayList<VideoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<VideoModel>> call, Response<ArrayList<VideoModel>> response) {
                Log.e("responseBody", "onResponse: " + response.body());
                videoModelArrayList = response.body();
                Log.e("vdoName", "onResponse: " + videoModelArrayList.get(0).getVideoName());
                Log.e("vdolocation", "onResponse: " + videoModelArrayList.get(0).getLocation());
                Log.e("vdoid", "onResponse: " + videoModelArrayList.get(0).getVideoId());

                videoAdapter = new VideoAdapter(MainActivity.this, videoModelArrayList);
                videoRV.setAdapter(videoAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<VideoModel>> call, Throwable t) {
                Log.e("fail", "onFailure: " + t.getMessage());
            }
        });

        Call<ArrayList<DemoImgModel>> listCall = RetrofitClient.getInstance().getApi().getAll();
        listCall.enqueue(new Callback<ArrayList<DemoImgModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DemoImgModel>> call, Response<ArrayList<DemoImgModel>> response) {
                Log.e("response", "onResponse: " + response.body());
                imgModelArrayList = response.body();
                demoImgAdapter = new DemoImgAdapter(MainActivity.this, imgModelArrayList);
                demoRV.setAdapter(demoImgAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<DemoImgModel>> call, Throwable t) {
                Log.e("fail2", "onFailure: " + t.getMessage());

            }
        });

        String vdourl = "https://sundarbantourist.com/sunapp/dashboard/video/201904231555995994.mp4";
        Uri uri = Uri.parse(vdourl);


    }
}
