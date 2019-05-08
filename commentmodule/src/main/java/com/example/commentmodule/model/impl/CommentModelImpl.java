package com.example.commentmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.basemodule.bean.Comment;
import com.example.basemodule.bean.CommentUIBean;
import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.Like;
import com.example.basemodule.bus.generated.im.EventsDefineAsCommentEvents;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.CommentNetServer;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.commentmodule.model.ICommentModel;
import com.example.commentmodule.model.adapter.CommentContentAdapter;
import com.example.commentmodule.presenter.impl.CommentPressenter;
import com.jeremyliao.im.core.InvokingMessage;

public class CommentModelImpl extends BaseModel<CommentPressenter> implements ICommentModel {

    private CommentContentAdapter commentContentAdapter;
    private CommentUIBean commentUIBean;

    public CommentModelImpl() {
    }

    public CommentModelImpl(CommentPressenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerCommentInsertObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerCommentErrorBusObserver(owner);
    }

    @Override
    public void updateLike(int pid, int upORdown) {
        if (upORdown > 0) {
            Like l = new Like();
            l.setLtopalid(pid);
            l.setLuserid(LoginStatusUtils.mLogin.getId());
            PalSquareNetServer.getInstance().doLikeOperating(l);
        }else {
            PalSquareNetServer.getInstance().doUnLikeOperating(
                    LoginStatusUtils.mLogin.getId(), pid);
        }
    }

    @Override
    public CommentContentAdapter produceCommentAdapter(int position) {
        commentContentAdapter = new CommentContentAdapter(position);
        return commentContentAdapter;
    }

    @Override
    public void uploadCommentData( String s, int pid) {
        commentUIBean = new CommentUIBean();
        Comment comment = new Comment();
        comment.setComtype(0);
        comment.setUid(LoginStatusUtils.mLogin.getId());
        comment.setToid(pid);
        comment.setComcontent(s);
        commentUIBean.setmAccount(LoginStatusUtils.mAccount);
        commentUIBean.setmComment(comment);
        CommentNetServer.getInstance().insertCommentData(comment);
    }

    private void notifyAdapater() {
        commentContentAdapter.addItem(commentContentAdapter.getItemCount(), commentUIBean);
    }

    /* 注册数据总线事件监听 */
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

    private void registerCommentErrorBusObserver(LifecycleOwner owner) {
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
