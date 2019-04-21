package com.example.loginmodule.view.ui;

import android.os.Bundle;

import com.example.basemodule.presenter.BasePresenter;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;

public class LoginActivity extends BaseActivity {


    @Override
    protected void initBinding() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_login);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initListener() {

    }
}
