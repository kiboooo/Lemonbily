package com.example.loginmodule.view;

public interface ILoginView {

     void showToast(String msg,int state);

    void loginSuccess();

    void loginFail();

    void getLoginObjSuccess();

    void getLoginObjFail();

    void logoutSuccess();

    void logoutFail();

    void permanentLogoutSuccess();

    void permanentLogoutFail();

    void changePassWordSuccess();

    void changePassWordFail();

}
