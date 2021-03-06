package com.example.loginmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.impl.MineCollectModel;
import com.example.loginmodule.presenter.IMineCollectPresenter;
import com.example.loginmodule.view.IMineCollectView;

public class MineCollectPresenter extends BasePresenter<IMineCollectView> implements IMineCollectPresenter {
    public MineCollectPresenter() {
    }

    public MineCollectPresenter(IMineCollectView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new MineCollectModel(this);
    }
    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void doLoadCollectVideoData(int uid) {
        ((MineCollectModel) getBaseModel()).doLoadCollectVideoData(uid);
    }

    @Override
    public void loadCollectVideoDataSuccess() {
        getView().loadCollectVideoDataSuccess();
    }

    @Override
    public void loadCollectVideoDataFail() {
        getView().loadCollectVideoDataFail();
    }

    @Override
    public RecyclerView.Adapter produceCollectAdapter() {
        return ((MineCollectModel) getBaseModel()).produceAdapter();
    }
}
