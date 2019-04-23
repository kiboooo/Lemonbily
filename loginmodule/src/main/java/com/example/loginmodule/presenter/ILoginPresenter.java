package com.example.loginmodule.presenter;

public interface ILoginPresenter {
     void sendErrorMsg(String msg,int state);

     void loginSuccess();

     void loginFail();
}
