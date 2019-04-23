package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.bean.Login;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.ILoginPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.lang.ref.SoftReference;

public class ILoginModelimpl implements ILoginModel {

    private Login mLoginSuccessful;
    private SoftReference<ILoginPresenter> presenter;

    public ILoginModelimpl() {
    }

    public ILoginModelimpl(ILoginPresenter presenter) {
        this.presenter = new SoftReference<>(presenter);
    }

    public ILoginPresenter getPresenter(){
        return presenter.get();
    }

    @Override
    public void AccountLogin(String phone, String passWord) {
        LoginNetServer.getInstance().login(phone, passWord);
    }

    @Override
    public void initLoginObservers(LifecycleOwner owner) {
        registerLoginErrorBusObserver(owner);
        registerLoginEvenObserver(owner);
    }


    //处理所有发生在Login情况下的
    private void registerLoginErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGIN_REQUEST_ERROR()
                .observeSticky(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (getPresenter() != null) {
                            getPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    private void registerLoginEvenObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGIN_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("登陆出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                            getPresenter().loginFail();
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                mLoginSuccessful = (Login) jsonResponse.getData();
                                LoginStatusUtils.isLogin = true;
                                LoginStatusUtils.token = jsonResponse.getToken();
                                getPresenter().loginSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().loginFail();
                            }
                        }

                    }
                });
    }


}
