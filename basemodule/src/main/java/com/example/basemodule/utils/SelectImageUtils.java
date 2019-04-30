package com.example.basemodule.utils;

import android.app.Activity;
import android.content.Intent;

public class SelectImageUtils {

    public static final int AVATAR_REQUEST_CODE = 111;

    public static void selectImageForAndroid(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        // 设置文件类型
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
