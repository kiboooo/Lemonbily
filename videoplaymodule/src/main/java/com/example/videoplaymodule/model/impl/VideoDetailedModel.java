package com.example.videoplaymodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.videoplaymodule.model.IViewDetailModel;
import com.example.videoplaymodule.presenter.impl.VideoDetailPagePresenter;

public class VideoDetailedModel extends BaseModel<VideoDetailPagePresenter> implements IViewDetailModel {

    public VideoDetailedModel() {
    }

    public VideoDetailedModel(VideoDetailPagePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
