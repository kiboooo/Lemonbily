package com.example.videoplaymodule.view.ui;

public interface IVedioDetailedPageView {

    void showToat(String msg, int state);

    void initCurrentCommentDataSuccess();

    void initCurrentCommentDataFail();

    void commentPublishSuccess();

    void commentPublishFail();

    void scrollToPosition(int position);
}
