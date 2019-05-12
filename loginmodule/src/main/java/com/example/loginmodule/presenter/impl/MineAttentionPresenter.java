package com.example.loginmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.impl.MineAttentionModel;
import com.example.loginmodule.presenter.IMineAttentionPresenter;
import com.example.loginmodule.view.IMineAttentionView;

public class MineAttentionPresenter extends BasePresenter<IMineAttentionView> implements IMineAttentionPresenter {
    public MineAttentionPresenter() {
    }

    public MineAttentionPresenter(IMineAttentionView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new MineAttentionModel(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }

    @Override
    public void loadBuddyRelationship(int uid) {
        ((MineAttentionModel) getBaseModel()).loadBuddyRelationship(uid);
    }

    @Override
    public void loadBuddyRelationshipSuccess() {
        getView().loadBuddyRelationshipSuccess();
    }

    @Override
    public void loadBuddyRelationshipFail() {
        getView().loadBuddyRelationshipFail();
    }

    @Override
    public RecyclerView.Adapter produceAttentionAdapter() {
        return ((MineAttentionModel) getBaseModel()).produceAttentionAdapter();
    }
}
