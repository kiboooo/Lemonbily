package com.example.basemodule.utils;

import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemAdaptionUtils {

    /*小米*/

    /**
     * 检查手机是否是miui
     * @ref http://dev.xiaomi.com/doc/p=254/index.html
     * @return
     */
    public static boolean isMIUI(){
        String device = Build.MANUFACTURER;
        return device.equals("Xiaomi");
    }

    public static String getMiuiVersion(){
        String propName = "ro.miui.ui.version.name";
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    /*小米*/
}
