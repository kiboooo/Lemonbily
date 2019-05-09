package com.example.videoplaymodule.view;

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
import com.example.basemodule.bean.Video;
import com.example.basemodule.net.NetWorkServer;
import com.example.videoplaymodule.R;
import com.example.videoplaymodule.presenter.impl.VideoDetailPagePresenter;
import com.example.videoplaymodule.view.ui.IVedioDetailedPageView;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

@Route(path = "/VideoPlayModule/VideoDetailedPageActivity")
public class VideoDetailedPageActivity
        extends BaseVideoActivity<IVedioDetailedPageView, VideoDetailPagePresenter>
        implements IVedioDetailedPageView,View.OnClickListener {

    @Autowired
    Video videoData;
    private TextView videoName;
    private TextView videoDescribe;
    private ImageView collectIcon;
    private StandardGSYVideoPlayer player;
    private RecyclerView commentRecycler;
    private EditText pushEdit;
    private Button pushBtn;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.videoplay_activity_video_play);
        ARouter.getInstance().inject(this);
        videoName = findViewById(R.id.detail_video_name);
        videoDescribe = findViewById(R.id.detail_video_describe);
        collectIcon = findViewById(R.id.detail_video_collect_btn);
        player = findViewById(R.id.detail_video_danmaku_player);
        commentRecycler = findViewById(R.id.detail_video_comment_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        commentRecycler.setLayoutManager(manager);
        pushEdit = findViewById(R.id.detail_video_edit_push);
        pushBtn = findViewById(R.id.detail_video_push_btn);
        player.getTitleTextView().setVisibility(View.GONE);
        player.getBackButton().setVisibility(View.VISIBLE);
    }
    @Override
    public VideoDetailPagePresenter initPresenter() {
        return new VideoDetailPagePresenter(this, this);
    }

    @Override
    protected void initBinding() {
        if (videoData != null) {
            videoName.setText(videoData.getVname());
            videoDescribe.setText(videoData.getVdescribe());
            initVideoBuilderMode();
        }
    }

    @Override
    public void initListener() {
        player.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collectIcon.setOnClickListener(this);
        pushBtn.setOnClickListener(this);
        pushEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(pushEdit.getText())) {
                    pushBtn.setEnabled(false);
                    pushBtn.setBackground(getDrawable(com.example.commentmodule.R.color.base_colorBackground));
                    pushBtn.setTextColor(getColor(com.example.commentmodule.R.color.base_colorWeakPoint));
                }else {
                    pushBtn.setEnabled(true);
                    pushBtn.setBackground(getDrawable(com.example.commentmodule.R.drawable.base_radius2_button));
                    pushBtn.setTextColor(getColor(com.example.commentmodule.R.color.base_colorPrimary));
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
        if (id == R.id.detail_video_collect_btn) {
            int i;
            if (!collectIcon.isSelected()) {
                collectIcon.setSelected(true);
                i = 1;
            }else {
                collectIcon.setSelected(false);
                i = -1;
            }
            //更新点赞结果
//            mPresenter.updateLikeNumber(psb.getPalcircle().getPalid(), i);
        }
        if (id == R.id.detail_video_push_btn) {
            //发送到RecycylerView 中进行假显
            Toast.makeText(this, pushEdit.getText().toString(), Toast.LENGTH_SHORT).show();
//            mPresenter.uploadCommentData(commentPush.getText().toString(), psb.getPalcircle().getPalid());
        }
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return player;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        if (videoData != null) {
            ImageView imageView = new ImageView(this);
            Glide.with(getApplicationContext())
                    .load(NetWorkServer.SERVER_URL +videoData.getVpicture())
                    .into(imageView);
            return new GSYVideoOptionBuilder()
                    .setThumbImageView(imageView)
                    .setUrl(NetWorkServer.SERVER_URL + videoData.getVpath())
                    .setCacheWithPlay(true)
                    .setVideoTitle(videoData.getVname())
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setSeekRatio(5);
        }
        return null;
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void showToat(String msg, int state) {
        showToasts(msg, state);
    }

}
