package com.example.videoplaymodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.videoplaymodule.model.impl.VideoDetailedModel;
import com.example.videoplaymodule.presenter.IVideoDetailPagePresenter;
import com.example.videoplaymodule.view.ui.IVedioDetailedPageView;

public class VideoDetailPagePresenter extends BasePresenter<IVedioDetailedPageView>
        implements IVideoDetailPagePresenter {

    public VideoDetailPagePresenter() {
    }

    public VideoDetailPagePresenter(IVedioDetailedPageView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new VideoDetailedModel(this);
    }


    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToat(msg, state);
    }

    @Override
    public RecyclerView.Adapter produceAdapter(Context context) {
        return ((VideoDetailedModel) getBaseModel()).produceAdapter(context);
    }
}
