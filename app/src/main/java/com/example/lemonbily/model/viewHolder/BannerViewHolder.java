package com.example.lemonbily.model.viewHolder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.R;
import com.example.lemonbily.model.adapter.BannerAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;

public class BannerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnLongClickListener {

    private static final int WHAT_AUTO_PLAY = 99999;

    private int autoPlayDuration = 3000;
    private int currentIndex = 0;
    public RecyclerView bannerRecyclerView;
    public TextView bannerContent;
    private BannerAdapter adapter;
    private onRecyclerViewItemClickListener itemClickListener;

    public BannerViewHolder(@NonNull final View itemView, @NonNull Context context) {
        super(itemView);

        bannerRecyclerView = itemView.findViewById(R.id.banner_view);
        final LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bannerRecyclerView.setLayoutManager(manager);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(bannerRecyclerView);

        bannerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int firstReal = manager.findFirstVisibleItemPosition();
                View viewFirst = manager.findViewByPosition(firstReal);
                if (viewFirst != null) {
                    float width = viewFirst.getWidth();
                    if (width != 0) {
                        float right = viewFirst.getRight();
                        float offsetRatio = right / width;
                        if (offsetRatio > 0.8) {
                            if (currentIndex != firstReal) {
                                currentIndex = firstReal;
                                refreshTextContent(currentIndex);
                            }
                        } else if (offsetRatio < 0.2) {
                            if (currentIndex != firstReal + 1) {
                                currentIndex = firstReal + 1;
                                refreshTextContent(currentIndex);
                            }
                        }
                    }
                }
            }
        });

        adapter = new BannerAdapter(context, CommonUtils.initBannerList());

        bannerRecyclerView.setAdapter(adapter);
        bannerContent = itemView.findViewById(R.id.banner_content);
        bannerContent.setText(adapter.getBannerImageListData(0));
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(onRecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        adapter.setmListener(itemClickListener);
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(this,view, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onLongItemClick(this, view, getLayoutPosition());
            return true;
        }
        return false;
    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                bannerRecyclerView.smoothScrollToPosition(++currentIndex);
                refreshTextContent(currentIndex);
                mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
            }
            return false;
        }
    });

    public void startPoll() {
        mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
    }

    private void refreshTextContent(int position) {
        bannerContent.setText(adapter.getBannerImageListData(position % BannerAdapter.bannerSize));
    }

}
