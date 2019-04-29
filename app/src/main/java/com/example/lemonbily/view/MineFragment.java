package com.example.lemonbily.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.MinePresenter;
import com.example.lemonbily.view.ui.IMineView;

@Route(path = "/Lemonbily/MineFragment")
public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements IMineView,View. OnClickListener {


    private TextView loginButton;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        if (LoginStatusUtils.isLogin){
            // 登录状态获取数据，登录后有关的数据
            initAccountData(LoginStatusUtils.mLogin.getId());
        }
    }

    @Override
    protected void initFragmentChildView(View view) {
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
    }

    @Override
    protected int initFragmentView() {
        if (LoginStatusUtils.isLogin){
            // 登录状态就加载我的界面
        }
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter(this,this);
    }

    private void initAccountData(int aid) {
        //加载用户数据
        mPresenter.initAccountData(aid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:{
                Log.e(TAG, "onClick loginButton");
                ARouter.getInstance()
                        .build("/LoginModule/LoginActivity")
                        .navigation(getContext(), new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                Log.d(TAG, "找到");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Log.d(TAG, "onLost");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d(TAG, "onArrival");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {

                            }
                        });

                break;
            }

                default:
                    break;
        }
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void accountInitSuccess() {
        showToasts("获取成功", Toast.LENGTH_SHORT);
    }

    @Override
    public void accountInitFail() {
    }
}
