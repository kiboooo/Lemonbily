package com.example.lemonbily.model.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basemodule.bean.UIBeans;
import com.example.basemodule.bean.Video;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.R;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;

public class OneViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private TextView titleViewContent;
    private TextView oneMainContent;
    private TextView oneIntroductionContent;
    private ImageView oneImageView;
    private View titleView;
    private onRecyclerViewItemClickListener listener;

    public OneViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.one_item_title);
        titleViewContent = itemView.findViewById(R.id.item_title);
        oneMainContent = itemView.findViewById(R.id.one_main_content);
        oneIntroductionContent = itemView.findViewById(R.id.one_introduction_content);
        oneImageView = itemView.findViewById(R.id.one_item_image_view);

        titleView.setOnClickListener(this);
        oneMainContent.setOnClickListener(this);
        oneIntroductionContent.setOnClickListener(this);
        oneImageView.setOnClickListener(this);
    }

    public void setListener(onRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClick(this, view, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (listener != null) {
            listener.onLongItemClick(this, view, getAdapterPosition());
            return true;
        }
        return false;
    }

    public void bindData(Context mContext,UIBeans beans) {
        Video video = (Video) beans.getObject();
        if (video != null) {
            titleViewContent.setText(beans.getTitleContext());
            oneMainContent.setText(video.getVname());
            oneIntroductionContent.setText(video.getVdescribe());
            oneImageView.setBackgroundColor(Color.TRANSPARENT);
            Glide.with(mContext)
                    .load(NetWorkServer.SERVER_URL
                            + video.getVpicture())
                    .apply(CommonUtils.imageRequestOption())
                    .into(oneImageView);
        }
    }
}
