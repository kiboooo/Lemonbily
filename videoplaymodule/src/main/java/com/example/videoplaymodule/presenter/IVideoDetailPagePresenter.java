package com.example.videoplaymodule.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public interface IVideoDetailPagePresenter {
    RecyclerView.Adapter produceAdapter(Context context);
}
