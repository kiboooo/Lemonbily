package com.example.publishmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Palcircle;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.publishmodule.model.IPushModel;
import com.example.publishmodule.presenter.impl.PushPresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class PushModelImpl extends BaseModel<PushPresenter> implements IPushModel {

    public PushModelImpl() {
    }

    public PushModelImpl(PushPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInsertPalDataObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerPalErrorBusObserver(owner);
    }

    @Override
    public void pushPalData(String content, int uid) {
        Palcircle curPalcircle = new Palcircle();
        curPalcircle.setPalcontent(content);
        curPalcircle.setPallicknum(0);
        curPalcircle.setPaluserid(uid);
        PalSquareNetServer.getInstance().pushPalCircleToServer(curPalcircle);
    }


    /* 注册数据总线事件监听 */
    private void registerInsertPalDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .INSERT_PAL_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("插入Palcircle出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                if (jsonResponse.getData() != null) {
                                    getPresenter().pushCommentSuccess();
                                } else {
                                    getPresenter().sendErrorMsg("插入Palcircle出错，请稍后重试",
                                            Toast.LENGTH_SHORT);
                                    getPresenter().pushCommentFail();
                                }
                            }else if(jsonResponse.getCode() == 1004){
                                InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                                        .USER_INACTIVATION().post(null);
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().pushCommentFail();
                            }
                        }

                    }
                });
    }


    private void registerPalErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .PAL_ERROR()
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
