package com.example.loginmodule.presenter;

public interface IRegisterPresenter {

    void sendErrorMsg(String msg,int state);

    void registerSuccess();

    void registerFail();
}
