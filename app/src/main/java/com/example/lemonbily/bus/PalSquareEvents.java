package com.example.lemonbily.bus;

import com.example.basemodule.bean.JsonResponse;
import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;

@InvokingEventsDefine()
public class PalSquareEvents {

    @EventType(JsonResponse.class)
    public static final String LOAD_PAL_DATA = "load_pal_data";

    @EventType(String.class)
    public static final String LOAD_PAL_DATA_ERROR = "load_pal_data_error";

}
