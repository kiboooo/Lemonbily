package com.example.basemodule.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.presenter.BasePresenter;


public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColorForStatus();
        initView(savedInstanceState);
        init();
        initBinding();
        initListener();
    }

    protected abstract void initBinding();


    private void init(){
        ARouter.getInstance().inject(this);
        mContext = BaseActivity.this;
        mPresenter = initPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    /**
     * 初始化Activity对应的视图，以及控件绑定
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化MVP架构的P层处理逻辑
     */
    public abstract T initPresenter();

    /**
     * 初始化该页面的监听事件
     */
    public abstract void initListener();

    /**
     * 实现状态栏上字体颜色
     * 验证：MIUI
     */
    private void setColorForStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}
