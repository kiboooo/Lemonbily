package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.impl.MineCollectPresenter;
import com.example.loginmodule.view.IMineCollectView;

@Route(path = "/LoginModule/MineCollectActivity")
public class MineCollectActivity extends BaseActivity<IMineCollectView, MineCollectPresenter>
        implements IMineCollectView, View.OnClickListener {

    private ImageView backBtn;
    private TextView titleBar;
    private TextView nullTips;
    private RecyclerView recyclerView;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_mine_collect);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        titleBar = findViewById(R.id.base_normal_back_title);
        nullTips = findViewById(R.id.mine_collect_no_data_tips);
        recyclerView = findViewById(R.id.mine_collect_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
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
        if (LoginStatusUtils.isLogin) {
            mPresenter.doLoadCollectVideoData(LoginStatusUtils.mLogin.getId());
        }
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

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void loadCollectVideoDataSuccess() {
        if (LoginStatusUtils.isLogin) {
            RecyclerView.Adapter adapter = mPresenter.produceCollectAdapter();
            if (adapter != null) {
                nullTips.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void loadCollectVideoDataFail() {

    }
}
