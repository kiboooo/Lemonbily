package com.example.lemonbily.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.IHomeModelImpl;
import com.example.lemonbily.view.ui.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> {

    public HomePresenter(IHomeView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IHomeModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {

    }
}
