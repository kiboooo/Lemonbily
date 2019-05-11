package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.loginmodule.model.IMineCollectModel;
import com.example.loginmodule.presenter.impl.MineCollectPresenter;

public class MineCollectModel extends BaseModel<MineCollectPresenter>
        implements IMineCollectModel {
    public MineCollectModel() {
    }

    public MineCollectModel(MineCollectPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
