package com.example.lemonbily.model.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
