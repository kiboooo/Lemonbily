package com.example.lemonbily.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseFragment;
import com.example.lemonbily.R;
import com.example.lemonbily.model.adapter.PalPagerAdapter;
import com.example.lemonbily.model.net.PalSquareNetServer;
import com.example.lemonbily.presenter.impl.PalCirclePresenter;
import com.example.lemonbily.view.ui.IPalCircleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Route(path = "/Lemonbily/PalCircleFragment")
public class PalCircleFragment extends BaseFragment<IPalCircleView, PalCirclePresenter>
        implements IPalCircleView {

    private TabLayout tableLayout;
    private ViewPager viewPager;

    private PalCircleSquareFragment squareFragment;
    private PalCircleMSGFragment msgFragment;

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        if (LoginStatusUtils.isLogin) {
            Objects.requireNonNull(tableLayout.getTabAt(0)).select();
            PalSquareNetServer.getInstance().loadPalSquareAllData(LoginStatusUtils.mLogin.getId());
        }
    }

    @Override
    protected void initFragmentChildView(final View view) {
        if (LoginStatusUtils.isLogin) {
            tableLayout = view.findViewById(R.id.pal_table_layout);
            viewPager = view.findViewById(R.id.pal_view_pager);
            viewPager.setAdapter(new PalPagerAdapter(getFragmentManager(), initChildFragmentList()));
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
            tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } else {
            TextView loginButton = view.findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ARouter.getInstance()
                            .build("/LoginModule/LoginActivity")
                            .withBoolean("exPush", true)
                            .navigation();
                }
            });
        }

    }

    private List<Fragment> initChildFragmentList() {
        List<Fragment> list = new ArrayList<>();
        squareFragment = (PalCircleSquareFragment) ARouter.getInstance()
                .build("/Lemonbily/PalCircleSquareFragment").navigation();
        list.add(squareFragment);
        msgFragment = (PalCircleMSGFragment) ARouter.getInstance()
                .build("/Lemonbily/PalCircleMSGFragment").navigation();
        list.add(msgFragment);
        return list;
    }

    @Override
    protected int initFragmentView() {
        if (LoginStatusUtils.isLogin)
            return R.layout.fragment_pal_circle;
        else
            return R.layout.fragment_mine_not_login;

    }

    @Override
    protected PalCirclePresenter initPresenter() {
        return new PalCirclePresenter(this, this);
    }
}
