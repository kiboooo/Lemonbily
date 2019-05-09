package com.example.videoplaymodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.basemodule.bean.CommentUIBean;
import com.example.basemodule.model.BaseModel;
import com.example.videoplaymodule.model.IViewDetailModel;
import com.example.videoplaymodule.model.adapter.DetailCommentAdapter;
import com.example.videoplaymodule.presenter.impl.VideoDetailPagePresenter;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailedModel extends BaseModel<VideoDetailPagePresenter>
        implements IViewDetailModel {

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

    @Override
    public RecyclerView.Adapter produceAdapter(Context context) {
        RecyclerView.Adapter adapter = loadFakerData(context);
        return adapter;
    }

    private RecyclerView.Adapter loadFakerData(Context context) {
        List<CommentUIBean> list = new ArrayList<>();
        list.add(new CommentUIBean());
        list.add(new CommentUIBean());
        list.add(new CommentUIBean());
        list.add(new CommentUIBean());
        list.add(new CommentUIBean());
        list.add(new CommentUIBean());
        return new DetailCommentAdapter(context, list);
    }
}
