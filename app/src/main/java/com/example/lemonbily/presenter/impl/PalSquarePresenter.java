package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IPalSquareModelImpl;
import com.example.lemonbily.view.ui.IPalSquareView;

public class PalSquarePresenter extends BasePresenter<IPalSquareView> {

    public PalSquarePresenter() {
    }

    public PalSquarePresenter(IPalSquareView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IPalSquareModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }
}
