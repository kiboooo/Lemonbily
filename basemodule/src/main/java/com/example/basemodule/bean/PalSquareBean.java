package com.example.basemodule.bean;

import java.util.List;

public class PalSquareBean {
    private boolean isLike = false;
    private boolean isAttention = false;

    private Account account;
    private Palcircle palcircle;
    private List<CommentUIBean> commentUIBeans;

    public PalSquareBean() {
    }

    public PalSquareBean(boolean isLike, boolean isAttention,
                         Account account, Palcircle palcircle, List<CommentUIBean> commentUIBeans) {
        this.isLike = isLike;
        this.isAttention = isAttention;
        this.account = account;
        this.palcircle = palcircle;
        this.commentUIBeans = commentUIBeans;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Palcircle getPalcircle() {
        return palcircle;
    }

    public void setPalcircle(Palcircle palcircle) {
        this.palcircle = palcircle;
    }

    public List<CommentUIBean> getCommentUIBeans() {
        return commentUIBeans;
    }

    public void setCommentUIBeans(List<CommentUIBean> commentUIBeans) {
        this.commentUIBeans = commentUIBeans;
    }
}
