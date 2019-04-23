package com.example.loginmodule.model;

import android.arch.lifecycle.LifecycleOwner;

public interface ILoginModel {

    public void AccountLogin(String phone, String passWord);

    public void initLoginObservers(LifecycleOwner owner );

}
