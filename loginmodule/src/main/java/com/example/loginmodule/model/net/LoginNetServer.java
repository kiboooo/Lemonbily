package com.example.loginmodule.model.net;

import android.util.Log;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.net.NetWorkServer;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.example.loginmodule.model.LoginServer;
import com.example.basemodule.bean.Login;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginNetServer {

    public static final String TAG = "LoginNetServer";
    private LoginServer mLoginServer;
    private EventsDefineAsLoginEvents eventBus;

    //注册
    public void registered(Login login) {
        callBack(mLoginServer.registered(login), eventBus.REGISTER_EVENT());
    }

    //登录
    public void login(String phone, String passWord) {
        callBack(mLoginServer.login(phone, passWord), eventBus.LOGIN_EVENT());
    }

    //登出
    public void logout(String phone) {
        callBack(mLoginServer.logout(phone), eventBus.LOGOUT_EVENT());
    }

    //更改密码
    public void changePassWord(int id,String phone,String oldPassWord,String newPassWord) {
        callBack(mLoginServer.changePassWord(id, phone, oldPassWord, newPassWord),
                eventBus.CHANGE_PASSWORD_EVENT());
    }



    private LoginNetServer() {
        mLoginServer = NetWorkServer.getInstance().getRetrofit().create(LoginServer.class);
        eventBus = InvokingMessage.get().as(EventsDefineAsLoginEvents.class);
    }

    private static class InstanceLoginNetServer{
        private static final LoginNetServer instance = new LoginNetServer();
    }

    public static LoginNetServer getInstance(){
        return InstanceLoginNetServer.instance;
    }

    private boolean isFailOnResponse(Response response) {
        boolean result = true;
        if (!response.isSuccessful()) {
            eventBus.LOGIN_REQUEST_ERROR().post(response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            eventBus.LOGIN_REQUEST_ERROR().post(response.message());
            result = false;
        }
        return result;
    }

    private void callBack(Call<JsonResponse<Login>> mCall,
                          final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Login>>() {
            @Override
            public void onResponse(Call<JsonResponse<Login>> call,
                                   Response<JsonResponse<Login>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Login>> call, Throwable t) {
                eventBus.LOGIN_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
