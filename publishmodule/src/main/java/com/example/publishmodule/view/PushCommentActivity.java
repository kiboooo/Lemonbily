package com.example.publishmodule.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.publishmodule.R;
import com.example.publishmodule.presenter.impl.PushPresenter;
import com.example.publishmodule.view.ui.IPushCommentView;

@Route(path = "/publishModule/PushCommentActivity")
public class PushCommentActivity extends BaseActivity<IPushCommentView, PushPresenter>
        implements IPushCommentView, View.OnClickListener {

    private EditText contentEdit;
    private ImageView backBtn;
    private Button pushBtn;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.publish_activity_push_comment);
        backBtn = findViewById(R.id.push_back_btn);
        contentEdit = findViewById(R.id.publish_push_content);
        pushBtn = findViewById(R.id.push_btn);
    }

    @Override
    protected void initSP() {

    }

    @Override
    public PushPresenter initPresenter() {
        return new PushPresenter(this, this);
    }

    @Override
    protected void initBinding() {
        pushBtn.setEnabled(false);
    }

    @Override
    public void initListener() {
        backBtn.setOnClickListener(this);
        pushBtn.setOnClickListener(this);
        contentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(contentEdit.getText())) {
                    pushBtn.setEnabled(false);
                    pushBtn.setBackground(getDrawable(R.drawable.base_radius_button3));
                    pushBtn.setTextColor(getColor(R.color.base_graybackground));
                } else {
                    pushBtn.setEnabled(true);
                    pushBtn.setBackground(getDrawable(R.drawable.base_radius2_button));
                    pushBtn.setTextColor(getColor(R.color.base_colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.push_back_btn) {
            finish();
        }

        if (id == R.id.push_btn) {
           //do push content
            if (!TextUtils.isEmpty(contentEdit.getText())) {
                mPresenter.pushPalDataToServer(contentEdit.getText().toString(), LoginStatusUtils.mLogin.getId());
            }
        }
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void pushCommentSuccess() {
        PalSquareNetServer.getInstance().getPalSquareEvents().PUBLISH_PAL_FINISH().post("push_finish");
        finish();
    }

    @Override
    public void pushCommentFail() {

    }


}
