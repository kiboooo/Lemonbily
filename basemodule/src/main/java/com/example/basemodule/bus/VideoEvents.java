package com.example.basemodule.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;


@InvokingEventsDefine()
public class VideoEvents {

    @EventType(JsonResponse.class)
    public static final String LOAD_ALL_VIDEO_DATA = "load_all_video_data";

    @EventType(String.class)
    public static final String VIDEO_REQUEST_ERROR = "video_request_error";
}
