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

}
