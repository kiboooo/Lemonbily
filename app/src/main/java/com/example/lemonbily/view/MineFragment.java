package com.example.lemonbily.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.MinePresenter;
import com.example.lemonbily.view.ui.IMineView;

@Route(path = "/Lemonbily/MineFragment")
public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements IMineView,View. OnClickListener {


    private TextView loginButton;
    private ViewStub loginAndRegisterView;
    private ImageView mineSetting;
    private ImageView mineAvaatr;
    private TextView mineAccountName;
    private ImageView mineGender;
    private TextView mineAttention;
    private TextView mineCollection;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        if (LoginStatusUtils.isLogin){
            // 登录状态获取数据，登录后有关的数据
            initAccountData(LoginStatusUtils.mLogin.getId());
            bindAccountData();
        }
    }


    @Override
    protected void initFragmentChildView(View view) {
        loginAndRegisterView = view.findViewById(R.id.not_login_view);
        if (!LoginStatusUtils.isLogin){
            // 非登录状态就加载注册登录界面
            if (loginAndRegisterView != null) {
                try {
                    View rootView  = loginAndRegisterView.inflate();
                    loginButton = rootView.findViewById(R.id.loginButton);
                    loginButton.setOnClickListener(new OnMutiClickListener() {
                        @Override
                        public void onMutiClick(View view) {
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
                        }
                    });
                } catch (Exception e) {
                    loginAndRegisterView.setVisibility(View.VISIBLE);
                }
            }
        }else {
            loginAndRegisterView.setVisibility(View.GONE);
            mineSetting = view.findViewById(R.id.mine_setting_btn);
            mineAccountName = view.findViewById(R.id.mine_account_name);
            mineAvaatr = view.findViewById(R.id.mine_avatar);
            mineGender = view.findViewById(R.id.mine_gender);
            mineAttention = view.findViewById(R.id.mine_attention);
            mineCollection = view.findViewById(R.id.mine_collection);

            mineSetting.setOnClickListener(this);
            mineAvaatr.setOnClickListener(this);
            mineAttention.setOnClickListener(this);
            mineCollection.setOnClickListener(this);
        }

    }

    @Override
    protected int initFragmentView() {
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

    private void bindAccountData() {
        //绑定用户数据
        if (LoginStatusUtils.mAccount != null) {
            Glide.with(this)
                    .load(NetWorkServer.SERVER_URL + LoginStatusUtils.mAccount.getAavatar())
                    .apply(CommonUtils.avatarRequestOption())
                    .into(mineAvaatr);
            mineAccountName.setText(LoginStatusUtils.mAccount.getAname());
            mineGender.setSelected(LoginStatusUtils.mAccount.getAsex().equals("w"));
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.mine_setting_btn) {
            ARouter.getInstance()
                    .build("/LoginModule/SettingActivity")
                    .navigation();
        }

        if (i == R.id.mine_attention) {

        }

        if (i == R.id.mine_collection) {

        }
        if (i == R.id.mine_avatar) {

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
