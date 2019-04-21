package com.example.basemodule.application;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //第三方SDK的初始化
        Log.e("LoginApplication", "LoginApplication");
        initSDK();
        init();
    }

    /**
     * 初始化第三方SDK
     *
     */
    private void initSDK() {
        //初始化ARouter
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    /**
     * 初始化其他功能
     */
    private void init() {

    }

    public Application getApplication(){
        return this;
    }

    public boolean isDebug(){
        try {
            ApplicationInfo info = this.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}
