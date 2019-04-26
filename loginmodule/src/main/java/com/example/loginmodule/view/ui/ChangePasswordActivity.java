package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.ChangePasswordPresenter;
import com.example.loginmodule.view.IChangePasswordView;

@Route(path = "/LoginModule/ChangePasswordActivity")
public class ChangePasswordActivity
        extends BaseActivity<IChangePasswordView, ChangePasswordPresenter>
        implements View.OnClickListener,IChangePasswordView {

    ImageView titleBarBackBtn;
    TextView titleBarDescription;
    EditText mOldPassword;
    EditText mNewPassword;
    EditText mNewPasswordCheck;
    Button mContinue;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_change_password);
        titleBarBackBtn = findViewById(R.id.base_normal_title_back_btn);
        titleBarDescription = findViewById(R.id.base_normal_back_title);
        mOldPassword = findViewById(R.id.change_password_old);
        mNewPassword = findViewById(R.id.change_password_new);
        mNewPasswordCheck = findViewById(R.id.change_password_new_again);
        mContinue = findViewById(R.id.change_password_continue);
    }

    @Override
    public ChangePasswordPresenter initPresenter() {
        return new ChangePasswordPresenter(this, this);
    }


    @Override
    protected void initBinding() {
        titleBarDescription.setText("修改密码");
    }

    @Override
    public void initListener() {
        titleBarBackBtn.setOnClickListener(this);
        mContinue.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                if (checkInputContent()) {
                    showLoading();
                    mPresenter.changePassword(LoginStatusUtils.mLogin.getId(),
                            LoginStatusUtils.mLogin.getLphone(),
                            LoginStatusUtils.mLogin.getLpassword(),
                            CommonUtils.passwordEncode(mNewPassword.getText().toString()));
                }
            }
        });
    }

    private boolean checkInputContent() {
        String old = mOldPassword.getText().toString();
        String newPass = mNewPassword.getText().toString();
        String checkPass = mNewPasswordCheck.getText().toString();
        if (!isCorrectPass(old)) {
            mOldPassword.setError("密码错误");
            return false;
        }
        if (newPass.length() < 6) {
            mNewPassword.setError(getResources().getString(R.string.login_register_password_content));
            return false;
        }

        if (!newPass.equals(checkPass)) {
            mNewPasswordCheck.setError("两次输入不一致");
            return false;
        }
        return true;
    }

    private boolean isCorrectPass(String old) {
        if (CommonUtils.isTextEmpty(old)
                || old.equals(getResources().getString(R.string.login_old_password))) {
            return false;
        }
        return CommonUtils.passwordEncode(old).equals(LoginStatusUtils.mLogin.getLpassword());
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
    public void changePasswodSuccess() {
        showToasts("修改成功", Toast.LENGTH_SHORT);
        hideLoading();
        finish();
    }

    @Override
    public void changePasswordFail() {
        hideLoading();
    }

    @Override
    public void doHider() {
        hideLoading();
    }
}
