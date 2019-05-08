package com.example.commentmodule.presenter;

import com.example.commentmodule.model.adapter.CommentContentAdapter;

public interface ICommentPressenter {
    void uploadCommentData(String s, int pid);

    void updateLikeNumber(int pid, int upORdown);

    CommentContentAdapter produceCommentAdapter(int position);

    void commentPublishSuccess();

    void commentPublishFail();
}
