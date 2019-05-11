package com.example.loginmodule.view.ui;

import android.os.Bundle;

import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.presenter.impl.MineCollectPresenter;
import com.example.loginmodule.view.IMineCollectView;

public class MineCollectActivity extends BaseActivity<IMineCollectView, MineCollectPresenter>
        implements IMineCollectView{
    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initSP() {

    }

    @Override
    public MineCollectPresenter initPresenter() {
        return new MineCollectPresenter(this, this);
    }

    @Override
    protected void initBinding() {

    }

    @Override
    public void initListener() {

    }
}
