package com.example.commentmodule.presenter;

public interface ICommentPressenter {
    void uploadCommentData(String s);

    void updateLikeNumber(int pid, int upORdown);
}
