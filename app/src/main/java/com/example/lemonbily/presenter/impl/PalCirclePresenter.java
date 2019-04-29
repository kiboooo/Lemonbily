package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IPalCircleModelImpl;
import com.example.lemonbily.view.ui.IPalCircleView;

public class PalCirclePresenter extends BasePresenter<IPalCircleView>  {

    public PalCirclePresenter() {
    }

    public PalCirclePresenter(IPalCircleView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IPalCircleModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }

}
