package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.impl.ILoginModelimpl;
import com.example.loginmodule.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{

    private ILoginModel loginModel;

    private LoginPresenter() {
        this.loginModel = new ILoginModelimpl(this);
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

    public void login(String phone, String passWord) {
        loginModel.AccountLogin(phone, passWord);
    }

    private  static class SingletonPresenter {
        private static final LoginPresenter presenter = new LoginPresenter();
    }

    public static LoginPresenter getInstance(){
        return SingletonPresenter.presenter;
    }

    public LoginPresenter init(ILoginView loginView, LifecycleOwner owner) {
        attachView(loginView);
        loginModel.initLoginObservers(owner);
        NetWorkServer.getInstance().initRetrofit();
        return this;
    }

}
