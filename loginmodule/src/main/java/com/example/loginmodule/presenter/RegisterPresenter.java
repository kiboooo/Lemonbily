package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.bean.Login;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.IRegisterModel;
import com.example.loginmodule.model.impl.IRegisterModelImpl;
import com.example.loginmodule.view.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    private IRegisterModel registerModel;

    public RegisterPresenter(IRegisterView registerView, LifecycleOwner owner) {
        this.registerModel = new IRegisterModelImpl(this);
        attachView(registerView);
        registerModel.initRegisterObservers(owner);
    }

    public void accountRegister(Login login) {

    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg,state);
    }

    @Override
    public void registerSuccess() {
        getView().registerSuccess();
    }

    @Override
    public void registerFail() {
        getView().registerFail();
    }
}
