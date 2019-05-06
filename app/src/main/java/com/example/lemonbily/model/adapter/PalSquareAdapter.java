package com.example.lemonbily.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lemonbily.R;
import com.example.basemodule.bean.PalSquareBean;

import java.util.List;

public class PalSquareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOT_VIEW = 333333;
    private static final int NORMAL_VIEW = 444444;

    private Context mContext;
    private List<PalSquareBean> palSquareBeans;
    private static onRecyclerViewItemClickListener itemClickListener;

    public PalSquareAdapter(Context mContext, List<PalSquareBean> palSquareBeans) {
        this.mContext = mContext;
        this.palSquareBeans = palSquareBeans;
    }

    public static void setItemClickListener(onRecyclerViewItemClickListener itemClickListener) {
        PalSquareAdapter.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == FOOT_VIEW) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_home_foot_view, viewGroup, false);
            new HomeAdapter.FootViewHolder(view);
        }
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pal_square_normal_item, viewGroup, false);
        return new PalSquareViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT_VIEW;
        } else {
            return NORMAL_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return palSquareBeans.size() + 1;
    }

    public static class PalSquareViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {


        ImageView avatar;
        TextView name;
        ImageView genderIcon;
        TextView attentionBtn;
        TextView content;
        ImageView likeBtn;
        ImageView commentBtn;

        PalSquareViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.square_avatar);
            name = itemView.findViewById(R.id.square_name);
            genderIcon = itemView.findViewById(R.id.square_gender);
            attentionBtn = itemView.findViewById(R.id.square_attention);
            content = itemView.findViewById(R.id.square_content);
            likeBtn = itemView.findViewById(R.id.square_like_icon);
            commentBtn = itemView.findViewById(R.id.square_conment_icon);

            avatar.setOnClickListener(this);
            attentionBtn.setOnClickListener(this);
            likeBtn.setOnClickListener(this);
            commentBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                if (view.getId() == R.id.square_like_icon) {
                    if (!likeBtn.isSelected()) {
                        likeBtn.setSelected(true);
                    }else {
                        likeBtn.setSelected(false);
                    }
                }
                if (view.getId() == R.id.square_attention) {
                    if (!attentionBtn.isSelected()) {
                        attentionBtn.setText("已关注");
                        attentionBtn.setSelected(true);
                    }else {
                        attentionBtn.setText("+关注");
                        attentionBtn.setSelected(false);
                    }
                }
                itemClickListener.onItemClick(this, view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onLongItemClick(this, view, getAdapterPosition());
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
