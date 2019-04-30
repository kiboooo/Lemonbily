package com.example.basemodule.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basemodule.presenter.BasePresenter;


public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initFragmentView(), container, false);
    }

    /**
     * 执行时机 ：
     *          onCreateView执行完毕之后，在onStart之前
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initFragmentChildView(view);
    }

    /**
     *  执行时机：
     *         在onViewCreated 被执行完后调用，在 onStart 执行之前
     *  进行该Fragment所用到的数据绑定
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    /**
     * 数据绑定操作
     *
     * @param savedInstanceState
     */
    protected abstract void initFragmentData(Bundle savedInstanceState);

    /**
     * 执行控件绑定 findViewById
     *
     * @param view
     */
    protected abstract void initFragmentChildView(View view);

    /**
     * 获取Fragment对应的布局
     *
     * @return
     */
    protected abstract int initFragmentView();

    /**
     * 初始化Precenter
     *
     * @return
     */
    protected abstract T initPresenter();

    protected void showToasts(String msg,int state){
        Toast.makeText(getContext(), msg, state).show();
    }


}
