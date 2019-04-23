package com.example.basemodule.application;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.net.NetWorkServer;

public abstract class BaseApplication extends Application {

    //由每个module中的独自的 Application 初始化的接口；
    public abstract void initApplication(Application application);

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
        initApplication(this);

        //初始化Retrofit
        NetWorkServer.getInstance().initRetrofit();
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
