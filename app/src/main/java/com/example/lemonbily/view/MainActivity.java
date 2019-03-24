package com.example.lemonbily.view;

import android.os.Bundle;
import android.view.View;

import com.example.lemonbily.R;
import com.example.lemonbily.presenter.MainPresenter;
import com.example.lemonbily.view.ui.IMainView;

import base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void init() {
        presenter = MainPresenter.getInstance().init(this);
    }

    @Override
    public void goToLogin(View view) {
        presenter.ToActivity(MainActivity.this,
                "com.example.loginmodule.view.ui.LoginActivity");
    }

    @Override
    public void goToVideo(View view) {

        presenter.ToActivity(MainActivity.this,
                "com.example.videoplaymodule.VideoPlayActivity");
    }


}
