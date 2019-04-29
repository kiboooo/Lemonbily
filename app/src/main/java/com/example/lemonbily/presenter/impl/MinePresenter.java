package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.IMineModel;
import com.example.lemonbily.model.impl.IMineModelImpl;
import com.example.lemonbily.presenter.IMindPressenter;
import com.example.lemonbily.view.ui.IMineView;

public class MinePresenter extends BasePresenter<IMineView> implements IMindPressenter {

    public MinePresenter(IMineView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IMineModelImpl(this);
    }

    public void initAccountData(int aid) {
        ((IMineModel) getBaseModel()).initAccountData(aid);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg, state);
        Log.e("MinePresenter", msg);
    }

    @Override
    public void accountInitSuccess() {
        getView().accountInitSuccess();
    }

    @Override
    public void accountInitFail() {
        getView().accountInitFail();
    }
}
