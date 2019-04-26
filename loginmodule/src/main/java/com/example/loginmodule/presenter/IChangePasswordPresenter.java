package com.example.loginmodule.presenter;

import com.example.basemodule.presenter.IBasePresenter;

public interface IChangePasswordPresenter extends IBasePresenter {
    void sendErrorMsg(String msg,int state);

    void changePasswordSuccess();

    void changePasswordFail();
}
