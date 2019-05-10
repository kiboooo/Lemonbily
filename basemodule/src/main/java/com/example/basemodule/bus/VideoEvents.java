package com.example.basemodule.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;


@InvokingEventsDefine()
public class VideoEvents {

    @EventType(JsonResponse.class)
    public static final String LOAD_ALL_VIDEO_DATA = "load_all_video_data";

    @EventType(JsonResponse.class)
    public static final String LOAD_VIDEO_DETAIL_DATA = "load_video_detail_data";

    @EventType(JsonResponse.class)
    public static final String DO_COLLECT_OPERATION = "do_collect_operation";

    @EventType(JsonResponse.class)
    public static final String DO_UNCOLLECT_OPERATION = "do_uncollect_operation";

    @EventType(String.class)
    public static final String VIDEO_REQUEST_ERROR = "video_request_error";

    @EventType(String.class)
    public static final String VIDEO_DETAIL_REQUEST_ERROR = "video_detail_request_error";
}
