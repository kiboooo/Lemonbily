package com.example.basemodule.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    private Gson mGson;
    @Override
    public void init(Context context) {
        mGson = new Gson();
    }
    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return mGson.fromJson(text, clazz);
    }
    @Override
    public String object2Json(Object instance) {
        return mGson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return  mGson.fromJson(input, clazz);
    }
}
