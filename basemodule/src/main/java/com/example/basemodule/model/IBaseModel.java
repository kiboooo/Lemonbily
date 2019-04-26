package com.example.basemodule.model;

import android.arch.lifecycle.LifecycleOwner;

public interface IBaseModel {

    void initObservers(LifecycleOwner owner);

    void initErrorObservers(LifecycleOwner owner);

}
