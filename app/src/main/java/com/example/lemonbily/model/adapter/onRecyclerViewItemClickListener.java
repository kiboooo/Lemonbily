package com.example.lemonbily.model.adapter;

import android.view.View;

public interface onRecyclerViewItemClickListener {

    void onItemClick( View v, int position);

    void onLongItemClick( View v, int position);
}
