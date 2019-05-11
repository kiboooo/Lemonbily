package com.example.basemodule.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;

@InvokingEventsDefine()
public class PalSquareEvents {

    @EventType(JsonResponse.class)
    public static final String LOAD_PAL_DATA = "load_pal_data";

    @EventType(JsonResponse.class)
    public static final String LOAD_PAL_SQUARE_DATA = "load_pal_square_data";

    @EventType(JsonResponse.class)
    public static final String INSERT_PAL_DATA = "insert_pal_data";

    @EventType(String.class)
    public static final String PAL_ERROR = "pal_error";

    @EventType(JsonResponse.class)
    public static final String DO_LIKE_OPERATION = "do_like_operation";

    @EventType(JsonResponse.class)
    public static final String DO_UNLIKE_OPERATION = "do_unlike_operation";

    @EventType(JsonResponse.class)
    public static final String DO_ATTENTION_OPERATION = "do_attention_operation";

    @EventType(JsonResponse.class)
    public static final String DO_UNATTENTION_OPERATION = "do_unattention_operation";

    @EventType(String.class)
    public static final String PUBLISH_PAL_FINISH = "publish_pal_finish";

}
