package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.ILoginModel;
import com.example.basemodule.bean.Login;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.ILoginPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.lang.ref.SoftReference;

public class ILoginModelimpl implements ILoginModel {

    private SoftReference<ILoginPresenter> loginPresenter;

    public ILoginModelimpl() {
    }

    public  ILoginModelimpl(ILoginPresenter presenter){
        this.loginPresenter = new SoftReference<>(presenter);
    }


    public ILoginPresenter getLoginPresenter(){
        return loginPresenter.get();
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
                        if (getLoginPresenter() != null) {
                            getLoginPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    //登录事件的观察者
    private void registerLoginEvenObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGIN_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getLoginPresenter().sendErrorMsg("登陆出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                            getLoginPresenter().loginFail();
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = true;
                                LoginStatusUtils.token = jsonResponse.getToken();
                                LoginStatusUtils.mLogin = (Login) jsonResponse.getData();
                                getLoginPresenter().loginSuccess();
                            } else {
                                getLoginPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getLoginPresenter().loginFail();
                            }
                        }

                    }
                });
    }

    //登出事件的观察者
    private void registerLogoutObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGOUT_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getLoginPresenter().sendErrorMsg("登出出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = false;
                                LoginStatusUtils.token = "";
                                LoginStatusUtils.mLogin = null;
                                getLoginPresenter().logoutSuccess();
                            } else {
                                getLoginPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getLoginPresenter().logoutFail();
                            }
                        }

                    }
                });
    }





}
