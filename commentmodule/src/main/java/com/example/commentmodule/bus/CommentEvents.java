package com.example.commentmodule.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;

@InvokingEventsDefine()
public class CommentEvents {

    @EventType(String.class)
    public static final String COMMENT_REQUEST_ERROR = "comment_request_error";

    @EventType(JsonResponse.class)
    public static final String COMMENT_INSERT = "comment_insert";
}
