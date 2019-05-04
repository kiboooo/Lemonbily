package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.bean.UIBeans;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.model.IHomeModel;
import com.example.lemonbily.model.adapter.BannerAdapter;
import com.example.lemonbily.model.adapter.HomeAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.model.bean.HomeUIBeans;
import com.example.lemonbily.presenter.impl.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class IHomeModelImpl extends BaseModel<HomePresenter> implements IHomeModel, onRecyclerViewItemClickListener {

    private List<UIBeans> uiBeansList = new ArrayList<>();
    private HomeAdapter homeAdapter;

    public IHomeModelImpl() {
    }

    public IHomeModelImpl(HomePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }

    public HomeAdapter produceAdapter(Context context) {
        loadHomeData();
        homeAdapter = new HomeAdapter(context,uiBeansList);
        homeAdapter.addFootView();
        homeAdapter.setItemClickListener(this);
        return homeAdapter;
    }

    private void loadHomeData(){
        uiBeansList.add(new UIBeans(HomeUIBeans.BANNER_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.NORMAL_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.TOW_IN_LINE_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.FOUR_GRID_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.NORMAL_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.TOW_IN_LINE_VIEW, null));
        uiBeansList.add(new UIBeans(HomeUIBeans.NORMAL_VIEW, null));
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, View v, int position) {
        if (vh instanceof HomeAdapter.NormalViewHolder
                || vh instanceof HomeAdapter.TwoViewHolder
                || vh instanceof HomeAdapter.FourViewHolder) {
            if (uiBeansList != null && uiBeansList.size() > 0) {
                getPresenter().showToast("viewType is "
                        + uiBeansList.get(position).getUiType(), Toast.LENGTH_SHORT);
            }
        }
        if (vh instanceof BannerAdapter.BannerItemViewHolder) {
            getPresenter().showToast(CommonUtils.initBannerList().get(position), Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void onLongItemClick(RecyclerView.ViewHolder vh, View v, int position) {

    }
}
