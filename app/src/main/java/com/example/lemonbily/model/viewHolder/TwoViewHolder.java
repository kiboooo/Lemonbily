package com.example.lemonbily.model.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lemonbily.R;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;

public  class TwoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnLongClickListener{

    private View titleView;
    private TextView titleViewContent;

    private TextView oneMainContent;
    private TextView oneIntroductionContent;
    private ImageView oneImageView;

    private TextView twoMainContent;
    private TextView twoIntroductionContent;
    private ImageView twoImageView;

    private onRecyclerViewItemClickListener listener;

    public TwoViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.tow_item_title);
        titleViewContent = itemView.findViewById(R.id.item_title);

        oneMainContent = itemView.findViewById(R.id.two_onecard_main_content);
        oneIntroductionContent = itemView.findViewById(R.id.two_onecard_introduction_content);
        oneImageView = itemView.findViewById(R.id.tow_onecard_image_view);

        twoMainContent = itemView.findViewById(R.id.two_twocard_main_content);
        twoIntroductionContent = itemView.findViewById(R.id.two_twocard_introduction_content);
        twoImageView = itemView.findViewById(R.id.tow_twocard_image_view);

        titleView.setOnClickListener(this);
        oneMainContent.setOnClickListener(this);
        oneIntroductionContent.setOnClickListener(this);
        oneImageView.setOnClickListener(this);
        twoMainContent.setOnClickListener(this);
        twoIntroductionContent.setOnClickListener(this);
        twoImageView.setOnClickListener(this);
    }

    public void setListener(onRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClick(this, view, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (listener != null) {
            listener.onLongItemClick(this, view, getLayoutPosition());
            return true;
        }
        return false;
    }
}
