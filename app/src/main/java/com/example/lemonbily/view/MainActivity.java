package com.example.lemonbily.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.lemonbily.R;
import com.example.lemonbily.presenter.impl.MainPresenter;
import com.example.lemonbily.view.ui.IMainView;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.videoplaymodule.model.net.VideoNetServer;
import com.jeremyliao.im.core.InvokingMessage;

@Route(path = "/Lemonbily/MainActivity")
public class MainActivity extends BaseActivity<IMainView,MainPresenter>
        implements IMainView,View.OnClickListener {

    private Fragment[] fragments = new Fragment[3];
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private PalCircleFragment palCircleFragment;

    private FragmentManager fragmentManager;

    private ImageView btnHome;
    private ImageView btnMine;
    private ImageView btnPalCircle;


    @Override
    protected void initBinding() {
        registerUserLifeEvenObserver(this);
        VideoNetServer.getInstance().loadAllVideoData();
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
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.btn_home);
        btnMine = findViewById(R.id.btn_mine);
        btnPalCircle = findViewById(R.id.btn_circle);
    }

    @Override
    protected void initSP() {
        LoginStatusUtils.initLoginStatus();
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
        switch (i) {
            case 0:
                btnHome.setSelected(true);
                btnPalCircle.setSelected(false);
                btnMine.setSelected(false);
                break;
            case 1:
                btnHome.setSelected(false);
                btnPalCircle.setSelected(true);
                btnMine.setSelected(false);
                break;
            case 2:
                btnHome.setSelected(false);
                btnPalCircle.setSelected(false);
                btnMine.setSelected(true);
                break;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginStatusUtils.savaLoginStatus();
    }

    private void registerUserLifeEvenObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                .USER_INACTIVATION()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        LoginStatusUtils.clearAll();
                        ARouter.getInstance()
                                .build("/LoginModule/LoginActivity")
                                .withBoolean("exPush", false)
                                .navigation();
                    }
                });
    }


}
