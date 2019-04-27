package com.example.loginmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.impl.ILoginModelimpl;
import com.example.loginmodule.presenter.ILoginPresenter;
import com.example.loginmodule.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public LoginPresenter(ILoginView loginView, LifecycleOwner owner) {
        super(loginView,owner);
    }

    @Override
    protected ILoginModel initModel() {
        return new ILoginModelimpl(this);
    }

    @Override
    public void sendErrorMsg(String msg,int state) {
        getView().dohide();
        getView().showToast(msg, state);
    }

    @Override
    public void loginSuccess() {
        getView().loginSuccess();
    }

    @Override
    public void loginFail() {
        getView().loginFail();
    }


    public void login(String phone, String passWord) {
        ((ILoginModelimpl)getBaseModel()).AccountLogin(phone, passWord);
    }

}
