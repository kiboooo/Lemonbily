package com.example.basemodule.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.example.basemodule.R;

public class PermissionUtils {

    public static final int REQUEST_PERMISSION = 111;
    public static final int REQUEST_SAVE_PERMISSION = 222;

    public static String[] SavePermissionGroup = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //检查是否授权了
    public static boolean checkPermission(Context context, String[] s) {
        for (String value : s) {
            if (ContextCompat.checkSelfPermission(context, value) < 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionResult(int[] grantResults) {
        for (int i : grantResults) {
            if (i == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public static void showPermissionRationale(@NonNull final Activity activity,
                                               @NonNull final OnPermisstionCallback callback) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("授权说明");
        dialog.setMessage(activity.getResources()
                .getString(R.string.base_permission_rationale_content));
        dialog.setCancelable(false);
        dialog.setPositiveButton("前往", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gotoSetting(activity);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onDenied();
            }
        });
        dialog.show();
    }

    //打开系统设置界面
    private static void gotoSetting(Activity activity){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("packge", activity.getPackageName(), null));
        activity.startActivityForResult(intent, REQUEST_PERMISSION);
    }

}
