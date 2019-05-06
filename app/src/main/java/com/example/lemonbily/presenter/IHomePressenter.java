package com.example.lemonbily.presenter;

import android.content.Context;

import com.example.lemonbily.model.adapter.HomeAdapter;

public interface IHomePressenter {

    HomeAdapter getHomeAdapter(Context context);

    void adapterNotifyDataSetChanged();

    void showToast(String msg, int state);

    void initHomeDataSuccess();

    void initHomeDataFail();
}
