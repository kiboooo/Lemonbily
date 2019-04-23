package com.example.lemonbily.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.MinePresenter;
import com.example.lemonbily.view.ui.IMineView;

@Route(path = "/Lemonbily/MineFragment")
public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements View. OnClickListener {


    private TextView loginButton;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected void initFragmentChildView(View view) {
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
    }

    @Override
    protected int initFragmentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
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
                                Log.e(TAG, "找到");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Log.e(TAG, "onLost");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.e(TAG, "onArrival");
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
}
