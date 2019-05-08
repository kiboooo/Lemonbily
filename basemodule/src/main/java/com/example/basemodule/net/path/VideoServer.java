package com.example.basemodule.net.path;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoServer {

    @GET("VideoController/selectAll")
    Call<JsonResponse<List<Video>>> getAllVideoData ();
}
