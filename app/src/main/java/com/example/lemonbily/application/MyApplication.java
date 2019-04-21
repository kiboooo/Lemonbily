package com.example.lemonbily.application;

import android.app.Application;

import com.example.basemodule.application.BaseApplication;
import com.example.basemodule.utils.AppConfigUtil;

public class MyApplication extends BaseApplication {

    @Override
    public void initApplication(Application application) {
        initModuleApp();
        init();
    }

    /**
     * 宿主初始化
     *
     */
    private void init() {

    }

    /**
     * 各个组件初始化
     *
     */
    private void initModuleApp() {
        for (String moduleApplication : AppConfigUtil.moduleApplications) {
            try {
                Class c = Class.forName(moduleApplication);
                BaseApplication baseApplication = (BaseApplication) c.newInstance();
                baseApplication.initApplication(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
