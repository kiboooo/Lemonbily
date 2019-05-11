package com.example.publishmodule.presenter;

public interface IPushPresenter {

    void pushPalDataToServer(String content, int uid);

    void pushCommentSuccess();

    void pushCommentFail();
}
