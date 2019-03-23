package application;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //第三方SDK的初始化
        Log.e("LoginApplication", "LoginApplication");
    }
}
