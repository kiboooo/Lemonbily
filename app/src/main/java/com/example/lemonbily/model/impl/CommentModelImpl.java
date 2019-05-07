package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.BaseModel;
import com.example.lemonbily.model.ICommentModel;
import com.example.lemonbily.presenter.impl.CommentPressenter;

public class CommentModelImpl extends BaseModel<CommentPressenter> implements ICommentModel {
    public CommentModelImpl() {
    }

    public CommentModelImpl(CommentPressenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

    }
}
