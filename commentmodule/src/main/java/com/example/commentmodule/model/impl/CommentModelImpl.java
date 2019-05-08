package com.example.commentmodule.model.impl;

import android.arch.lifecycle.LifecycleOwner;

import com.example.basemodule.bean.Like;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.net.PalSquareNetServer;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.commentmodule.model.ICommentModel;
import com.example.commentmodule.presenter.CommentPressenter;

public class CommentModelImpl extends BaseModel<CommentPressenter> implements ICommentModel {
    public CommentModelImpl() {
    }

    public CommentModelImpl(CommentPressenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {

    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {

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
}
