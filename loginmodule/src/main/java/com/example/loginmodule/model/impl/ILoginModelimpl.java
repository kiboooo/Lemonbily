package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Login;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.ILoginModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.ILoginPresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class ILoginModelimpl extends LoginBaseModel<ILoginPresenter> implements ILoginModel {

    public ILoginModelimpl() {
    }

    public ILoginModelimpl(ILoginPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerLoginEvenObserver(owner);
        registerLogoutObserver(owner);
    }

    @Override
    public void AccountLogin(String phone, String passWord) {
        LoginNetServer.getInstance().login(phone, passWord);
    }

    //登录事件的观察者
    private void registerLoginEvenObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGIN_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("登陆出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                            getPresenter().loginFail();
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = true;
                                LoginStatusUtils.token = jsonResponse.getToken();
                                LoginStatusUtils.mLogin = (Login) jsonResponse.getData();
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

    //登出事件的观察者
    private void registerLogoutObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGOUT_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("登出出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = false;
                                LoginStatusUtils.token = "";
                                LoginStatusUtils.mLogin = null;
                                getPresenter().logoutSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().logoutFail();
                            }
                        }

                    }
                });
    }





}
