package com.example.lemonbily.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface onRecyclerViewItemClickListener {

    void onItemClick(RecyclerView.ViewHolder vh, @NonNull View v, int position);


    void onLongItemClick(RecyclerView.ViewHolder vh,@NonNull View v, int position);
}
