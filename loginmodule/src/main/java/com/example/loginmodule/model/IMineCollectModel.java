package com.example.loginmodule.model;

import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;

public interface IMineCollectModel extends IBaseModel {

    void doLoadCollectVideoData(int uid);

    RecyclerView.Adapter produceAdapter();
}
