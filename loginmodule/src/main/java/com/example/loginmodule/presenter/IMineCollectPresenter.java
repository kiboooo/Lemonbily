package com.example.loginmodule.presenter;

import android.support.v7.widget.RecyclerView;

public interface IMineCollectPresenter {
    void doLoadCollectVideoData(int uid);

    void loadCollectVideoDataSuccess();

    void loadCollectVideoDataFail();

    RecyclerView.Adapter produceCollectAdapter();
}
