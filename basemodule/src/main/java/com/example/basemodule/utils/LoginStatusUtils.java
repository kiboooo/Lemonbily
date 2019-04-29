package com.example.basemodule.utils;


import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.Login;

public class LoginStatusUtils {
    public static boolean isLogin = false;
    public static String token = "";
    public static Login mLogin = null;
    public static Account mAccount = null;

    public static void savaLoginStatus(){
        SPUtils.put("isLogin", isLogin);
        SPUtils.put("token", token);
        SPUtils.saveObject("mLogin", mLogin);
        SPUtils.saveObject("mAccount", mAccount);
    }

    public static void initLoginStatus(){
        isLogin = SPUtils.get("isLogin", false);
        token = SPUtils.get("token", "");
        mLogin = SPUtils.readObject("mLogin",Login.class );
        mAccount = SPUtils.readObject("mAccount",Account.class );
    }


}
