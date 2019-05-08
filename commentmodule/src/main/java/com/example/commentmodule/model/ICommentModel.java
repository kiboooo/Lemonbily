package com.example.commentmodule.model;

import com.example.basemodule.model.IBaseModel;

public interface ICommentModel extends IBaseModel {

    void updateLike(int pid, int upORdown);
}
