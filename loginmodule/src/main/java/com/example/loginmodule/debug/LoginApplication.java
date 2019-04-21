package com.example.loginmodule.debug;

import android.app.Application;
import android.util.Log;

import com.example.basemodule.application.BaseApplication;

public class LoginApplication extends BaseApplication {

    @Override
    public void initApplication(Application application) {
        Log.e("LoginApplication", "LoginApplication");
    }
}
