package com.example.lemonbily.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.PalSquarePresenter;
import com.example.lemonbily.view.ui.IPalSquareView;

@Route(path = "/Lemonbily/PalCircleSquareFragment")
public class PalCircleSquareFragment  extends BaseFragment<IPalSquareView, PalSquarePresenter>
        implements IPalSquareView {
    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_pal_square;
    }

    @Override
    protected PalSquarePresenter initPresenter() {
        return new PalSquarePresenter(this, this);
    }
}
