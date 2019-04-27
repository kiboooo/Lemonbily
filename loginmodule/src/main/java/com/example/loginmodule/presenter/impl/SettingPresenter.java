package com.example.loginmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.impl.ISettingModelImpl;
import com.example.loginmodule.presenter.ISettingPresenter;
import com.example.loginmodule.view.ISettingView;

public class SettingPresenter extends BasePresenter<ISettingView> implements ISettingPresenter {

    public SettingPresenter() {
    }

    public SettingPresenter(ISettingView v, LifecycleOwner owner) {
        super(v, owner);
    }

    public void accountLogout(String phone) {
        ((ISettingModelImpl) getBaseModel()).logout(phone);
    }

    @Override
    protected IBaseModel initModel() {
        return new ISettingModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().doHider();
        getView().showToast(msg,state);
    }

    @Override
    public void logoutSuccess() {
        getView().logoutSuccess();
    }

    @Override
    public void logoutFail() {
        getView().logoutFail();
    }
}
