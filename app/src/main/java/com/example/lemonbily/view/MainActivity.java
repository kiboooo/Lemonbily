package com.example.lemonbily.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.view.BaseActivity;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.MainPresenter;
import com.example.lemonbily.view.ui.IMainView;

@Route(path = "/Lemonbily/MainActivity")
public class MainActivity extends BaseActivity<IMainView,MainPresenter> implements IMainView,View.OnClickListener {

    private Fragment[] fragments = new Fragment[3];
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private PalCircleFragment palCircleFragment;

    private FragmentManager fragmentManager;

    private Button btnHome;
    private Button btnMine;
    private Button btnPalCircle;


    @Override
    protected void initBinding() {
        palCircleFragment =(PalCircleFragment) ARouter.getInstance()
                .build("/Lemonbily/PalCircleFragment")
                .navigation();
        homeFragment = (HomeFragment) ARouter.getInstance()
                .build("/Lemonbily/HomeFragment")
                .navigation();
        mineFragment = (MineFragment) ARouter.getInstance()
                .build("/Lemonbily/MineFragment")
                .navigation();
        fragmentManager = getSupportFragmentManager();
        fragments = new Fragment[]{homeFragment, palCircleFragment, mineFragment};
        selectTab(0);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_main);
        btnHome = findViewById(R.id.btn_home);
        btnMine = findViewById(R.id.btn_mine);
        btnPalCircle = findViewById(R.id.btn_circle);
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter(this,this);
    }

    @Override
    public void initListener() {
        btnHome.setOnClickListener(this);
        btnMine.setOnClickListener(this);
        btnPalCircle.setOnClickListener(this);
    }

    private void selectTab(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if (!fragments[i].isAdded()) {
            transaction.add(R.id.fragment, fragments[i]);
        }
        transaction.show(fragments[i]);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                selectTab(0);
                break;

            case R.id.btn_circle:
                selectTab(1);
                break;

            case R.id.btn_mine:
                selectTab(2);
                break;
        }
    }
}
