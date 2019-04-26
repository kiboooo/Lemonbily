package com.example.lemonbily.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IMineModelImpl;
import com.example.lemonbily.view.ui.IMineView;

public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(IMineView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IMineModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }
}
