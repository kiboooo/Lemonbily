package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.impl.ILoginModelimpl;
import com.example.loginmodule.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{

    private ILoginModel loginModel;

    public LoginPresenter(ILoginView loginView, LifecycleOwner owner) {
        this.loginModel = new ILoginModelimpl(this);
        attachView(loginView);
        loginModel.initLoginObservers(owner);
    }

    @Override
    public void sendErrorMsg(String msg,int state) {
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
        loginModel.AccountLogin(phone, passWord);
    }

//    private  static class SingletonPresenter {
//        private static final LoginPresenter presenter = new LoginPresenter();
//    }

//    public static LoginPresenter getInstance(){
//        return SingletonPresenter.presenter;
//    }

//    public LoginPresenter init() {
//
//        return this;
//    }

}
