package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IMainModelImpl;
import com.example.lemonbily.view.ui.IMainView;

public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter() {
    }

    public MainPresenter(IMainView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IMainModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }

}
