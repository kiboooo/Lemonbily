package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Login;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.IRegisterModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.IRegisterPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.lang.ref.SoftReference;

public class IRegisterModelImpl implements IRegisterModel {

    private SoftReference<IRegisterPresenter> registerPresenter;

    public IRegisterModelImpl() {
    }

    public  IRegisterModelImpl(IRegisterPresenter presenter){
        this.registerPresenter = new SoftReference<>(presenter);
    }

    public IRegisterPresenter getRegisterPresenter() {
        return registerPresenter.get();
    }

    @Override
    public void initRegisterObservers(LifecycleOwner owner) {
        registerRegisterObserver(owner);
    }

    @Override
    public void register(Login login) {
        LoginNetServer.getInstance().registered(login);
    }


    //修改密码事件的观察者
    private void registerRegisterObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .REGISTER_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getRegisterPresenter().sendErrorMsg("注册出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.isLogin = true;
                                LoginStatusUtils.token = jsonResponse.getToken();
                                LoginStatusUtils.mLogin = (Login) jsonResponse.getData();
                                getRegisterPresenter().registerSuccess();
                            } else {
                                getRegisterPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getRegisterPresenter().registerFail();
                            }
                        }

                    }
                });
    }
}
