package com.example.lemonbily.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.basemodule.presenter.BasePresenter;
import com.example.lemonbily.model.IMainModel;
import com.example.lemonbily.model.impl.IMainModelImpl;
import com.example.lemonbily.view.ui.IMainView;

public class MainPresenter extends BasePresenter<IMainView> {

    private IMainModel mainModel;

    private  static class SingletonPresenter {
        private static final MainPresenter presenter = new MainPresenter();
    }

    public static MainPresenter getInstance(){
        return SingletonPresenter.presenter;
    }

    public MainPresenter init(IMainView iMainView) {
        attachView(iMainView);
        getInstance().mainModel = new IMainModelImpl();
        return getInstance();
    }

    //TODO : 把这个跳转逻辑放到liveDataBus中去。
    public void ToActivity(Context context,String packageName){
        try {
            Class aClass = Class.forName(packageName);
            Intent intent = new Intent(context,aClass );
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
