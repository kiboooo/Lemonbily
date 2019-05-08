package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.LoginNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.lemonbily.model.IMineModel;
import com.example.lemonbily.presenter.impl.MinePresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class IMineModelImpl extends BaseModel<MinePresenter> implements IMineModel {

    public IMineModelImpl() {
    }

    public IMineModelImpl(MinePresenter presenter) {
        super(presenter);
    }

    public void initAccountData(int aid) {
        LoginNetServer.getInstance().getAccount(aid);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInitAccountObserver(owner);
        registerLoginEvenObserver(owner);
        modifyAccountObserver(owner);
        modifyAvatarObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerLoginErrorBusObserver(owner);
    }

    //处理所有发生在获取Account用户请求情况下的Error
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

    private void registerInitAccountObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .INIT_ACCOUNT_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取Account出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.mAccount = (Account) jsonResponse.getData();
                                getPresenter().accountInitSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().accountInitFail();
                            }
                        }

                    }
                });
    }

    //登录事件的观察者
    private void registerLoginEvenObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .MINE_UI_DATA_UPDATE()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (s != null && s.equals("update")) {
                            initAccountData(LoginStatusUtils.mLogin.getId());
                        }
                    }
                });
    }

    //更新Account事件的观察者
    private void modifyAccountObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .MODIFY_ACCOUNT_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null != jsonResponse) {
                            if (jsonResponse.getCode() == 0) {
                                    getPresenter().updateUIView();
                            }
                        }

                    }
                });
    }

    //上传头像事件的观察者
    private void modifyAvatarObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .MODIFY_ACCOUNT_AVATAR_EVENT()
                .observeSticky(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null != jsonResponse) {
                            if (jsonResponse.getCode() == 0) {
                                getPresenter().updateUIView();
                            }
                        }

                    }
                });
    }


}
