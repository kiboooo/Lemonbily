package com.example.commentmodule.model;

import com.example.basemodule.model.IBaseModel;
import com.example.commentmodule.model.adapter.CommentContentAdapter;

public interface ICommentModel extends IBaseModel {

    void updateLike(int pid, int upORdown);

    CommentContentAdapter produceCommentAdapter(int position);

    void uploadCommentData( String s, int pid);
}
