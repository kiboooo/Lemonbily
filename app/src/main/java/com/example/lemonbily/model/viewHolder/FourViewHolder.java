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

import java.util.List;

public class FourViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private View titleView;
    private TextView titleViewContent;

    private TextView oneMainContent;
    private TextView oneIntroductionContent;
    private ImageView oneImageView;

    private TextView twoMainContent;
    private TextView twoIntroductionContent;
    private ImageView twoImageView;

    private TextView threeMainContent;
    private TextView threeIntroductionContent;
    private ImageView threeImageView;

    private TextView fourMainContent;
    private TextView fourIntroductionContent;
    private ImageView fourImageView;

    private onRecyclerViewItemClickListener listener;

    public FourViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.four_item_title);
        titleViewContent = itemView.findViewById(R.id.item_title);

        oneMainContent = itemView.findViewById(R.id.four_onecard_main_content);
        oneIntroductionContent = itemView.findViewById(R.id.four_onecard_introduction_content);
        oneImageView = itemView.findViewById(R.id.four_onecard_image_view);

        twoMainContent = itemView.findViewById(R.id.four_twocard_main_content);
        twoIntroductionContent = itemView.findViewById(R.id.four_twocard_introduction_content);
        twoImageView = itemView.findViewById(R.id.four_twocard_image_view);

        threeMainContent = itemView.findViewById(R.id.four_threecard_main_content);
        threeIntroductionContent = itemView.findViewById(R.id.four_threecard_introduction_content);
        threeImageView = itemView.findViewById(R.id.four_threecard_image_view);

        fourMainContent = itemView.findViewById(R.id.four_fourcard_main_content);
        fourIntroductionContent = itemView.findViewById(R.id.four_fourcard_introduction_content);
        fourImageView = itemView.findViewById(R.id.four_fourcard_image_view);

        titleView.setOnClickListener(this);
        oneMainContent.setOnClickListener(this);
        oneIntroductionContent.setOnClickListener(this);
        oneImageView.setOnClickListener(this);

        twoImageView.setOnClickListener(this);
        twoIntroductionContent.setOnClickListener(this);
        twoMainContent.setOnClickListener(this);

        threeImageView.setOnClickListener(this);
        threeIntroductionContent.setOnClickListener(this);
        threeMainContent.setOnClickListener(this);

        fourImageView.setOnClickListener(this);
        fourIntroductionContent.setOnClickListener(this);
        fourMainContent.setOnClickListener(this);
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

    public void bindData(Context mContext, UIBeans beans) {
        if (beans == null) {
            return;
        }
        titleViewContent.setText(beans.getTitleContext());
        List<Video> datas = (List<Video>) beans.getObject();
        if (datas == null || datas.size() < 4) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            if (i == 0) {
                bindDataForVH(mContext, oneMainContent, oneIntroductionContent, oneImageView, datas.get(i));
            } else if (i == 1) {
                bindDataForVH(mContext, twoMainContent, twoIntroductionContent, twoImageView, datas.get(i));
            } else if (i == 2) {
                bindDataForVH(mContext, threeMainContent, threeIntroductionContent, threeImageView, datas.get(i));
            } else if (i == 3) {
                bindDataForVH(mContext, fourMainContent, fourIntroductionContent, fourImageView, datas.get(i));
            }
        }
    }

    private void bindDataForVH(Context context, TextView main, TextView introduction, ImageView imageView, Video v) {
        main.setText(v.getVname());
        introduction.setText(v.getVdescribe());
        imageView.setBackgroundColor(Color.TRANSPARENT);
        Glide.with(context)
                .load(NetWorkServer.SERVER_URL
                        + v.getVpicture())
                .apply(CommonUtils.imageRequestOption())
                .into(imageView);
    }
}