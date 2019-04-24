package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.LoginPresenter;
import com.example.loginmodule.view.ILoginView;

@Route(path = "/LoginModule/LoginActivity")
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter>
        implements View.OnClickListener,ILoginView {

    EditText phone;
    EditText password;
    Button login;
    Button register;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_login);
        phone = findViewById(R.id.account_phone);
        password = findViewById(R.id.account_password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
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
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.login) {
            String mPhone = phone.getText().toString();
            String mPass = password.getText().toString();
            if (mPhone.equals(getResources().getString(R.string.login_phone_hint))||
                    !CommonUtils.isTextEmpty(mPhone) || !CommonUtils.isMobile(mPhone)) {
                phone.setError(getResources().getString(R.string.login_phone_error));
                return;
            }

            if (!CommonUtils.isTextEmpty(mPass)
                    || mPass.equals(getResources().getString(R.string.login_password_hint))) {
                password.setError(getResources().getString(R.string.login_password_error));
                return;
            }
            mPass = CommonUtils.passwordEncode(mPass);
            mPresenter.login(mPhone, mPass);

        } else if (i == R.id.register) {
            showToast("注册", Toast.LENGTH_LONG);

        }
    }

    @Override
    public void showToast(String msg, int state) {
        Toast.makeText(this, msg, state).show();
    }

    @Override
    public void loginSuccess() {
        hideLoding();
        toHomeView();
        showToast("登录成功 token :" + LoginStatusUtils.token, Toast.LENGTH_SHORT);
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
