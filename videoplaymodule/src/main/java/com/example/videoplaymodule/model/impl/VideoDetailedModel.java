package com.example.videoplaymodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.basemodule.bean.Collect;
import com.example.basemodule.bean.Comment;
import com.example.basemodule.bean.CommentUIBean;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.VideoDetailUIBean;
import com.example.basemodule.bus.generated.im.EventsDefineAsCommentEvents;
import com.example.basemodule.bus.generated.im.EventsDefineAsVideoEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.CommentNetServer;
import com.example.basemodule.net.VideoNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.videoplaymodule.model.IViewDetailModel;
import com.example.videoplaymodule.model.adapter.DetailCommentAdapter;
import com.example.videoplaymodule.presenter.impl.VideoDetailPagePresenter;
import com.jeremyliao.im.core.InvokingMessage;

public class VideoDetailedModel extends BaseModel<VideoDetailPagePresenter>
        implements IViewDetailModel {

    private VideoDetailUIBean videoDetailUIBean;
    private DetailCommentAdapter detailCommentAdapter;
    private CommentUIBean tempCommentUIBean;

    public VideoDetailedModel() {
    }

    public VideoDetailedModel(VideoDetailPagePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInitVideoDataDataObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerVideoDetailErrorBusObserver(owner);
        registerCommentInsertObserver(owner);
    }

    @Override
    public RecyclerView.Adapter produceAdapter(Context context) {
        detailCommentAdapter = loadFakerData(context);
        return detailCommentAdapter;
    }

    @Override
    public boolean isCurVideoCollect() {
        if (videoDetailUIBean == null)
            return false;
        else
            return videoDetailUIBean.isCollect();
    }

    @Override
    public void updateCollectState(Integer vid, int i) {
        if (i > 0) {
            videoDetailUIBean.setCollect(true);
            Collect tempCollect = new Collect();
            tempCollect.setVid(vid);
            tempCollect.setColid(LoginStatusUtils.mLogin.getId());
            VideoNetServer.getInstance().doCollectVideo(tempCollect);
        } else {
            //防止弱网环境下，导致数据不同步
            if (videoDetailUIBean.isCollect()) {
                videoDetailUIBean.setCollect(false);
                VideoNetServer.getInstance().doUnCollectVideo(LoginStatusUtils.mLogin.getId(), vid);
            }
        }
    }

    @Override
    public void uploadCommentData(String toString, Integer vid) {
        if (tempCommentUIBean == null) {
            tempCommentUIBean = new CommentUIBean();
            Comment comment = new Comment();
            comment.setComcontent(toString);
            comment.setComtype(1);
            comment.setUid(LoginStatusUtils.mLogin.getId());
            comment.setToid(vid);
            tempCommentUIBean.setmComment(comment);
            tempCommentUIBean.setmAccount(LoginStatusUtils.mAccount);
            CommentNetServer.getInstance().insertCommentData(comment);
        }
    }

    private DetailCommentAdapter loadFakerData(Context context) {
        if (videoDetailUIBean != null) {
            return new DetailCommentAdapter(context, videoDetailUIBean.getCommentUIBeanList());
        }else
            return null;
    }

    private void notifyAdapater() {
        if (tempCommentUIBean != null) {
            detailCommentAdapter.addItem(0, tempCommentUIBean);
            tempCommentUIBean = null;//清空临时数据
            getPresenter().scrollToPosition(0);
        }
    }

    /* 注册数据总线事件监听 */
    private void registerInitVideoDataDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .LOAD_VIDEO_DETAIL_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取VideoDetailData出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                videoDetailUIBean = (VideoDetailUIBean) jsonResponse.getData();
                                getPresenter().initCurrentCommentDataSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().initCurrentCommentDataFail();
                            }
                        }

                    }
                });
    }

    private void registerCommentInsertObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsCommentEvents.class)
                .COMMENT_INSERT()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("评论出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        } else {
                            if (jsonResponse.getCode() == 0) {
                                notifyAdapater();
                                getPresenter().commentPublishSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode() + " : "
                                        + jsonResponse.getMsg(), Toast.LENGTH_SHORT);
                                getPresenter().commentPublishFail();
                            }
                        }

                    }
                });
    }

    private void registerVideoDetailErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .VIDEO_DETAIL_REQUEST_ERROR()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (getPresenter() != null) {
                            getPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });

        InvokingMessage.get().as(EventsDefineAsCommentEvents.class)
                .COMMENT_REQUEST_ERROR()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (getPresenter() != null) {
                            getPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });
    }


}
