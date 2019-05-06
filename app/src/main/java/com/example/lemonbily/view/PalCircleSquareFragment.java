package com.example.lemonbily.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;
import com.example.lemonbily.view.ui.IPalSquareView;

@Route(path = "/Lemonbily/PalCircleSquareFragment")
public class PalCircleSquareFragment  extends BaseFragment<IPalSquareView, PalSquarePresenter>
        implements IPalSquareView,SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private boolean isRefresh = false;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        recyclerView.setAdapter(mPresenter.getPalSquareAdapter(getContext()));
    }

    @Override
    protected void initFragmentChildView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.pal_square_swipe_refresh);
        swipeRefreshLayout.setProgressViewOffset(true, 50, 100);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.pal_square_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_pal_square;
    }

    @Override
    protected PalSquarePresenter initPresenter() {
        return new PalSquarePresenter(this, this);
    }

    //下拉刷新
    @Override
    public void onRefresh() {

    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void initPalDataSuccess() {
    }

    @Override
    public void initPalDataFail() {

    }


}
