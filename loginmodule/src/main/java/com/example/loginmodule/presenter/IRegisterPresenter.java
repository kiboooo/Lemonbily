package com.example.loginmodule.presenter;

import com.example.basemodule.presenter.IBasePresenter;

public interface IRegisterPresenter extends IBasePresenter {

    void sendErrorMsg(String msg,int state);

    void registerSuccess();

    void registerFail();
}
