package com.example.loginmodule.model;

import android.arch.lifecycle.LifecycleOwner;

public interface ILoginModel {

    void AccountLogin(String phone, String passWord);

    void initLoginObservers(LifecycleOwner owner );

}
