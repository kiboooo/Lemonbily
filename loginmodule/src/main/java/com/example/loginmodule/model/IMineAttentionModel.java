package com.example.loginmodule.model;

import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;

public interface IMineAttentionModel extends IBaseModel {

    void loadBuddyRelationship(int uid);

    RecyclerView.Adapter produceAttentionAdapter();
}
