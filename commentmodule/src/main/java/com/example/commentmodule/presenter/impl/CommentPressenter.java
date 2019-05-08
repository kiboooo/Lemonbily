package com.example.commentmodule.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.commentmodule.model.adapter.CommentContentAdapter;
import com.example.commentmodule.model.impl.CommentModelImpl;
import com.example.commentmodule.presenter.ICommentPressenter;
import com.example.commentmodule.view.ui.ICommentView;

public class CommentPressenter extends BasePresenter<ICommentView> implements ICommentPressenter {

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
    public void uploadCommentData(String s, int pid) {
        //生成commentData 对象，存放如recyclerView进行假显；接着上传到服务器；
        ((CommentModelImpl) getBaseModel()).uploadCommentData(s, pid);
    }

    @Override
    public void updateLikeNumber(int pid, int upORdown) {
        ((CommentModelImpl) getBaseModel()).updateLike(pid, upORdown);
    }

    @Override
    public CommentContentAdapter produceCommentAdapter(int position) {
        return ((CommentModelImpl) getBaseModel()).produceCommentAdapter(position);
    }

    @Override
    public void commentPublishSuccess() {
        getView().commentPublishSuccess();
    }

    @Override
    public void commentPublishFail() {
        getView().commentPublishFail();
    }
}
