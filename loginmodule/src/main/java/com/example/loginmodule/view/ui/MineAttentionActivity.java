package com.example.loginmodule.view.ui;

import android.os.Bundle;

import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.presenter.impl.MineAttentionPresenter;
import com.example.loginmodule.view.IMineAttentionView;

public class MineAttentionActivity extends BaseActivity<IMineAttentionView, MineAttentionPresenter>
        implements IMineAttentionView {

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initSP() {

    }

    @Override
    public MineAttentionPresenter initPresenter() {
        return new MineAttentionPresenter(this, this);
    }

    @Override
    protected void initBinding() {

    }

    @Override
    public void initListener() {

    }
}
