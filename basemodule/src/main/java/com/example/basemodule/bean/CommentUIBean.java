package com.example.basemodule.bean;

public class CommentUIBean {
    private Account mAccount;
    private Comment mComment;

    public CommentUIBean() {
    }

    public CommentUIBean(Account mAccount, Comment mComment) {
        this.mAccount = mAccount;
        this.mComment = mComment;
    }

    public Comment getmComment() {
        return mComment;
    }

    public void setmComment(Comment mComment) {
        this.mComment = mComment;
    }

    public Account getmAccount() {
        return mAccount;
    }

    public void setmAccount(Account mAccount) {
        this.mAccount = mAccount;
    }
}
