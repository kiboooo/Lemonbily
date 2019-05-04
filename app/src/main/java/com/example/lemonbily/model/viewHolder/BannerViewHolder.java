package com.example.lemonbily.model.viewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lemonbily.R;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;

public class BannerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnLongClickListener {

    public RecyclerView bannerRecyclerView;
    public TextView bannerContent;
    private onRecyclerViewItemClickListener itemClickListener;

    public BannerViewHolder(@NonNull View itemView, @NonNull Context context) {
        super(itemView);
        bannerRecyclerView = itemView.findViewById(R.id.banner_view);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bannerRecyclerView.setLayoutManager(manager);
        bannerContent = itemView.findViewById(R.id.banner_content);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(onRecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(view, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onLongItemClick(view, getLayoutPosition());
            return true;
        }
        return false;
    }
}
