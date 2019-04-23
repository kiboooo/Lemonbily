package com.example.loginmodule.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;

@InvokingEventsDefine()
public class LoginEvents {

    @EventType(String.class)
    public static final String LOGIN_REQUEST_ERROR = "request_error";

    /**
     * 用户登录事件
     *
     *  消息体的类型为Json数据
     */
    @EventType(JsonResponse.class)
    public static final String LOGIN_EVENT = "account_login_event";

    //用户注册事件
    @EventType(JsonResponse.class)
    public static final String REGISTER_EVENT = "account_register_event";

    //账号登出事件
    @EventType(JsonResponse.class)
    public static final String LOGOUT_EVENT = "account_logout_event";


    //修改密码
    @EventType(JsonResponse.class)
    public static final String CHANGE_PASSWORD_EVENT = "account_change_password_event";

}
