package com.example.lemonbily.bus;

import com.jeremyliao.im.base.annotation.EventType;
import com.jeremyliao.im.base.annotation.InvokingEventsDefine;

@InvokingEventsDefine()
public class AppEvens {
    @EventType(String.class)
    public static final String MINE_UI_DATA_UPDATE = "mine_ui_data_update";
}
