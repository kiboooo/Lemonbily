package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.bean.UIBeans;
import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.R;
import com.example.lemonbily.model.IPalSquareModel;
import com.example.lemonbily.model.adapter.PalSquareAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;

import java.util.ArrayList;
import java.util.List;

public class IPalSquareModelImpl extends BaseModel<PalSquarePresenter>
        implements IPalSquareModel , onRecyclerViewItemClickListener {

    PalSquareAdapter palSquareAdapter;

    public IPalSquareModelImpl() {
    }

    public IPalSquareModelImpl(PalSquarePresenter presenter) {
        super(presenter);
    }

    public PalSquareAdapter produceAdapter(Context context) {
        List<UIBeans> test = new ArrayList<>();
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        test.add(new UIBeans(11, null));
        palSquareAdapter = new PalSquareAdapter(context, test);
        PalSquareAdapter.setItemClickListener(this);
        return palSquareAdapter;
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

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
}
