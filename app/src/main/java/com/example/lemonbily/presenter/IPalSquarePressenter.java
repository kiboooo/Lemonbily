package com.example.lemonbily.presenter;

import android.content.Context;

import com.example.lemonbily.model.adapter.PalSquareAdapter;

public interface IPalSquarePressenter {

    void showToast(String msg, int state);

    void initPalDataSuccess();

    void initPalDataFail();

    PalSquareAdapter getPalSquareAdapter(Context context);

    void updateLikeIconState(boolean isSelect);

}
