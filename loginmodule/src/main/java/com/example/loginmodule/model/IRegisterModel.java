package com.example.loginmodule.model;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.bean.Login;

public interface IRegisterModel  {

    void initRegisterObservers(LifecycleOwner owner );

    void register(Login login,String name,String gender);
}
