package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.bean.UIBeans;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.R;
import com.example.lemonbily.model.IHomeModel;
import com.example.lemonbily.model.adapter.BannerAdapter;
import com.example.lemonbily.model.adapter.HomeAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.model.bean.HomeUIBeans;
import com.example.lemonbily.model.viewHolder.FourViewHolder;
import com.example.lemonbily.model.viewHolder.OneViewHolder;
import com.example.lemonbily.model.viewHolder.TwoViewHolder;
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
        homeAdapter = new HomeAdapter(context, uiBeansList);
        homeAdapter.addFootView();
        homeAdapter.setItemClickListener(this);
        return homeAdapter;
    }

    private void loadHomeData() {
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
        int id = v.getId();
        if (vh instanceof OneViewHolder) {
            switch (id) {
                case R.id.one_item_title:
                    getPresenter().showToast("onClick OneViewHolder title", Toast.LENGTH_SHORT);
                    break;
                case R.id.one_item_image_view:
                case R.id.one_main_content:
                case R.id.one_introduction_content:
                    getPresenter().showToast("onClick to detailed page", Toast.LENGTH_SHORT);
                    break;
                default:
                    break;
            }
        }
        if (vh instanceof TwoViewHolder) {
            switch (id) {
                case R.id.tow_item_title:
                    getPresenter().showToast("onClick TwoViewHolder title", Toast.LENGTH_SHORT);
                    break;

                case R.id.two_onecard_main_content:
                case R.id.two_onecard_introduction_content:
                case R.id.tow_onecard_image_view:
                    getPresenter().showToast("onClick 111 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.two_twocard_main_content:
                case R.id.two_twocard_introduction_content:
                case R.id.tow_twocard_image_view:
                    getPresenter().showToast("onClick 222 to detailed page", Toast.LENGTH_SHORT);
                    break;

                default:
                    break;
            }
        }

        if (vh instanceof FourViewHolder) {
            switch (id) {
                case R.id.four_item_title:
                    getPresenter().showToast("onClick FourViewHolder title", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_onecard_main_content:
                case R.id.four_onecard_introduction_content:
                case R.id.four_onecard_image_view:
                    getPresenter().showToast("onClick 111 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_twocard_main_content:
                case R.id.four_twocard_introduction_content:
                case R.id.four_twocard_image_view:
                    getPresenter().showToast("onClick 222 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_threecard_main_content:
                case R.id.four_threecard_introduction_content:
                case R.id.four_threecard_image_view:
                    getPresenter().showToast("onClick 333 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_fourcard_main_content:
                case R.id.four_fourcard_introduction_content:
                case R.id.four_fourcard_image_view:
                    getPresenter().showToast("onClick 444 to detailed page", Toast.LENGTH_SHORT);
                    break;

                default:
                    if (uiBeansList != null && uiBeansList.size() > 0) {
                        getPresenter().showToast("viewType is "
                                + uiBeansList.get(position).getUiType(), Toast.LENGTH_SHORT);
                    }
                    break;
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
