package com.example.loginmodule.model;

import com.example.basemodule.bean.JsonResponse;
import com.example.loginmodule.model.bean.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginServer {

    @POST("LoginController/registered")
    Call<JsonResponse<Login>> registered (@Body Login login);

    @POST("LoginController/login")
    @FormUrlEncoded
    Call<JsonResponse<Login>> login (@Field("lphone") String phone,
                                    @Field("lpassword") String password);

    @POST("LoginController/logout")
    @FormUrlEncoded
    Call<JsonResponse<Login>> logout (@Field("lphone") String phone);

    @POST("LoginController/permanentLogout")
    @FormUrlEncoded
    Call<JsonResponse<Login>> permanentLogout (@Field("id") int id);

    @POST("LoginController/changePassWord")
    @FormUrlEncoded
    Call<JsonResponse<Login>> changePassWord (@Field("id") int id,@Field("lphone") String phone,
                                              @Field("oldPassWord") String oldPassWord,
                                              @Field("newPassWord") String newPassWord);

    @GET("LoginController/getLogin")
    @FormUrlEncoded
    Call<JsonResponse<Login>> getLogin(@Field("id") int id);
}
