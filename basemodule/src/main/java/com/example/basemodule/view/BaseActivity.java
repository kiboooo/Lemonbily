package com.example.basemodule.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.presenter.BasePresenter;


public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    public Context mContext;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColorForStatus();
        initSP();
        initView(savedInstanceState);
        init();
    }



    @Override
    protected void onStart () {
        super.onStart();
        initBinding();
        initListener();
    }

    private void init(){
        ARouter.getInstance().inject(this);
        mContext = BaseActivity.this;
        mPresenter = initPresenter();
        mLoadingDialog = new LoadingDialog();
    }

    /**
     * 初始化Activity对应的视图，以及控件绑定
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化SP中存放的资源
     */
    protected abstract void initSP();

    /**
     * 初始化MVP架构的P层处理逻辑
     */
    public abstract T initPresenter();

    protected abstract void initBinding();

    /**
     * 初始化该页面的监听事件
     */
    public abstract void initListener();

    /**
     * 实现状态栏上字体颜色
     * 验证：MIUI
     */
    private void setColorForStatus(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    public LoadingDialog getmLoadingDialog() {
        return mLoadingDialog;
    }
    public void showLoading() {
        if (mLoadingDialog != null && !mLoadingDialog.isVisible()){
            mLoadingDialog.showLoading(getSupportFragmentManager());
        }
    }

    public void hideLoading(){
        if (mLoadingDialog != null && mLoadingDialog.isVisible()) {
            mLoadingDialog.dismiss();
        }
    }

    protected void showToasts(String msg, int state) {
        Toast.makeText(this, msg, state).show();
    }

}
