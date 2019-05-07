package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.impl.CommentModelImpl;
import com.example.lemonbily.view.ui.ICommentView;

public class CommentPressenter extends BasePresenter<ICommentView> implements ICommentPressenter{

    public CommentPressenter() {
    }

    public CommentPressenter(ICommentView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new CommentModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void uploadCommentData(String s) {
        //生成commentData 对象，存放如recyclerView进行假显；接着上传到服务器；
    }
}
