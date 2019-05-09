package com.example.basemodule.utils;

public class AppConfigUtil {
    /**
     *  每个宿主App运用到的组件都需要记录
     *  以便于宿主在初始化的时候，一并初始化各个组件；
     */
    private static final String LoginModule = "com.example.loginmodule.debug.LoginApplication";
    private static final String VideoplayModule = "com.example.videoplaymodule.debug.VideoPlayApplication";
    private static final String CommentModule = "com.example.commentmodule.debug.CommentApplication";

    public static String[] moduleApplications = {
            LoginModule,VideoplayModule,CommentModule
    };
}
