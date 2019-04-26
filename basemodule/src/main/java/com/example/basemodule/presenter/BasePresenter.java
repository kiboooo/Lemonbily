package com.example.basemodule.presenter;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;

import java.lang.ref.SoftReference;

public abstract class BasePresenter<T> implements IBasePresenter{

    //View接口类型的软引用
    private SoftReference<T> mViewRef;
    private IBaseModel baseModel;

    public BasePresenter() {
    }

    public BasePresenter(T v, LifecycleOwner owner) {
        mViewRef = new SoftReference<T>(v);
        baseModel = initModel();
        baseModel.initErrorObservers(owner);
        baseModel.initObservers(owner);
    }

    protected IBaseModel getBaseModel() {
        return  baseModel;
    }

    protected abstract IBaseModel initModel();

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
        }
    }
}
