package com.example.videoplaymodule.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public interface IVideoDetailPagePresenter {
    RecyclerView.Adapter produceAdapter(Context context);

    void initCurrentCommentDataSuccess();

    void initCurrentCommentDataFail();

    void commentPublishSuccess();
    
    void commentPublishFail();

    boolean isCollect();

    void updateCollectState(Integer vid, int i);

    void uploadCommentData(String toString, Integer vid);

    void scrollToPosition(int position);
}
