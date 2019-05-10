package com.example.basemodule.bean;

import java.io.Serializable;
import java.util.List;

public class VideoDetailUIBean implements Serializable {
    private boolean collect;
    private List<CommentUIBean> commentUIBeanList;

    public VideoDetailUIBean() {
    }

    public VideoDetailUIBean(boolean isCollect, List<CommentUIBean> commentUIBeanList) {
        this.collect = isCollect;
        this.commentUIBeanList = commentUIBeanList;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        collect = collect;
    }

    public List<CommentUIBean> getCommentUIBeanList() {
        return commentUIBeanList;
    }

    public void setCommentUIBeanList(List<CommentUIBean> commentUIBeanList) {
        this.commentUIBeanList = commentUIBeanList;
    }
}
