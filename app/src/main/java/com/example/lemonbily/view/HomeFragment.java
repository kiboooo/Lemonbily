package com.example.lemonbily.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.HomePresenter;
import com.example.lemonbily.view.ui.IHomeView;

@Route(path = "/Lemonbily/HomeFragment")
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> {

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }
}
