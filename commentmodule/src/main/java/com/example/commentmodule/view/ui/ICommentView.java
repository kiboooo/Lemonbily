package com.example.commentmodule.view.ui;

public interface ICommentView {
    void showToast(String msg, int state);

    void commentPublishSuccess();

    void commentPublishFail();
}
