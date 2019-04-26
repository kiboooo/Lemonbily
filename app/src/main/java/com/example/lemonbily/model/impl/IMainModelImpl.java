package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IMainModel;
import com.example.lemonbily.presenter.MainPresenter;

/**
 * 主要的对Model接口中的方法提供实现
 */
public class IMainModelImpl extends BaseModel<MainPresenter> implements IMainModel {
    public IMainModelImpl() {
    }

    public IMainModelImpl(MainPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
