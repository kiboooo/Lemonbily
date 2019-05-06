package com.example.lemonbily.model.net;

import android.util.Log;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Palcircle;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.lemonbily.bus.generated.im.EventsDefineAsPalSquareEvents;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
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


    public void loadPalCircleAllData(){
        if (LoginStatusUtils.isLogin) {
            callPalBack(mPalSquareServer.getAllPalCircleData(LoginStatusUtils.mLogin.getLphone(),
                    LoginStatusUtils.token), palSquareEvents.LOAD_PAL_DATA());
        }else {
            //未登录，拉起登录界面
            InvokingMessage.get().as(EventsDefineAsLoginEvents.class).USER_INACTIVATION().post(null);
        }
    }


    private boolean isFailOnResponse(Response response) {
        boolean result = true;
        if (!response.isSuccessful()) {
            palSquareEvents.LOAD_PAL_DATA_ERROR().post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            palSquareEvents.LOAD_PAL_DATA_ERROR().post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }

    private void callPalBack(Call<JsonResponse<List<Palcircle>>> mCall,
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
                palSquareEvents.LOAD_PAL_DATA_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
