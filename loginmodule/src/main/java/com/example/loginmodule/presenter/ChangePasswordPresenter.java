package com.example.loginmodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.IChangePasswordModel;
import com.example.loginmodule.model.impl.IChangePasswordModelImpl;
import com.example.loginmodule.view.IChangePasswordView;

public class ChangePasswordPresenter extends BasePresenter<IChangePasswordView>
        implements IChangePasswordPresenter {

    public ChangePasswordPresenter() {
    }

    public ChangePasswordPresenter(IChangePasswordView v, LifecycleOwner owner) {
        super(v, owner);
    }

    public void changePassword(int id,String phone,String oldPassWord,String newPassWord) {
        ((IChangePasswordModel) getBaseModel()).changePassword(id, phone, oldPassWord, newPassWord);
    }

    @Override
    protected IBaseModel initModel() {
        return new IChangePasswordModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().doHider();
        getView().showToast(msg, state);
    }

    @Override
    public void changePasswordSuccess() {
        getView().changePasswodSuccess();
    }

    @Override
    public void changePasswordFail() {
        getView().changePasswordFail();
    }
}
