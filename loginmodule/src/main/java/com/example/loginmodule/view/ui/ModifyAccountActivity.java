package com.example.loginmodule.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.basemodule.bean.Account;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.OnMutiClickListener;
import com.example.basemodule.view.BaseActivity;
import com.example.loginmodule.R;
import com.example.loginmodule.presenter.impl.ModifyAccountPresenter;
import com.example.loginmodule.view.IModifyAccountView;

import java.io.File;

@Route(path = "/LoginModule/ModifyAccountActivity")
public class ModifyAccountActivity extends BaseActivity<IModifyAccountView, ModifyAccountPresenter>
        implements View.OnClickListener,IModifyAccountView {

    ImageView backBtn;
    TextView titleDescription;
    EditText accountName;
    RadioGroup genderRG;
    Button finishBtn;
    ImageView avatar;

    File avatarFile = null;
    String accountGender = "m";

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity_modify_data);
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        titleDescription = findViewById(R.id.base_normal_back_title);
        accountName = findViewById(R.id.modify_account_name);
        finishBtn = findViewById(R.id.modify_finish);
        avatar = findViewById(R.id.modify_avatar);
        genderRG = findViewById(R.id.modify_gender);
    }

    @Override
    protected void initSP() {

    }

    @Override
    public ModifyAccountPresenter initPresenter() {
        return new ModifyAccountPresenter(this, this);
    }

    @Override
    protected void initBinding() {
        titleDescription.setText("修改个人资料");
        if (LoginStatusUtils.mAccount != null) {
            accountName.setText(LoginStatusUtils.mAccount.getAname());
            if (LoginStatusUtils.mAccount.getAsex().equals("w")) {
                genderRG.check(R.id.gender_female);
                accountGender = "w";
            }else {
                genderRG.check(R.id.gender_man);
                accountGender = "m";
            }
            showAvatar(LoginStatusUtils.mAccount.getAavatar());
        }
    }

    private void showAvatar(String avatarPath) {
        //通过Glide 加载图片
        Glide.with(this)
                .load(NetWorkServer.SERVER_URL + avatarPath)
                .apply(CommonUtils.avatarRequestOption())
                .into(avatar);
    }

    @Override
    public void initListener() {
        backBtn.setOnClickListener(this);
        avatar.setOnClickListener(this);
        finishBtn.setOnClickListener(new OnMutiClickListener() {
            @Override
            public void onMutiClick(View view) {
                if (checkModify()) {
                    doModify();
                } else if (avatarFile != null) {
                    doModifyAvatar();
                }
            }
        });
        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
    private boolean checkModify() {
        String name = accountName.getText().toString();
        if (CommonUtils.isTextEmpty(name)) {
            accountName.setError("非法的用户名");
            return false;
        }
        return (!name.equals(LoginStatusUtils.mAccount.getAname())
                || !accountGender.equals(LoginStatusUtils.mAccount.getAsex()));
    }

    private void doModify() {
        Account account = new Account(LoginStatusUtils.mAccount.getAid(),
                accountName.getText().toString(), accountGender, LoginStatusUtils.mAccount.getAavatar());
        mPresenter.modifyAccountData(account, avatarFile);
    }

    private void doModifyAvatar() {
        mPresenter.modifyAccountAvatar(avatarFile);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.base_normal_title_back_btn) {
            finish();
        }
        if (i == R.id.modify_avatar) {
            toSelectAvatar();
            showToasts("拉起选择头像界面", Toast.LENGTH_SHORT);
        }
    }

    private void toSelectAvatar() {
        //拉起图片选择界面
        // avatarFile = new File();
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg,state);
    }

    @Override
    public void modifySuccess() {
        hideLoading();
        showToasts("修改成功", Toast.LENGTH_SHORT);
    }

    @Override
    public void modifFail() {
        hideLoading();
    }

    @Override
    public void doHider() {
        hideLoading();
    }
}
