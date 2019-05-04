package com.example.lemonbily.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerItemViewHolder>  {

    private List<String> bannerImageList;
    private Context mContext;

    public void updateData(List<String> list) {
        bannerImageList = list;
        notifyDataSetChanged();
    }

    public BannerAdapter(Context context, List<String> bannerImageList) {
        this.mContext = context;
        this.bannerImageList = bannerImageList;
    }

    @NonNull
    @Override
    public BannerItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_banner_item_view, viewGroup, false);
        return new BannerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerItemViewHolder holder, int i) {
        Glide.with(mContext)
                .load(NetWorkServer.SERVER_URL + bannerImageList.get(i % bannerImageList.size()))
                .apply(CommonUtils.imageRequestOption())
                .into(holder.itemImage);
    }


    @Override
    public int getItemCount() {
        if (bannerImageList == null) {
            return 0;
        }

        return bannerImageList.size() < 2 ? 1 : Integer.MAX_VALUE;
    }

    static class BannerItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;

        public BannerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.banner_image_view);
        }
    }
}
