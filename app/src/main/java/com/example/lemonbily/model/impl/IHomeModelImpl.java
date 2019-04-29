package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IHomeModel;
import com.example.lemonbily.presenter.impl.HomePresenter;

public class IHomeModelImpl extends BaseModel<HomePresenter> implements IHomeModel {

    public IHomeModelImpl() {
    }

    public IHomeModelImpl(HomePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
