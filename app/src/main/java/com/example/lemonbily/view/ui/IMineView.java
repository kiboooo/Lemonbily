package com.example.lemonbily.view.ui;

public interface IMineView {
    void showToast(String msg, int state);

    void accountInitSuccess();

    void accountInitFail();

    void updateView();
}
