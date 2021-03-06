package com.example.videoplaymodule.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;

public interface IViewDetailModel extends IBaseModel {
    RecyclerView.Adapter produceAdapter(Context context);

    boolean isCurVideoCollect();

    void updateCollectState(Integer vid, int i);

    void uploadCommentData(String toString, Integer vid);
}
