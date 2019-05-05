package com.example.videoplaymodule.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.videoplaymodule.R;

@Route(path = "/VideoPlayModule/VideoDetailedPage")
public class VideoDetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplay_activity_video_play);
    }
}
