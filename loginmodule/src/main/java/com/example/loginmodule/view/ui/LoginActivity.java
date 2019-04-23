package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.LoginPresenter;
import com.example.loginmodule.view.ILoginView;

@Route(path = "/LoginModule/LoginActivity")
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter>
        implements View.OnClickListener,ILoginView {

    TextView textView ;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_login);
        textView = findViewById(R.id.login_text_btn);
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this,this);
    }

    @Override
    protected void initBinding() {

    }

    @Override
    public void initListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login("12345678900",
                        "21232f297a57a5a743894a0e4a801fc3");
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showToast(String msg, int state) {
        Toast.makeText(this, msg, state).show();
    }

    @Override
    public void loginSuccess() {
        hideLoding();
        textView.setText("token is : "+LoginStatusUtils.token);
//        toHomeView();
        showToast("登录成功", Toast.LENGTH_SHORT);
    }

    @Override
    public void loginFail() {
        hideLoding();
        showToast("登录失败", Toast.LENGTH_SHORT);
    }

    @Override
    public void getLoginObjSuccess() {

    }

    @Override
    public void getLoginObjFail() {

    }

    @Override
    public void logoutSuccess() {

    }

    @Override
    public void logoutFail() {

    }

    @Override
    public void permanentLogoutSuccess() {

    }

    @Override
    public void permanentLogoutFail() {

    }

    @Override
    public void changePassWordSuccess() {

    }

    @Override
    public void changePassWordFail() {

    }

    private void hideLoding(){

    }

    private void toHomeView(){
        ARouter.getInstance().build("/Lemonbily/MainActivity").navigation();
        finish();
    }


}
