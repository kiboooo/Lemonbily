package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Login;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.IChangePasswordModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.IChangePasswordPresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class IChangePasswordModelImpl extends LoginBaseModel<IChangePasswordPresenter>
        implements IChangePasswordModel {

    public IChangePasswordModelImpl() {
    }

    public IChangePasswordModelImpl(IChangePasswordPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerChangePasswordObserver(owner);
    }

    @Override
    public void changePassword(int id,String phone,String oldPassWord,String newPassWord) {
        LoginNetServer.getInstance().changePassWord(id, phone, oldPassWord, newPassWord);
    }

    //修改密码事件的观察者
    private void registerChangePasswordObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .CHANGE_PASSWORD_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("修改密码出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.mLogin = (Login) jsonResponse.getData();
                                getPresenter().changePasswordSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().changePasswordFail();
                            }
                        }

                    }
                });
    }
}
