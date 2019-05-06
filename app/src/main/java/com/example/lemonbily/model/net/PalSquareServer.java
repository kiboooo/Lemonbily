package com.example.lemonbily.model.net;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Palcircle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PalSquareServer {

    @GET("PalcircleController/selectAll")
    Call<JsonResponse<List<Palcircle>>> getAllPalCircleData (@Header("phone") String loginPhone,
                                                             @Header("token") String token);
}
