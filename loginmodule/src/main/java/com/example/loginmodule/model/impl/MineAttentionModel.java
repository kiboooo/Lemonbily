package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.loginmodule.model.IMineAttentionModel;
import com.example.loginmodule.model.adapter.MineAttentionAdapter;
import com.example.loginmodule.presenter.impl.MineAttentionPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.util.List;

public class MineAttentionModel extends BaseModel<MineAttentionPresenter>
        implements IMineAttentionModel {

    private MineAttentionAdapter attentionAdapter;

    public MineAttentionModel() {
    }

    public MineAttentionModel(MineAttentionPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerLoadBuddyDataObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerPalErrorBusObserver(owner);
    }

    @Override
    public void loadBuddyRelationship(int uid) {
        PalSquareNetServer.getInstance().loadBuddyRelationshipData(uid);
    }

    @Override
    public RecyclerView.Adapter produceAttentionAdapter() {
        if (PalSquareUtils.buddyRelationshipBeans != null) {
            attentionAdapter = new MineAttentionAdapter(PalSquareUtils.buddyRelationshipBeans);
            return attentionAdapter;
        }
        return null;
    }

    /* 注册数据总线事件监听 */
    private void registerLoadBuddyDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .LOAD_BUDDY_RELATIONSHIP_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取Buddy出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                if (jsonResponse.getData() != null) {
                                    PalSquareUtils.buddyRelationshipBeans = (List<Account>) jsonResponse.getData();
                                    getPresenter().loadBuddyRelationshipSuccess();
                                } else {
                                    getPresenter().sendErrorMsg("获取Buddy为空，请稍后重试",
                                            Toast.LENGTH_SHORT);
                                    getPresenter().loadBuddyRelationshipFail();
                                }
                            }else if(jsonResponse.getCode() == 1004){
                                InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                                        .USER_INACTIVATION().post(null);
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().loadBuddyRelationshipFail();
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
