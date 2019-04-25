package com.example.basemodule.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.basemodule.R;

import java.util.Objects;

public class LoadingDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除Title
        Objects.requireNonNull(getDialog().getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //设置背景为透明

        View mView = inflater.inflate(R.layout.base_normal_loading, container);
        return mView;
    }

    public void showLoading(FragmentManager fragmentManager) {
        this.setCancelable(false);
        this.show(fragmentManager, "loading");
    }

}
