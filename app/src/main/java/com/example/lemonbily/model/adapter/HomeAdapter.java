package com.example.lemonbily.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basemodule.bean.UIBeans;
import com.example.lemonbily.R;
import com.example.lemonbily.model.bean.HomeUIBeans;
import com.example.lemonbily.model.viewHolder.BannerViewHolder;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEAD_VIEW = 10000;
    private static final int FOOT_VIEW = 20000;

    private List<UIBeans> uiBeansList;
    private int headAndFootCount = 0;
    private Context mContext;
    private static onRecyclerViewItemClickListener itemClickListener;

    public HomeAdapter() {
    }

    public HomeAdapter(Context context,List<UIBeans> uiBeansList ) {
        this.mContext = context;
        this.uiBeansList = uiBeansList;
    }

    public void setItemClickListener(onRecyclerViewItemClickListener itemClickListener) {
        HomeAdapter.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mView;
        switch (viewType) {
            case HomeUIBeans.EMPTY_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_one_item, viewGroup, false);
                return new NormalViewHolder(mView);

            case HomeUIBeans.NORMAL_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_one_item, viewGroup, false);
                return new NormalViewHolder(mView);

            case HEAD_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_banner, viewGroup, false);
                BannerViewHolder bannerViewHolder = new BannerViewHolder(mView, mContext);
                bannerViewHolder.setItemClickListener(itemClickListener);
                return bannerViewHolder;

            case HomeUIBeans.TOW_IN_LINE_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_two_item, viewGroup, false);
                return new TwoViewHolder(mView);

            case HomeUIBeans.FOUR_GRID_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_four_item, viewGroup, false);
                return new FourViewHolder(mView);

            case FOOT_VIEW:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_foot_view, viewGroup, false);
                return new FootViewHolder(mView);

            default:
                mView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.fragment_home_one_item, viewGroup, false);
                return new NormalViewHolder(mView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) viewHolder;
        }

        if (viewHolder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.startPoll();
        }

        if (viewHolder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;
        }

        if (viewHolder instanceof TwoViewHolder) {
            TwoViewHolder twoViewHolder = (TwoViewHolder) viewHolder;
        }

        if (viewHolder instanceof FourViewHolder) {
            FourViewHolder fourViewHolder = (FourViewHolder) viewHolder;
        }

    }

    @Override
    public int getItemCount() {
        return uiBeansList.size() + headAndFootCount;
    }

    public void addFootView() {
        headAndFootCount += 1;
    }

    public Object getItemData(int position) {
        if (uiBeansList != null) {
            return uiBeansList.get(position).getObject();
        }else
            return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (uiBeansList.size() == 0) {
            return HomeUIBeans.EMPTY_VIEW;
        }else if (position == uiBeansList.size()) {
            return FOOT_VIEW;
        }else if (uiBeansList.get(position).getUiType() == HomeUIBeans.BANNER_VIEW) {
            return HEAD_VIEW;
        }  else {
            return uiBeansList.get(position).getUiType();
        }
    }


   public static class NormalViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,View.OnLongClickListener{

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(this, view, getLayoutPosition());
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
    }

   public static class TwoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,View.OnLongClickListener{

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(this, view, getLayoutPosition());
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
    }

   public static class FourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener,View.OnLongClickListener{

        public FourViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(this,view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onLongItemClick(this,view, getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
