package com.example.publishmodule.view.ui;

public interface IPushCommentView {
    void showToast(String msg, int state);

    void pushCommentSuccess();

    void pushCommentFail();
}
