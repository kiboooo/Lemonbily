package com.example.basemodule.utils;

import android.os.SystemClock;
import android.view.View;

public abstract class OnMutiClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME  = 1000; //点击间隔设置为1s
    private static long lastClickTime;

    public abstract void onMutiClick(View view);

    @Override
    public void onClick(View view) {
        long curClickTime = SystemClock.elapsedRealtime();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onMutiClick(view);
        }
    }
}
