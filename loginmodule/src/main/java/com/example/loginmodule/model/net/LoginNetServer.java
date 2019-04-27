package com.example.loginmodule.model.net;

import android.util.Log;

import com.example.basemodule.bean.Account;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Login;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.bus.generated.im.EventsDefineAsLoginEvents;
import com.jeremyliao.im.core.InvokingMessage;
import com.jeremyliao.liveeventbus.LiveEventBus;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginNetServer {

    private static final String TAG = "LoginNetServer";
    private LoginServer mLoginServer;
    private EventsDefineAsLoginEvents eventBus;

    //注册
    public void registered(Login login) {
        callLoginBack(mLoginServer.registered(login), eventBus.REGISTER_EVENT());
    }

    //注册个人信息
    public void registeredAccount(Account account) {
        callAccountBack(mLoginServer.registeredAccount(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, account), eventBus.REGISTER_ACCOUNT_EVENT());
    }

    //登录
    public void login(String phone, String passWord) {
        callLoginBack(mLoginServer.login(phone, passWord), eventBus.LOGIN_EVENT());
    }

    //登出
    public void logout(String phone) {
        callLoginBack(mLoginServer.logout(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token,phone), eventBus.LOGOUT_EVENT());
    }

    //更改密码
    public void changePassWord(int id,String phone,String oldPassWord,String newPassWord) {
        callLoginBack(mLoginServer.changePassWord(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token,id, phone, oldPassWord, newPassWord),
                eventBus.CHANGE_PASSWORD_EVENT());
    }

    //更新Account用户
    public void modifyAccount(Account account) {
        callAccountBack(mLoginServer.modifyAccount(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token,account),
                eventBus.MODIFY_ACCOUNT_EVENT());
    }

    //更新Account用户的头像
    public void modifydAccountAvatar( int aid,MultipartBody.Part imag) {
        callAccountBack(mLoginServer.modifydAccountAvatar(LoginStatusUtils.mLogin.getLphone(),
                LoginStatusUtils.token, aid, imag),
                eventBus.MODIFY_ACCOUNT_AVATAR_EVENT());
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
            eventBus.LOGIN_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message());
            result = false;
        }
        Object jr = response.body();
        if (null == jr) {
            eventBus.LOGIN_REQUEST_ERROR().post("code :" + response.code()
                    + " msg: " + response.message() + " body is null ;");
            result = false;
        }
        return result;
    }

    private void callLoginBack(Call<JsonResponse<Login>> mCall,
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

    private void callAccountBack(Call<JsonResponse<Account>> mCall,
                          final LiveEventBus.Observable<JsonResponse> observable) {
        mCall.enqueue(new Callback<JsonResponse<Account>>() {
            @Override
            public void onResponse(Call<JsonResponse<Account>> call,
                                   Response<JsonResponse<Account>> response) {
                if(isFailOnResponse(response)){
                    observable.post(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Account>> call, Throwable t) {
                eventBus.LOGIN_REQUEST_ERROR().post(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
