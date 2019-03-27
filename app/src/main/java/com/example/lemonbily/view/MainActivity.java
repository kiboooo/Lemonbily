package com.example.lemonbily.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.basemodule.baseActivity;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.MainPresenter;
import com.example.lemonbily.view.ui.IMainView;

public class MainActivity extends baseActivity implements IMainView,View.OnClickListener {

    private MainPresenter presenter;
    private Fragment[] fragments = new Fragment[3];
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private PalCircleFragment palCircleFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Button btnHome;
    private Button btnMine;
    private Button btnPalCircle;


    @Override
    public void initView() {
        setContentView(R.layout.activity_login_main);
        btnHome = findViewById(R.id.btn_home);
        btnMine = findViewById(R.id.btn_mine);
        btnPalCircle = findViewById(R.id.btn_circle);
    }

    @Override
    public void initPresenter() {
        presenter = MainPresenter.getInstance().init(this);
        initFragment();
    }

    @Override
    public void initListener() {
        btnHome.setOnClickListener(this);
        btnMine.setOnClickListener(this);
        btnPalCircle.setOnClickListener(this);
    }

    private void initFragment(){
        palCircleFragment = new PalCircleFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        fragmentManager = getSupportFragmentManager();
        fragments = new Fragment[]{homeFragment, mineFragment, palCircleFragment};
        selectTab(0);
    }


    private void selectTab(int i) {
        transaction = fragmentManager.beginTransaction();
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
    public void goToLogin(View view) {
        presenter.ToActivity(MainActivity.this,
                "com.example.loginmodule.view.ui.LoginActivity");
    }

    @Override
    public void goToVideo(View view) {

        presenter.ToActivity(MainActivity.this,
                "com.example.videoplaymodule.VideoPlayActivity");
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
