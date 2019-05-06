package com.example.lemonbily.presenter.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import com.example.basemodule.model.IBaseModel;
import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.adapter.PalSquareAdapter;
import com.example.lemonbily.model.impl.IPalSquareModelImpl;
import com.example.lemonbily.presenter.IPalSquarePressenter;
import com.example.lemonbily.view.ui.IPalSquareView;

public class PalSquarePresenter extends BasePresenter<IPalSquareView> implements IPalSquarePressenter {

    public PalSquarePresenter() {
    }

    public PalSquarePresenter(IPalSquareView v, LifecycleOwner owner) {
        super(v, owner);
    }

    @Override
    protected IBaseModel initModel() {
        return new IPalSquareModelImpl(this);
    }

    @Override
    public void sendErrorMsg(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void showToast(String msg, int state) {
        getView().showToast(msg, state);
    }

    @Override
    public void initPalDataSuccess() {
        getView().initPalDataSuccess();
    }

    @Override
    public void initPalDataFail() {
        getView().initPalDataFail();
    }

    @Override
    public PalSquareAdapter getPalSquareAdapter(Context context) {
        return ((IPalSquareModelImpl) getBaseModel()).produceAdapter(context);
    }

    @Override
    public void updateLikeIconState(boolean isSelect) {

    }
}
