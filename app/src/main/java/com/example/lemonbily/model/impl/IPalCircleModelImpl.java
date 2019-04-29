package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IPalCircleModel;
import com.example.lemonbily.presenter.impl.PalCirclePresenter;

public class IPalCircleModelImpl extends BaseModel<PalCirclePresenter> implements IPalCircleModel {

    public IPalCircleModelImpl() {
    }

    public IPalCircleModelImpl(PalCirclePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
