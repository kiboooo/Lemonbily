package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Login;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.IRegisterModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.IRegisterPresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class IRegisterModelImpl extends LoginBaseModel<IRegisterPresenter> implements IRegisterModel {

    private String registerName;
    private String registerGender;

    public IRegisterModelImpl() {
    }

    public IRegisterModelImpl(IRegisterPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerRegisterObserver(owner);
        registerRegisterAccountObserver(owner);
    }

    @Override
    public void register(Login login, String name,String gender) {
        login.setLpassword(CommonUtils.passwordEncode(login.getLpassword()));
        LoginNetServer.getInstance().registered(login);
        registerName = name;
        registerGender = gender;
    }

    private void registerAccount(Account account) {
        LoginNetServer.getInstance().registeredAccount(account);
    }


    //注册事件的观察者
    private void registerRegisterObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .REGISTER_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("注册出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = true;
                                LoginStatusUtils.token = jsonResponse.getToken();
                                LoginStatusUtils.mLogin = (Login) jsonResponse.getData();
                                registerAccount(new Account(((Login) jsonResponse.getData()).getId(),
                                        registerName, registerGender,null));
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().registerFail();
                            }
                        }

                    }
                });
    }

    //注册到用户个人信息的观察者
    private void registerRegisterAccountObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .REGISTER_ACCOUNT_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("注册个人信息出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.mAccount = (Account) jsonResponse.getData();
                                getPresenter().registerSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().registerFail();
                            }
                        }

                    }
                });
    }
}
