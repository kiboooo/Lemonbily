package com.example.lemonbily.presenter.impl;

import android.content.Context;

import com.example.lemonbily.model.adapter.HomeAdapter;

public interface IHomePressenter {

    HomeAdapter getHomeAdapter(Context context);

    void showToast(String msg, int state);
}