package com.example.commentmodule.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.basemodule.view.BaseActivity;
import com.example.commentmodule.R;
import com.example.commentmodule.presenter.impl.CommentPressenter;
import com.example.commentmodule.view.ui.ICommentView;

@Route(path = "/CommentModule/CommentActivity")
public class CommentActivty extends BaseActivity<ICommentView, CommentPressenter>
        implements ICommentView, View.OnClickListener {

    ImageView backBtn;
    TextView titleDescription;
    TextView content;
    ImageView avatar;
    ImageView gender;
    TextView name;
    ImageView likeBtn;
    TextView likeNumContent;
    EditText commentPush;
    Button pushBtn;
    RecyclerView commentListRecyclerView;

    @Autowired(name = "position")
    int curPosition;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.comment_activity_comment);
        ARouter.getInstance().inject(this);
        titleDescription = findViewById(R.id.base_normal_back_title);
        titleDescription.setText("动态详情页");
        backBtn = findViewById(R.id.base_normal_title_back_btn);
        content = findViewById(R.id.comment_detail_content);
        avatar = findViewById(R.id.square_detail_avatar);
        gender = findViewById(R.id.square_detail_gender);
        name = findViewById(R.id.square_detail_name);
        likeBtn = findViewById(R.id.square_detail_like_icon);
        likeNumContent = findViewById(R.id.detail_like_number);
        commentPush = findViewById(R.id.comment_detail_push);
        pushBtn = findViewById(R.id.comment_detail_push_btn);
        pushBtn.setEnabled(false);
        pushBtn.setTextColor(getColor(R.color.base_colorWeakPoint));
        commentListRecyclerView = findViewById(R.id.detail_comment_recycler);
        commentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initSP() {
    }

    @Override
    public CommentPressenter initPresenter() {
        return new CommentPressenter(this, this);
    }

    @Override
    protected void initBinding() {
        PalSquareBean psb = PalSquareUtils.palSquareBeans.get(curPosition);
        if (psb != null) {
            Glide.with(this)
                    .load(NetWorkServer.SERVER_URL
                            + psb.getAccount().getAavatar())
                    .apply(CommonUtils.avatarRequestOption())
                    .into(avatar);
            name.setText(psb.getAccount().getAname());
            gender.setSelected(psb.getAccount().getAsex() != null
                    && psb.getAccount().getAsex().equals("w"));
            content.setText(psb.getPalcircle().getPalcontent());
            likeBtn.setSelected(psb.isLike());
            if (psb.getPalcircle().getPallicknum() >= 0) {
                likeNumContent.setText(psb.getPalcircle().getPallicknum() + " 个赞");
            } else {
                likeNumContent.setVisibility(View.INVISIBLE);
            }
            commentListRecyclerView.setAdapter(mPresenter.produceCommentAdapter(curPosition));
        }
    }

    @Override
    public void initListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        likeBtn.setOnClickListener(this);
        pushBtn.setOnClickListener(this);
        commentPush.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(commentPush.getText())) {
                    pushBtn.setEnabled(false);
                    pushBtn.setBackground(getDrawable(R.color.base_colorBackground));
                    pushBtn.setTextColor(getColor(R.color.base_colorWeakPoint));
                }else {
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
        PalSquareBean psb = PalSquareUtils.palSquareBeans.get(curPosition);
        if (id == R.id.square_detail_like_icon) {
            int i;
            if (!likeBtn.isSelected()) {
                likeBtn.setSelected(true);
                PalSquareUtils.palSquareBeans.get(curPosition).setLike(true);
                i = 1;
            }else {
                likeBtn.setSelected(false);
                PalSquareUtils.palSquareBeans.get(curPosition).setLike(false);
                i = -1;
            }
            PalSquareUtils.palSquareBeans.get(curPosition)
                    .getPalcircle().setPallicknum(psb.getPalcircle().getPallicknum() + i);
            //更新点赞结果
            mPresenter.updateLikeNumber(psb.getPalcircle().getPalid(), i);
            likeNumContent.setVisibility(View.VISIBLE);
            likeNumContent.setText(psb.getPalcircle().getPallicknum() + " 个赞");
        }

        if (id == R.id.comment_detail_push_btn) {
            //发送到RecycylerView 中进行假显
            Toast.makeText(this, commentPush.getText().toString(), Toast.LENGTH_SHORT).show();
            mPresenter.uploadCommentData(commentPush.getText().toString(), psb.getPalcircle().getPalid());
            CommonUtils.closeSoftKeybord(commentPush, getApplicationContext());
        }
    }

    @Override
    public void showToast(String msg, int state) {
        showToasts(msg, state);
    }

    @Override
    public void commentPublishSuccess() {
        commentPushChangeFocus();
    }

    private void commentPushChangeFocus() {
        commentPush.setText("");
        commentPush.clearFocus();
    }

    @Override
    public void commentPublishFail() {

    }

}
