package com.example.loginmodule.presenter;

import com.example.basemodule.presenter.IBasePresenter;

public interface ILoginPresenter extends IBasePresenter {
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
