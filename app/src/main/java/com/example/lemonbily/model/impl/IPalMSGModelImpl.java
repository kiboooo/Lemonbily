package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IPalMSGModel;
import com.example.lemonbily.presenter.impl.PalMSGPresenter;

public class IPalMSGModelImpl extends BaseModel<PalMSGPresenter> implements IPalMSGModel {
    public IPalMSGModelImpl() {
    }

    public IPalMSGModelImpl(PalMSGPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
