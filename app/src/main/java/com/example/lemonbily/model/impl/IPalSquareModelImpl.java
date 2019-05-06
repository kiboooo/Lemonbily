package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.R;
import com.example.lemonbily.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.lemonbily.model.IPalSquareModel;
import com.example.lemonbily.model.adapter.PalSquareAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;
import com.jeremyliao.im.core.InvokingMessage;

import java.util.ArrayList;
import java.util.List;

public class IPalSquareModelImpl extends BaseModel<PalSquarePresenter>
        implements IPalSquareModel, onRecyclerViewItemClickListener {

    PalSquareAdapter palSquareAdapter;

    public IPalSquareModelImpl() {
    }

    public IPalSquareModelImpl(PalSquarePresenter presenter) {
        super(presenter);
    }

    public PalSquareAdapter produceAdapter(Context context) {
        List<PalSquareBean> test = new ArrayList<>();
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        test.add(new PalSquareBean());
        palSquareAdapter = new PalSquareAdapter(context, test);
        PalSquareAdapter.setItemClickListener(this);
        return palSquareAdapter;
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInitPalDataObserver(owner);
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
                    doAttention();
                    break;
                case R.id.square_like_icon:
                    //修改该Item对应的数据中是否点赞的信息

                    doLike();//执行点赞操作；
                    break;
                case R.id.square_conment_icon:
                    toPalDetailPage();
                    break;
                default:
                    break;
            }
        }
    }

    private void doAttention() {
        getPresenter().showToast("点击了关注", Toast.LENGTH_SHORT);
    }

    private void doLike() {
        getPresenter().showToast("点击了点赞", Toast.LENGTH_SHORT);
    }

    private void toPalDetailPage() {
        getPresenter().showToast("点击了 评论", Toast.LENGTH_SHORT);
    }

    private void toUserDetailPage() {
        getPresenter().showToast("点击了 头像", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLongItemClick(RecyclerView.ViewHolder vh, @NonNull View v, int position) {

    }

    /* 注册数据总线事件监听 */
    private void registerInitPalDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class)
                .LOAD_PAL_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取Video出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                getPresenter().initPalDataSuccess();
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
                .LOAD_PAL_DATA_ERROR()
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
