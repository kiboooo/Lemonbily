package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.impl.ILoginModelimpl;
import com.example.loginmodule.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{

    public LoginPresenter(ILoginView loginView, LifecycleOwner owner) {
        super(loginView,owner);
    }

    @Override
    protected ILoginModel initModel() {
        return new ILoginModelimpl(this);
    }

    @Override
    public void sendErrorMsg(String msg,int state) {
        getView().doHideLoading();
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

    @Override
    public void getLoginObjSuccess() {

    }

    @Override
    public void getLoginObjFail() {

    }

    @Override
    public void logoutSuccess() {

    }

    @Override
    public void logoutFail() {

    }

    @Override
    public void permanentLogoutSuccess() {

    }

    @Override
    public void permanentLogoutFail() {

    }

    @Override
    public void changePassWordSuccess() {

    }

    @Override
    public void changePassWordFail() {

    }

    public void login(String phone, String passWord) {
        ((ILoginModelimpl)getBaseModel()).AccountLogin(phone, passWord);
    }

}
