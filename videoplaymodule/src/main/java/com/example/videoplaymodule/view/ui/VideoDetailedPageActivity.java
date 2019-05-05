package com.example.videoplaymodule.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.bean.Video;
import com.example.videoplaymodule.R;

@Route(path = "/VideoPlayModule/VideoDetailedPageActivity")
public class VideoDetailedPageActivity extends AppCompatActivity {

    @Autowired
    Video videoData;
    private TextView detailTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplay_activity_video_play);
        ARouter.getInstance().inject(this);
        detailTextView = findViewById(R.id.text_video_detail);
        if (videoData != null) {
            detailTextView.setText(videoData.toString());
        }
    }

}
