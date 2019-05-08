package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.IBasePresenter;
import com.jeremyliao.im.core.InvokingMessage;

public abstract class LoginBaseModel<T extends IBasePresenter> extends BaseModel<T> implements IBaseModel {

    LoginBaseModel() {
    }

    LoginBaseModel(T presenter) {
        super(presenter);
    }
   public void initErrorObservers(LifecycleOwner owner){
        registerLoginErrorBusObserver(owner);
   }

    //处理所有发生在登录请求情况下的Error
    private void registerLoginErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .LOGIN_REQUEST_ERROR()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (getPresenter() != null) {
                            getPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
