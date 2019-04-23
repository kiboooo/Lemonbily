package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.presenter.RegisterPresenter;
import com.example.loginmodule.view.IRegisterView;

public class RegisterActivity extends BaseActivity<IRegisterView, RegisterPresenter>
        implements View.OnClickListener, IRegisterView {

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(this, this);
    }

    @Override
    protected void initBinding() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showToast(String msg, int state) {
        Toast.makeText(this, msg, state).show();
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFail() {

    }
}
