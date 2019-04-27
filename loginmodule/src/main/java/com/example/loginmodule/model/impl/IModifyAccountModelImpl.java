package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.IModifyAccountModel;
import com.example.loginmodule.model.net.LoginNetServer;
import com.example.loginmodule.presenter.IModifyAccountPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class IModifyAccountModelImpl extends LoginBaseModel<IModifyAccountPresenter> implements IModifyAccountModel {

    private MultipartBody.Part mAvatarPart = null;

    public IModifyAccountModelImpl() {
    }

    public IModifyAccountModelImpl(IModifyAccountPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        modifyAccountObserver(owner);
        modifyAvatarObserver(owner);
    }

    @Override
    public void modifyAccount(Account account, File file) {
        if (file != null) {
            mAvatarPart = produceMultiparBody(file);
        }
        LoginNetServer.getInstance().modifyAccount(account);
    }

    @Override
    public void modifyAccountAvatar(File file) {
        mAvatarPart = produceMultiparBody(file);
        LoginNetServer.getInstance().modifydAccountAvatar(
                LoginStatusUtils.mAccount.getAid(), mAvatarPart);
    }

    private MultipartBody.Part produceMultiparBody(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData("avatarImages", file.getName(), requestFile);
    }


    //更新Account事件的观察者
    private void modifyAccountObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .MODIFY_ACCOUNT_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("登出出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.mAccount = (Account) jsonResponse.getData();
                                if (mAvatarPart != null) {
                                    LoginNetServer.getInstance().modifydAccountAvatar(
                                            LoginStatusUtils.mAccount.getAid(), mAvatarPart);
                                }else
                                    getPresenter().modifySuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().modifFail();
                            }
                        }

                    }
                });
    }

    //上传头像事件的观察者
    private void modifyAvatarObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .MODIFY_ACCOUNT_AVATAR_EVENT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("登出出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0) {
                                LoginStatusUtils.mAccount = (Account) jsonResponse.getData();
                                getPresenter().modifySuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().modifFail();
                            }
                        }

                    }
                });
    }
}
