package com.example.loginmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Video;
import com.example.basemodule.bus.generated.im.EventsDefineAsVideoEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.VideoNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.loginmodule.model.IMineCollectModel;
import com.example.loginmodule.model.adapter.MineCollectAdapter;
import com.example.loginmodule.presenter.impl.MineCollectPresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.util.List;

public class MineCollectModel extends BaseModel<MineCollectPresenter>
        implements IMineCollectModel{

    private MineCollectAdapter mineCollectAdapter;

    public MineCollectModel() {
    }

    public MineCollectModel(MineCollectPresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerLoadCollectVideoDataObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerVideoErrorBusObserver(owner);
    }
    @Override
    public void doLoadCollectVideoData(int uid) {
        if (LoginStatusUtils.isLogin) {
            VideoNetServer.getInstance().loadCollectVideoData(uid);
        }
    }

    @Override
    public RecyclerView.Adapter produceAdapter() {
        if (LoginStatusUtils.isLogin && PalSquareUtils.collectVideoBeans != null) {
            mineCollectAdapter  = new MineCollectAdapter(PalSquareUtils.collectVideoBeans);
            return mineCollectAdapter;
        }
        return null;
    }

    /* 注册数据总线事件监听 */
    private void registerLoadCollectVideoDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .LOAD_VIDEO_COLLECT_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取Video出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                PalSquareUtils.collectVideoBeans = (List<Video>) jsonResponse.getData();
                                getPresenter().loadCollectVideoDataSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().loadCollectVideoDataFail();
                            }
                        }

                    }
                });
    }

    private void registerVideoErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .VIDEO_REQUEST_ERROR()
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
