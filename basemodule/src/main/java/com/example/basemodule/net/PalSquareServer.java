package com.example.basemodule.net;

import com.example.basemodule.bean.Buddy;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Like;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.bean.Palcircle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PalSquareServer {

    @GET("PalcircleController/selectAll")
    Call<JsonResponse<List<Palcircle>>> getAllPalCircleData (@Header("phone") String loginPhone,
                                                             @Header("token") String token);

    @POST("PalcircleController/initPalSquareData")
    @FormUrlEncoded
    Call<JsonResponse<List<PalSquareBean>>> getPalSquareData(@Header("phone") String loginPhone,
                                                             @Header("token") String token, @Field("uid") int uid);

    @POST("LikeController/insert")
    Call<JsonResponse<Like>> likeOperating (@Header("phone") String loginPhone,
                                              @Header("token") String token,  @Body Like like);

    @POST("LikeController/deleteByUserIDAndPalID")
    @FormUrlEncoded
    Call<JsonResponse<Like>> unlikeOperating (@Header("phone") String loginPhone,
                                            @Header("token") String token,
                                              @Field("userID") int userID,
                                              @Field("palID") int palID);

    @POST("BuddyController/insert")
    Call<JsonResponse<Buddy>> attentionOperating (@Header("phone") String loginPhone,
                                            @Header("token") String token,  @Body Buddy buddy);

    @POST("BuddyController/deleteByUIDandBuddlyID")
    @FormUrlEncoded
    Call<JsonResponse<Buddy>> unattentionOperating(@Header("phone") String loginPhone,
                                                   @Header("token") String token,
                                                   @Field("userID") int userID,
                                                   @Field("buddyID") int buddyID);

}
