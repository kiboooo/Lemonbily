package com.example.basemodule.net.path;

import com.example.basemodule.bean.Collect;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Video;
import com.example.basemodule.bean.VideoDetailUIBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface VideoDetailServer {

    @POST("VideoDetailController/initVideoDetailData")
    @FormUrlEncoded
    Call<JsonResponse<VideoDetailUIBean>> initVideoDetailData (@Header("phone") String loginPhone,
                                                           @Header("token") String token,
                                                           @Field("uid") int uid,
                                                           @Field("vid") int vid);
    @POST("CollectController/insert")
    Call<JsonResponse<Collect>> collectOperating (@Header("phone") String loginPhone,
                                            @Header("token") String token, @Body Collect collect);

    @POST("CollectController/deleteByColIDAndVID")
    @FormUrlEncoded
    Call<JsonResponse<Collect>> uncollectOperating (@Header("phone") String loginPhone,
                                                               @Header("token") String token,
                                                               @Field("colID") int colID,
                                                               @Field("vid") int vid);

    @POST("VideoDetailController/getCollectVideoData")
    @FormUrlEncoded
    Call<JsonResponse<List<Video>>> loadCollectVideoData(@Header("phone") String loginPhone,
                                                        @Header("token") String token,
                                                        @Field("uid") int uid);
}
