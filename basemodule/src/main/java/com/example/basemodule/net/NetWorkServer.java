package com.example.basemodule.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求库： Retrofit2.0
 * 数据总线： InvokingMessage 基于 liveDatabus
 */
public class NetWorkServer {

    /**
     * 服务器地址前缀URL
     */
    private static final String BASE_URL = "http://47.107.46.0:80/lemonbily/";

    private static final String DEBUG_BASE_URL = "http://localhost:80/lemonbily/";
    private static final int READ_TIMEOUT  = 60;        //读取超时时间，单位秒
    private static final int CONNECT_TIMEOUT  = 50;     //链接超时时间，单位秒

    private Retrofit mRetrofit;

    private static class SingleNetWorkServer{
        private static final NetWorkServer instance = new NetWorkServer();
    }

    private NetWorkServer() {
    }

    public void  initRetrofit() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        //TODO : 注意上线时，把URL的地址更正
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create()) //添加Gson解析工厂，返回对应的对象。
                .build();
    }

    public static NetWorkServer getInstance(){
        return SingleNetWorkServer.instance;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

}
