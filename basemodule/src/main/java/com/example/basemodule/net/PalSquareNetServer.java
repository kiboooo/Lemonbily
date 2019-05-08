package com.example.basemodule.net;

import android.util.Log;

import com.example.basemodule.bean.Buddy;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Like;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.bean.Palcircle;
import com.example.basemodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.basemodule.net.path.PalSquareServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalSquareNetServer {
    private static final String TAG = "PalSquareNetServer";
    private PalSquareServer mPalSquareServer;
    private EventsDefineAsPalSquareEvents palSquareEvents;

    public PalSquareNetServer() {
        mPalSquareServer = NetWorkServer.getInstance().getRetrofit().create(PalSquareServer.class);
        palSquareEvents = InvokingMessage.get().as(EventsDefineAsPalSquareEvents.class);
    }

    private static class InstancePalSquareNetServer{
        private static final PalSquareNetServer instance = new PalSquareNetServer();
    }

    public static PalSquareNetServer getInstance(){
        return PalSquareNetServer.InstancePalSquareNetServer.instance;
    }

    public void loadPalSquareAllData(int uid){
        if (LoginStatusUtils.isLogin) {
            callPalSquareBack(mPalSquareServer.getPalSquareData(LoginStatusUtils.mLogin.getLphone(),
                    LoginStatusUtils.token, uid), palSquareEvents.LOAD_PAL_SQUARE_DATA());
        }else {
            //未登录，拉起登录界面
            InvokingMessage.get().as(EventsDefineAsLoginEvents.class).USER_INACTIVATION().post(null);
        }
    }

    public void doLikeOperating(Like like) {
        callLikeBack(mPalSquareServer.likeOperating(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, like), palSquareEvents.DO_LIKE_OPERATION());
    }

    public void doUnLikeOperating(int userID, int palIDe) {
        callLikeBack(mPalSquareServer.unlikeOperating(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, userID, palIDe), palSquareEvents.DO_UNLIKE_OPERATION());
    }

    public void doAttentionOperating(Buddy buddy) {
        callAttentionBack(mPalSquareServer.attentionOperating(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, buddy), palSquareEvents.DO_ATTENTION_OPERATION());
    }

    public void doUnAttentionOperating(int userID, int buddyID) {
        callAttentionBack(mPalSquareServer.unattentionOperating(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token,userID, buddyID), palSquareEvents.DO_UNATTENTION_OPERATION());
    }


    public void loadPalCircleAllData(){
        if (LoginStatusUtils.isLogin) {
            callPalCircleBack(mPalSquareServer.getAllPalCircleData(LoginStatusUtils.mLogin.getLphone(),
                    LoginStatusUtils.token), palSquareEvents.LOAD_PAL_DATA());
        }else {
            //未登录，拉起登录界面
            InvokingMessage.get().as(EventsDefineAsLoginEvents.class).USER_INACTIVATION().post(null);
        }
    }


    private boolean isFailOnResponse(Response response) {
        boolean result = true;
        if (!response.isSuccessful()) {
            palSquareEvents.PAL_ERROR().post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            palSquareEvents.PAL_ERROR().post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }

    private void callPalCircleBack(Call<JsonResponse<List<Palcircle>>> mCall,
                                   final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<List<Palcircle>>>() {
            @Override
            public void onResponse(Call<JsonResponse<List<Palcircle>>> call,
                                   Response<JsonResponse<List<Palcircle>>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<List<Palcircle>>> call, Throwable t) {
                palSquareEvents.PAL_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void callPalSquareBack(Call<JsonResponse<List<PalSquareBean>>> mCall,
                                   final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<List<PalSquareBean>>>() {
            @Override
            public void onResponse(Call<JsonResponse<List<PalSquareBean>>> call,
                                   Response<JsonResponse<List<PalSquareBean>>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<List<PalSquareBean>>> call, Throwable t) {
                palSquareEvents.PAL_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }


    private void callLikeBack(Call<JsonResponse<Like>> mCall,
                                   final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Like>>() {
            @Override
            public void onResponse(Call<JsonResponse<Like>> call,
                                   Response<JsonResponse<Like>> response) {
                if (isFailOnResponse(response)) {
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Like>> call, Throwable t) {
                palSquareEvents.PAL_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void callAttentionBack(Call<JsonResponse<Buddy>> mCall,
                              final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Buddy>>() {
            @Override
            public void onResponse(Call<JsonResponse<Buddy>> call,
                                   Response<JsonResponse<Buddy>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Buddy>> call, Throwable t) {
                palSquareEvents.PAL_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
