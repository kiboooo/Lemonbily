package com.example.basemodule.net.path;

import com.example.basemodule.bean.Comment;
import com.example.basemodule.bean.JsonResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CommentServer {

    @POST("CommentController/insert")
    Call<JsonResponse<Comment>> insertComment (@Header("phone") String loginPhone,
                                               @Header("token") String token,
                                               @Body Comment comment);
}
