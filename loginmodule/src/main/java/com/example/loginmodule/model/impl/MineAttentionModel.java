package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.loginmodule.model.IMineAttentionModel;
import com.example.loginmodule.presenter.impl.MineAttentionPresenter;

public class MineAttentionModel extends BaseModel<MineAttentionPresenter>
        implements IMineAttentionModel {

    public MineAttentionModel() {
    }

    public MineAttentionModel(MineAttentionPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
