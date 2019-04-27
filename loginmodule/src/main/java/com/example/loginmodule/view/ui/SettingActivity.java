package com.example.loginmodule.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.impl.SettingPresenter;
import com.example.loginmodule.view.ISettingView;

@Route(path = "/LoginModule/SettingActivity")
public class SettingActivity extends BaseActivity<ISettingView, SettingPresenter>
        implements View.OnClickListener,ISettingView {

    ImageView backBtn;
    TextView titleDescription;
    FrameLayout modifyData;
    FrameLayout logout;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_setting);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        titleDescription = findViewById(R.id.base_normal_back_title);
        modifyData = findViewById(R.id.setting_modify_data);
        logout = findViewById(R.id.setting_logout);
    }

    @Override
    public SettingPresenter initPresenter() {
        return new SettingPresenter(this, this);
    }

    @Override
    protected void initBinding() {
        titleDescription.setText("设置");
    }

    @Override
    public void initListener() {
        backBtn.setOnClickListener(this);
        modifyData.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                toModifyActivty();
            }
        });
        logout.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                doLogout();
            }
        });
    }

    private void doLogout() {
        if (LoginStatusUtils.isLogin) {
            showLoading();
            mPresenter.accountLogout(LoginStatusUtils.mLogin.getLphone());
        }else {
            //兜底逻辑，防止由于逻辑错误，在未登录之前，就进入设置页面
            showToasts("请先登录", Toast.LENGTH_SHORT);
            finish();
        }
    }


    private void toModifyActivty() {
        ARouter.getInstance().build("/LoginModule/ModifyAccountActivity").navigation();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.base_normal_title_back_btn) {
            finish();
        }
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg,state);
    }

    @Override
    public void logoutSuccess() {
        hideLoading();
        showToasts("登出成功", Toast.LENGTH_SHORT);
        ARouter.getInstance().build("/Lemonbily/MainActivity")
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .navigation();
    }

    @Override
    public void logoutFail() {
        hideLoading();
        showToasts("登出失败，请重试", Toast.LENGTH_SHORT);
    }

    @Override
    public void doHider() {
        hideLoading();
    }
}
