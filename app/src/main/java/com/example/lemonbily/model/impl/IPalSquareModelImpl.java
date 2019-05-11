package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.bean.Buddy;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Like;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.lemonbily.R;
import com.example.lemonbily.model.IPalSquareModel;
import com.example.lemonbily.model.adapter.PalSquareAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.util.List;

public class IPalSquareModelImpl extends BaseModel<PalSquarePresenter>
        implements IPalSquareModel, onRecyclerViewItemClickListener {

    private PalSquareAdapter palSquareAdapter;

    public IPalSquareModelImpl() {
    }

    public IPalSquareModelImpl(PalSquarePresenter presenter) {
        super(presenter);
    }

    public PalSquareAdapter produceAdapter(Context context) {
        if (PalSquareUtils.palSquareBeans != null) {
            palSquareAdapter = new PalSquareAdapter(context);
            PalSquareAdapter.setItemClickListener(this);
            return palSquareAdapter;
        } else {
            return null;
        }
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInitPalDataObserver(owner);
        registerPublishFinishBusObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerPalErrorBusObserver(owner);
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, @NonNull View v, int position) {
        int id = v.getId();
        if (vh instanceof PalSquareAdapter.PalSquareViewHolder) {
            switch (id) {
                case R.id.square_avatar:
                    toUserDetailPage();
                    break;
                case R.id.square_attention:
                    doAttention(position);
                    break;
                case R.id.square_detail_like_icon:
                    //修改该Item对应的数据中是否点赞的信息
                    doLike(position);//执行点赞操作；
                    break;
                case R.id.square_detail_conment_icon:
                    toPalDetailPage(position);
                    break;
                default:
                    break;
            }
        }
    }

    private void doAttention(int position) {
        getPresenter().showToast("点击了关注", Toast.LENGTH_SHORT);
        //上传关注信息
        PalSquareBean psb = palSquareAdapter.getPalSquareBean(position);
        if (psb != null) {
            int Buddyid;
            int Userid;
            if (LoginStatusUtils.mLogin.getId() > psb.getAccount().getAid()) {
                Buddyid = LoginStatusUtils.mLogin.getId();
                Userid = psb.getAccount().getAid();
            } else {
                Buddyid = psb.getAccount().getAid();
                Userid = LoginStatusUtils.mLogin.getId();
            }
            if (psb.isAttention()) {
                //关注操作
                Buddy b = new Buddy();
                b.setBuddyid(Buddyid);
                b.setUserid(Userid);
                PalSquareNetServer.getInstance().doAttentionOperating(b);
            } else {
                //取消关注操作
                PalSquareNetServer.getInstance().doUnAttentionOperating(Userid, Buddyid);
            }
        }
        palSquareAdapter.notifyDataSetChanged();
    }

    private void doLike(int position) {
        getPresenter().showToast("点击了点赞", Toast.LENGTH_SHORT);
        //上传点赞信息
        PalSquareBean psb = palSquareAdapter.getPalSquareBean(position);
        if (psb != null) {
            if (psb.isLike()) {
                //点赞操作
                Like l = new Like();
                l.setLtopalid(psb.getPalcircle().getPalid());
                l.setLuserid(LoginStatusUtils.mLogin.getId());
                PalSquareNetServer.getInstance().doLikeOperating(l);
            } else {
                //取消点赞操作
                PalSquareNetServer.getInstance().doUnLikeOperating(
                        LoginStatusUtils.mLogin.getId(),psb.getPalcircle().getPalid());
            }
        }
    }

    private void toPalDetailPage(int position) {
        getPresenter().showToast("点击了 评论", Toast.LENGTH_SHORT);
        ARouter.getInstance()
                .build("/CommentModule/CommentActivity")
                .withInt("position", position)
                .navigation();
    }

    private void toUserDetailPage() {
        getPresenter().showToast("点击了 头像", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongItemClick(RecyclerView.ViewHolder vh, @NonNull View v, int position) {

    }

    public void notifyRecyclerViewBindData() {
        if (PalSquareUtils.palSquareBeans != null) {
            palSquareAdapter.updateDataList();
        }
    }

    /* 注册数据总线事件监听 */
    private void registerInitPalDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .LOAD_PAL_SQUARE_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取PalData出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                if (jsonResponse.getData() != null) {
                                    PalSquareUtils.palSquareBeans = (List<PalSquareBean>) jsonResponse.getData();
                                    getPresenter().initPalDataSuccess();
                                } else {
                                    getPresenter().sendErrorMsg("获取PalData为空，请稍后重试",
                                            Toast.LENGTH_SHORT);
                                    getPresenter().initPalDataFail();
                                }
                            }else if(jsonResponse.getCode() == 1004){
                                InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                                        .USER_INACTIVATION().post(null);
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().initPalDataFail();
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

    private void registerPublishFinishBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .PUBLISH_PAL_FINISH()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (LoginStatusUtils.isLogin) {
                            PalSquareNetServer.getInstance().loadPalSquareAllData(LoginStatusUtils.mLogin.getId());
                        }
                    }
                });
    }
}
