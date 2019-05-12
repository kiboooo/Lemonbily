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
import com.example.loginmodule.presenter.impl.MineAttentionPresenter;
import com.example.loginmodule.view.IMineAttentionView;

@Route(path = "/LoginModule/MineAttentionActivity")
public class MineAttentionActivity extends BaseActivity<IMineAttentionView, MineAttentionPresenter>
        implements IMineAttentionView, View.OnClickListener {

    private ImageView backBtn;
    private TextView titleBar;
    private TextView nullTips;
    private RecyclerView recyclerView;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_mine_attention);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        titleBar = findViewById(R.id.base_normal_back_title);
        nullTips = findViewById(R.id.mine_attention_no_data_tips);
        recyclerView = findViewById(R.id.mine_attention_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
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
        titleBar.setText("我的关注");
        if (LoginStatusUtils.isLogin) {
            mPresenter.loadBuddyRelationship(LoginStatusUtils.mLogin.getId());
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
    public void loadBuddyRelationshipSuccess() {
        RecyclerView.Adapter adapter = mPresenter.produceAttentionAdapter();
        if (adapter != null) {
            nullTips.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void loadBuddyRelationshipFail() {

    }
}
