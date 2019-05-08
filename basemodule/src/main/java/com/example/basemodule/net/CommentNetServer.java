package com.example.basemodule.net;

import android.util.Log;

import com.example.basemodule.bean.Comment;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bus.generated.im.EventsDefineAsCommentEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.net.path.CommentServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentNetServer {

    private static final String TAG = "CommentNetServer";
    private CommentServer mCommentServer;
    private EventsDefineAsCommentEvents eventBus;


    private CommentNetServer() {
        mCommentServer = NetWorkServer.getInstance().getRetrofit().create(CommentServer.class);
        eventBus = InvokingMessage.get().as(EventsDefineAsCommentEvents.class);
    }

    private static class InstanceCommentNetServer {
        private static final CommentNetServer instance = new CommentNetServer();
    }

    public static CommentNetServer getInstance(){
        return InstanceCommentNetServer.instance;
    }

    public void insertCommentData(Comment comment) {
        callCommentBack(mCommentServer.insertComment(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, comment), eventBus.COMMENT_INSERT());
    }

    private void callCommentBack(Call<JsonResponse<Comment>> mCall,
                               final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Comment>>() {
            @Override
            public void onResponse(Call<JsonResponse<Comment>> call,
                                   Response<JsonResponse<Comment>> response) {
                if(isFailOnResponse(response)){
                    if (response.body() != null && response.body().getCode() == 1004) {
                        InvokingMessage.get().as(EventsDefineAsLoginEvents.class)
                                .USER_INACTIVATION().post(null);
                    }
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Comment>> call, Throwable t) {
                eventBus.COMMENT_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
    private boolean isFailOnResponse(Response response) {
        boolean result = true;
        if (!response.isSuccessful()) {
            eventBus.COMMENT_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            eventBus.COMMENT_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }
}
