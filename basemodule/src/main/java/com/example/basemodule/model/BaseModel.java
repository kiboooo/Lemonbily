package com.example.basemodule.model;

import com.example.basemodule.presenter.IBasePresenter;

import java.lang.ref.SoftReference;

public abstract class BaseModel<T extends IBasePresenter>{

    //View接口类型的软引用
    private SoftReference<T> mPresenterRef;

    protected BaseModel() {

    }

    public BaseModel(T presenter) {
        mPresenterRef = new SoftReference<>(presenter);
    }

    protected T getPresenter() {
        return mPresenterRef.get();
    }

    public boolean isViewAttached() {
        return mPresenterRef != null && mPresenterRef.get() != null;
    }

    public void detachView() {
        if (mPresenterRef != null) {
            mPresenterRef.clear();
        }
    }

}
