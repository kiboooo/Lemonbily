package com.example.videoplaymodule.debug;

import android.app.Application;

/**
 * 当videoPlay作为单独应用所需要的
 */
public class VideoPlayApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 需要的第三方数据，以及一些特殊资源的初始化
     */
    private void init(){

    }
}
