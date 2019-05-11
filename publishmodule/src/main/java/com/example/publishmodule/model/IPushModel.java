package com.example.publishmodule.model;

import com.example.basemodule.model.IBaseModel;

public interface IPushModel extends IBaseModel {

    void pushPalData(String content, int uid);
}
