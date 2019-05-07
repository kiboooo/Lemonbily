package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.List;

public class PalSquareBean implements Serializable {
    private boolean like = false;
    private boolean attention = false;

    private Account account;
    private Palcircle palcircle;
    private List<CommentUIBean> commentUIBeans;

    public PalSquareBean() {
    }

    public PalSquareBean(boolean like, boolean attention, Account account, Palcircle palcircle, List<CommentUIBean> commentUIBeans) {
        this.like = like;
        this.attention = attention;
        this.account = account;
        this.palcircle = palcircle;
        this.commentUIBeans = commentUIBeans;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
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
