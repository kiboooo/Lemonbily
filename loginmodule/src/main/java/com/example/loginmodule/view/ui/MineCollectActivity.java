package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.impl.MineCollectPresenter;
import com.example.loginmodule.view.IMineCollectView;

@Route(path = "/LoginModule/MineCollectActivity")
public class MineCollectActivity extends BaseActivity<IMineCollectView, MineCollectPresenter>
        implements IMineCollectView, View.OnClickListener {

    private ImageView backBtn;
    private TextView titleBar;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_mine_collect);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        titleBar = findViewById(R.id.base_normal_back_title);
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
        titleBar.setText("我的收藏");
    }

    @Override
    public void initListener() {
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.base_normal_title_back_btn) {
            finish();
        }
    }
}
