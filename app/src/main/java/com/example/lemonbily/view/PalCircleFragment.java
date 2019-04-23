package com.example.lemonbily.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.PalCirclePresenter;
import com.example.lemonbily.view.ui.IPalCircleView;

@Route(path = "/Lemonbily/PalCircleFragment")
public class PalCircleFragment extends BaseFragment<IPalCircleView, PalCirclePresenter> {

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_pal_circle;
    }

    @Override
    protected PalCirclePresenter initPresenter() {
        return new PalCirclePresenter();
    }
}
