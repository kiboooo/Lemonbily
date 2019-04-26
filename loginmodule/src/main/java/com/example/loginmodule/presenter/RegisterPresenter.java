package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.bean.Login;
import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.IRegisterModel;
import com.example.loginmodule.model.impl.IRegisterModelImpl;
import com.example.loginmodule.view.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    public RegisterPresenter(IRegisterView registerView, LifecycleOwner owner) {
        super(registerView,owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IRegisterModelImpl(this);
    }

    public void accountRegister(Login login ,String name,String gender) {
        ((IRegisterModel) getBaseModel()).register(login,name,gender);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().doHideLoading();
        getView().showToast(msg,state);
    }

    @Override
    public void registerSuccess() {
        getView().registerSuccess();
    }

    @Override
    public void registerFail() {
        getView().registerFail();
    }
}
