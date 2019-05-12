package com.example.loginmodule.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.basemodule.bean.Video;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.R;

import java.util.List;

public class MineCollectAdapter extends RecyclerView.Adapter<MineCollectAdapter.CollectViewHolder> {


    private Context curContext;
    private List<Video> curVideoList;

    public MineCollectAdapter(List<Video> curVideoList) {
        this.curVideoList = curVideoList;
    }

    @NonNull
    @Override
    public CollectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        curContext = viewGroup.getContext();
        View view = LayoutInflater.from(curContext)
                .inflate(R.layout.login_mine_collect_item_view, viewGroup, false);
        return new CollectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectViewHolder vh, int i) {
        if (LoginStatusUtils.isLogin && curVideoList != null) {
            final Video video = curVideoList.get(i);
            if (video != null) {
                vh.videoName.setText(video.getVname());
                vh.videoTime.setText(video.getVtimer());
                Glide.with(curContext)
                        .load(NetWorkServer.SERVER_URL
                                + video.getVpicture())
                        .apply(CommonUtils.imageRequestOption())
                        .into(vh.image);
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toDetailPageAction(video);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return curVideoList == null ? 0 : curVideoList.size();
    }

    static class CollectViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView videoTime;
        TextView videoName;

        public CollectViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.collect_image);
            videoTime = itemView.findViewById(R.id.collect_video_time);
            videoName = itemView.findViewById(R.id.collect_video_name);
        }
    }

    //拉起详情页
    private void toDetailPageAction(Video video) {
        ARouter.getInstance()
                .build("/VideoPlayModule/VideoDetailedPageActivity")
                .withSerializable("videoData", video)
                .navigation();
    }
}
