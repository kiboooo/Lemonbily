package com.example.videoplaymodule.view;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.bean.Video;
import com.example.basemodule.view.BaseActivity;
import com.example.videoplaymodule.R;
import com.example.videoplaymodule.presenter.impl.VideoDetailPagePresenter;
import com.example.videoplaymodule.view.ui.IVedioDetailedPageView;

@Route(path = "/VideoPlayModule/VideoDetailedPageActivity")
public class VideoDetailedPageActivity
        extends BaseActivity<IVedioDetailedPageView, VideoDetailPagePresenter>
        implements IVedioDetailedPageView {

    @Autowired
    Video videoData;
    private TextView detailTextView;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.videoplay_activity_video_play);
        ARouter.getInstance().inject(this);
        detailTextView = findViewById(R.id.text_video_detail);

    }

    @Override
    protected void initSP() {

    }

    @Override
    public VideoDetailPagePresenter initPresenter() {
        return new VideoDetailPagePresenter(this, this);
    }

    @Override
    protected void initBinding() {
        if (videoData != null) {
            detailTextView.setText(videoData.toString());
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showToat(String msg, int state) {
        showToasts(msg, state);
    }
}
