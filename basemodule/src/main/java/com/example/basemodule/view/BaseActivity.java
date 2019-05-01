package com.example.basemodule.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.presenter.BasePresenter;
import com.example.basemodule.utils.OnPermisstionCallback;
import com.example.basemodule.utils.PermissionUtils;


public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    public Context mContext;
    private LoadingDialog mLoadingDialog;
    private  String[] mPermissions;
    private  OnPermisstionCallback mCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColorForStatus();
        initSP();
        initView(savedInstanceState);
        init();
        initBinding();
    }



    @Override
    protected void onStart () {
        super.onStart();
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

    //授权权限
    protected void initPermission(@NonNull String[] permisstions,@NonNull int requestCode,
                                      @NonNull OnPermisstionCallback callback) {
        if (permisstions.length < 1) {
            return;
        }
        mPermissions = permisstions;
        mCallback = callback;
        if (PermissionUtils.checkPermission(this,permisstions)) {
            callback.onGranted();
        }else {
            requestPermissions(permisstions, requestCode);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.REQUEST_SAVE_PERMISSION:
                if (PermissionUtils.checkPermissionResult(grantResults)) {
                    if (mCallback != null) {
                        mCallback.onGranted();
                    }
                }else {
                    if (shouldShowRequestPermissionsRationale(permissions)) {
                        ActivityCompat.requestPermissions(this,
                                permissions, PermissionUtils.REQUEST_SAVE_PERMISSION);
                    }else {
                        //用户选择了不再提示，需要弹出提示Dialoge，引导开启；
                        if (mCallback != null) {
                            PermissionUtils.showPermissionRationale(this, mCallback);
                        }
                    }
                }
                break;

             default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private  boolean shouldShowRequestPermissionsRationale(@NonNull String[] permissions ) {
        for (String s : permissions) {
            if (!shouldShowRequestPermissionRationale(s)) {
                //点击了不再提醒
                return false;
            }
        }
        //点击了拒绝
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PermissionUtils.REQUEST_PERMISSION && mCallback != null) {
            if (mPermissions != null
                    && PermissionUtils.checkPermission(this, mPermissions)) {
                mCallback.onGranted();
            }else{
                mCallback.onDenied();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
