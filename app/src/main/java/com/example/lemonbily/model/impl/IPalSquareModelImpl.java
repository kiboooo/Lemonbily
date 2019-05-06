package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.IPalSquareModel;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;

public class IPalSquareModelImpl extends BaseModel<PalSquarePresenter>
        implements IPalSquareModel {

    public IPalSquareModelImpl() {
    }

    public IPalSquareModelImpl(PalSquarePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
