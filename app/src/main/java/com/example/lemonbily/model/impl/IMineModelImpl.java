package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IMineModel;
import com.example.lemonbily.presenter.MinePresenter;

public class IMineModelImpl extends BaseModel<MinePresenter> implements IMineModel {

    public IMineModelImpl() {
    }

    public IMineModelImpl(MinePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
