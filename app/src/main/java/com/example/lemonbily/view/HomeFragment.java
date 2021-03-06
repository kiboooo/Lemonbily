package com.example.lemonbily.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.HomePresenter;
import com.example.lemonbily.view.ui.IHomeView;
import com.example.basemodule.net.VideoNetServer;

@Route(path = "/Lemonbily/HomeFragment")
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter>
        implements IHomeView, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private boolean isRefresh = false;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }
    @Override
    protected void initFragmentChildView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh);
        swipeRefreshLayout.setProgressViewOffset(true, 50, 100);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.home_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter(this,this);
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void initHomeDataSuccess() {
        if (!isRefresh) {
            mRecyclerView.setAdapter(mPresenter.getHomeAdapter(getContext()));
        }else {
            isRefresh = false;
            swipeRefreshLayout.setRefreshing(false);
            mPresenter.adapterNotifyDataSetChanged();
        }
    }

    @Override
    public void initHomeDataFail() {
        showToasts("获取Video失败" , Toast.LENGTH_SHORT);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        //执行网络请求操作，获取新的首页数据；
        isRefresh = true;
        VideoNetServer.getInstance().loadAllVideoData();
    }
}
