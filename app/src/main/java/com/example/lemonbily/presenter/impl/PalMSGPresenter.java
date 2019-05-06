package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IPalMSGModelImpl;
import com.example.lemonbily.view.ui.IPalMSGView;

public class PalMSGPresenter extends BasePresenter<IPalMSGView> {
    public PalMSGPresenter() {

    }

    public PalMSGPresenter(IPalMSGView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IPalMSGModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }
}
