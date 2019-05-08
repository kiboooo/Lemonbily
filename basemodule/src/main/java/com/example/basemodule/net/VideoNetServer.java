package com.example.basemodule.net;

import android.util.Log;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Video;
import com.example.basemodule.bus.generated.im.EventsDefineAsVideoEvents;
import com.example.basemodule.net.path.VideoServer;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoNetServer {
    private static final String TAG = "VideoNetServer";
    private VideoServer mVideoServer;
    private EventsDefineAsVideoEvents videoEvents;

    private VideoNetServer() {
        mVideoServer = NetWorkServer.getInstance().getRetrofit().create(VideoServer.class);
        videoEvents = InvokingMessage.get().as(EventsDefineAsVideoEvents.class);
    }

    private static class InstanceHomeNetServer{
        private static final VideoNetServer instance = new VideoNetServer();
    }

    public static VideoNetServer getInstance(){
        return InstanceHomeNetServer.instance;
    }

    //获取所有视频数据
    public void loadAllVideoData() {
        callVideoBack(mVideoServer.getAllVideoData(), videoEvents.LOAD_ALL_VIDEO_DATA());
    }

    private void callVideoBack(Call<JsonResponse<List<Video>>> mCall,
                               final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<List<Video>>>() {
            @Override
            public void onResponse(Call<JsonResponse<List<Video>>> call,
                                   Response<JsonResponse<List<Video>>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<List<Video>>> call, Throwable t) {
                videoEvents.VIDEO_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private boolean isFailOnResponse(Response response) {
        boolean result = true;
        if (!response.isSuccessful()) {
            videoEvents.VIDEO_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            videoEvents.VIDEO_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }
}
