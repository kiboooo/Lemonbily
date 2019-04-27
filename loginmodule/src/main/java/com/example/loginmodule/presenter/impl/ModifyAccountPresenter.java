package com.example.loginmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.bean.Account;
import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.loginmodule.model.impl.IModifyAccountModelImpl;
import com.example.loginmodule.presenter.IModifyAccountPresenter;
import com.example.loginmodule.view.IModifyAccountView;

import java.io.File;

public class ModifyAccountPresenter extends BasePresenter<IModifyAccountView> implements IModifyAccountPresenter {

    public ModifyAccountPresenter() {
    }

    public ModifyAccountPresenter(IModifyAccountView v, LifecycleOwner owner) {
        super(v, owner);
    }

    public void modifyAccountData(Account account, File avatar) {
        ((IModifyAccountModelImpl) getBaseModel()).modifyAccount(account, avatar);
    }

    public void modifyAccountAvatar(File file) {
        ((IModifyAccountModelImpl) getBaseModel()).modifyAccountAvatar(file);
    }


    @Override
    protected IModifyAccountView getView() {
        return super.getView();
    }

    @Override
    protected IBaseModel initModel() {
        return new IModifyAccountModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().doHider();
        getView().showToast(msg, state);
    }

    @Override
    public void modifySuccess() {
        getView().modifySuccess();
    }

    @Override
    public void modifFail() {
        getView().modifFail();
    }
}
