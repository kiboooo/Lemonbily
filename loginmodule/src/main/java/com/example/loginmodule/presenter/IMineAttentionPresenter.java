package com.example.loginmodule.presenter;

import android.support.v7.widget.RecyclerView;

public interface IMineAttentionPresenter {
    void loadBuddyRelationship(int uid);

    void loadBuddyRelationshipSuccess();

    void loadBuddyRelationshipFail();

    RecyclerView.Adapter produceAttentionAdapter();
}
