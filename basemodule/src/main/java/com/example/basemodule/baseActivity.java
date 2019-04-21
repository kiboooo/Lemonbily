package com.example.basemodule;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class baseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColorForStatus();
        initView();
        initPresenter();
        initListener();
    }

    public abstract void initView();

    public abstract void initPresenter();

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

}
