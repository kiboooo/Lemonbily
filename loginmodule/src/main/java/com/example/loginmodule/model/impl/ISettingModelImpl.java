package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.ISettingModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.ISettingPresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class ISettingModelImpl  extends LoginBaseModel<ISettingPresenter> implements ISettingModel {
    public ISettingModelImpl() {
    }

    public ISettingModelImpl(ISettingPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerLogoutObserver(owner);
    }

    @Override
    public void logout(String phone) {
        LoginNetServer.getInstance().logout(phone);
    }


    //登出事件的观察者
    private void registerLogoutObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGOUT_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
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
