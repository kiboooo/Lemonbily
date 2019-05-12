package com.example.basemodule.net;

import android.util.Log;

import com.example.basemodule.bean.Collect;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Video;
import com.example.basemodule.bean.VideoDetailUIBean;
import com.example.basemodule.bus.generated.im.EventsDefineAsVideoEvents;
import com.example.basemodule.net.path.VideoDetailServer;
import com.example.basemodule.net.path.VideoServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoNetServer {
    private static final String TAG = "VideoNetServer";
    private VideoServer mVideoServer;
    private VideoDetailServer mVideoDetailServer;
    private EventsDefineAsVideoEvents videoEvents;

    private VideoNetServer() {
        mVideoServer = NetWorkServer.getInstance().getRetrofit().create(VideoServer.class);
        mVideoDetailServer = NetWorkServer.getInstance().getRetrofit().create(VideoDetailServer.class);
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

    //获取所有视频数据
    public void loadCollectVideoData(int uid) {
        callVideoBack(mVideoDetailServer.loadCollectVideoData(LoginStatusUtils.mLogin.getLphone(), LoginStatusUtils.token, uid),
                videoEvents.LOAD_VIDEO_COLLECT_DATA());
    }

    //获取视频详情页数据：对于视频的评论信息等
    public void loadVideoDetailData(int uid, int vid) {
        callVideoDetailBack(mVideoDetailServer.initVideoDetailData(
                LoginStatusUtils.mLogin.getLphone(),LoginStatusUtils.token,uid,vid),
                videoEvents.LOAD_VIDEO_DETAIL_DATA());
    }

    //收藏视频
    public void doCollectVideo(Collect collect) {
        callVideoCollectBack(mVideoDetailServer.collectOperating(
                LoginStatusUtils.mLogin.getLphone(),LoginStatusUtils.token,collect),
                videoEvents.DO_COLLECT_OPERATION());
    }

    //取消收藏
    public void doUnCollectVideo(int uid, int vid) {
        callVideoCollectBack(mVideoDetailServer.uncollectOperating(
                LoginStatusUtils.mLogin.getLphone(),LoginStatusUtils.token,uid,vid),
                videoEvents.DO_UNCOLLECT_OPERATION());
    }

    private void callVideoBack(Call<JsonResponse<List<Video>>> mCall,
                               final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<List<Video>>>() {
            @Override
            public void onResponse(Call<JsonResponse<List<Video>>> call,
                                   Response<JsonResponse<List<Video>>> response) {
                if(isFailOnResponse(response,videoEvents.VIDEO_REQUEST_ERROR())){
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

    private void callVideoDetailBack(Call<JsonResponse<VideoDetailUIBean>> mCall,
                               final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<VideoDetailUIBean>>() {
            @Override
            public void onResponse(Call<JsonResponse<VideoDetailUIBean>> call,
                                   Response<JsonResponse<VideoDetailUIBean>> response) {
                if(isFailOnResponse(response,videoEvents.VIDEO_DETAIL_REQUEST_ERROR())){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<VideoDetailUIBean>> call, Throwable t) {
                videoEvents.VIDEO_DETAIL_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void callVideoCollectBack(Call<JsonResponse<Collect>> mCall,
                                     final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Collect>>() {
            @Override
            public void onResponse(Call<JsonResponse<Collect>> call,
                                   Response<JsonResponse<Collect>> response) {
                if(isFailOnResponse(response,videoEvents.VIDEO_DETAIL_REQUEST_ERROR())){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Collect>> call, Throwable t) {
                videoEvents.VIDEO_DETAIL_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private boolean isFailOnResponse(Response response ,LiveEventBus.Observable<String> error) {
        boolean result = true;
        if (!response.isSuccessful()) {
            error.post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            error.post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }
}
