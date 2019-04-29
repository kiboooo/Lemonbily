package com.example.loginmodule.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basemodule.bean.Login;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.impl.RegisterPresenter;
import com.example.loginmodule.view.IRegisterView;


@Route(path = "/LoginModule/RegisterActivity")
public class RegisterActivity extends BaseActivity<IRegisterView, RegisterPresenter>
        implements View.OnClickListener, IRegisterView {

    EditText mPhone;
    EditText mAccountName;
    EditText mPassword;
    RadioGroup radioGroup;
    Button mContinue;
    ImageView backBtn;
    TextView titleDescription;

    String inAccountName;
    String accountGender = "m"; //默认为男性: m ;而女性为: w

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_register);
        mPhone = findViewById(R.id.register_phone);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        mAccountName = findViewById(R.id.register_account_name);
        mPassword = findViewById(R.id.register_password);
        mContinue = findViewById(R.id.register_continue);
        radioGroup = findViewById(R.id.register_gender);
        titleDescription = findViewById(R.id.base_normal_back_title);
    }

    @Override
    protected void initSP() {

    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(this, this);
    }

    @Override
    protected void initBinding() {
        titleDescription.setText("注册");
    }

    @Override
    public void initListener() {
        backBtn .setOnClickListener(this);
        mContinue.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
               Login registerData =  checkInputeContent();
               if (registerData != null)
                   toRegister(registerData,inAccountName);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.gender_man) {
                    accountGender = "m";
                }else if (i == R.id.gender_female) {
                    accountGender = "w";
                }
            }
        });
    }

    private Login checkInputeContent() {
        String inPhone = mPhone.getText().toString();
        String inPassword = mPassword.getText().toString();
        inAccountName = mAccountName.getText().toString();
        if (isPhoneError(inPhone)){
            mPhone.setError(getResources().getString(R.string.login_phone_error));
            return null;
        }

        if (isPassWordError(inPassword)) {
            mPassword.setError(getResources().getString(R.string.login_register_password_content));
            return null;
        }

        if (isAccountNameError(inAccountName)) {
            mAccountName.setError("请输入用户名");
            return null;
        }
        return new Login(null, inPassword, inPhone, null);
    }

    private boolean isAccountNameError(String inName) {
        return inName.equals(getResources().getString(R.string.login_phone_hint))
                || CommonUtils.isTextEmpty(inName);
    }

    private boolean isPassWordError(String inPassword) {
        return inPassword.equals(getResources().getString(R.string.login_register_password_content))
                || CommonUtils.isTextEmpty(inPassword) || inPassword.length() < 6;
    }

    private boolean isPhoneError(String inPhone) {
        return !CommonUtils.isMobile(inPhone);
    }

    private void toRegister(Login login ,String accountName) {
        //执行注册的网络请求
        showLoading();
        mPresenter.accountRegister(login,accountName,accountGender);
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
        showToasts(msg, state);
    }

    @Override
    public void registerSuccess() {
        this.hideLoading();
        showToast("登录成功", Toast.LENGTH_SHORT);
        ARouter.getInstance().build("/Lemonbily/MainActivity")
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .navigation();
        finish();
    }

    @Override
    public void registerFail() {
        this.hideLoading();
    }

    @Override
    public void doHideLoading() {
        this.hideLoading();
    }

}
