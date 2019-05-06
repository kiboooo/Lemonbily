package com.example.lemonbily.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.PalMSGPresenter;
import com.example.lemonbily.view.ui.IPalMSGView;

@Route(path = "/Lemonbily/PalCircleMSGFragment")
public class PalCircleMSGFragment extends BaseFragment<IPalMSGView, PalMSGPresenter> implements IPalMSGView {
    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_pal_msg;
    }

    @Override
    protected PalMSGPresenter initPresenter() {
        return new PalMSGPresenter(this, this);
    }
}
