package com.example.publishmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.publishmodule.model.impl.PushModelImpl;
import com.example.publishmodule.presenter.IPushPresenter;
import com.example.publishmodule.view.ui.IPushCommentView;

public class PushPresenter extends BasePresenter<IPushCommentView> implements IPushPresenter {

    public PushPresenter() {
    }

    public PushPresenter(IPushCommentView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new PushModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void pushPalDataToServer(String content, int uid) {
        ((PushModelImpl) getBaseModel()).pushPalData(content, uid);
    }

    @Override
    public void pushCommentSuccess() {
        getView().pushCommentSuccess();
    }

    @Override
    public void pushCommentFail() {

    }
}
