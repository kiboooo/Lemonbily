package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.adapter.HomeAdapter;
import com.example.lemonbily.model.impl.IHomeModelImpl;
import com.example.lemonbily.view.ui.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePressenter{

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


    @Override
    public HomeAdapter getHomeAdapter(Context context) {
        return ((IHomeModelImpl)getBaseModel()).produceAdapter(context);
    }

    @Override
    public void showToast(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void initHomeDataSuccess() {
        getView().initHomeDataSuccess();
    }

    @Override
    public void initHomeDataFail() {
        getView().initHomeDataFail();
    }
}
