package com.example.loginmodule.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.LoginPresenter;
import com.example.loginmodule.view.ILoginView;

@Route(path = "/LoginModule/LoginActivity")
public class LoginActivity extends BaseActivity<ILoginView,LoginPresenter>
        implements View.OnClickListener,ILoginView {

    ImageView backBtn;
    TextView titleDescription;
    EditText phone;
    EditText password;
    Button login;
    Button register;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_login);
        titleDescription = findViewById(R.id.base_normal_back_title);
        titleDescription.setText("注册与登录");
        backBtn = findViewById(R.id.base_normal_title_back_btn);
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
        login.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                String mPhone = phone.getText().toString();
                String mPass = password.getText().toString();
                if (mPhone.equals(getResources().getString(R.string.login_phone_hint))||
                        CommonUtils.isTextEmpty(mPhone) || !CommonUtils.isMobile(mPhone)) {
                    phone.setError(getResources().getString(R.string.login_phone_error));
                    return;
                }

                if (CommonUtils.isTextEmpty(mPass)
                        || mPass.equals(getResources().getString(R.string.login_password_hint))) {
                    password.setError(getResources().getString(R.string.login_password_error));
                    return;
                }
                showLoading();
                mPass = CommonUtils.passwordEncode(mPass);
                mPresenter.login(mPhone, mPass);
            }
        });
        register.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                ARouter.getInstance()
                        .build("/LoginModule/RegisterActivity")
                        .navigation();
            }
        });
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.base_normal_title_back_btn) {
            finish();
        }
    }

    @Override
    public void showToast(String msg, int state) {
        Toast.makeText(this, msg, state).show();
    }

    @Override
    public void loginSuccess() {
        hideLoading();
        toHomeView(false);
        showToast("登录成功 token :" + LoginStatusUtils.token, Toast.LENGTH_SHORT);
    }

    @Override
    public void loginFail() {
        hideLoading();
    }

    @Override
    public void doHideLoading() {
        hideLoading();
    }


    private void toHomeView(boolean isExPull){
        if (isExPull) {
            //判断是否是外部拉起
            ARouter.getInstance().build("/Lemonbily/MainActivity")
                    .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .navigation();
        }
        finish();
    }


}
