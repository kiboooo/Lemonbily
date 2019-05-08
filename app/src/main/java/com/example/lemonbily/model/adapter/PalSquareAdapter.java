package com.example.lemonbily.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basemodule.bean.PalSquareBean;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.lemonbily.R;

public class PalSquareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOT_VIEW = 333333;
    private static final int NORMAL_VIEW = 444444;

    private Context mContext;
    private static onRecyclerViewItemClickListener itemClickListener;

    public PalSquareAdapter(Context mContext) {
        this.mContext = mContext;
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
           return new HomeAdapter.FootViewHolder(view);
        }
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pal_square_normal_item, viewGroup, false);
        return new PalSquareViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PalSquareViewHolder) {
            if (PalSquareUtils.palSquareBeans != null && PalSquareUtils.palSquareBeans.size() > 0) {
                PalSquareBean psb = PalSquareUtils.palSquareBeans.get(i);
                if (psb != null) {
                    PalSquareViewHolder vh = (PalSquareViewHolder) viewHolder;
                    vh.likeBtn.setSelected(psb.isLike());
                    if (psb.getAccount().getAid().equals(LoginStatusUtils.mLogin.getId())) {
                        vh.attentionBtn.setVisibility(View.INVISIBLE);
                    } else {
                        vh.attentionBtn.setVisibility(View.VISIBLE);
                        bindAttention(vh.attentionBtn, psb.isAttention());
                    }
                    vh.name.setText(psb.getAccount().getAname());
                    vh.genderIcon.setSelected(psb.getAccount().getAsex() != null
                            && psb.getAccount().getAsex().equals("w"));
                    vh.content.setText(psb.getPalcircle().getPalcontent());
                    Glide.with(mContext)
                            .load(NetWorkServer.SERVER_URL
                                    + psb.getAccount().getAavatar())
                            .apply(CommonUtils.avatarRequestOption())
                            .into(vh.avatar);
                }
            }
        }

        if (viewHolder instanceof FootViewHolder) {

        }
    }

    private void bindAttention(TextView attentionBtn, boolean attention) {
        if (attention) {
            attentionBtn.setText("已关注");
            attentionBtn.setSelected(true);
        }else {
            attentionBtn.setText("+关注");
            attentionBtn.setSelected(false);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT_VIEW;
        } else {
            return NORMAL_VIEW;
        }
    }

    public void addItem(int position, PalSquareBean palSquareBean) {
        PalSquareUtils.palSquareBeans.add(position,palSquareBean );
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        PalSquareUtils.palSquareBeans.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        if (PalSquareUtils.palSquareBeans == null) {
            return 0;
        }
        return PalSquareUtils.palSquareBeans.size() + 1;
    }

    public void updateDataList() {
        notifyDataSetChanged();
    }

    public PalSquareBean getPalSquareBean(int position) {
        if (position < 0) {
            return null;
        }
        return PalSquareUtils.palSquareBeans.get(position);
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
            likeBtn = itemView.findViewById(R.id.square_detail_like_icon);
            commentBtn = itemView.findViewById(R.id.square_detail_conment_icon);

            avatar.setOnClickListener(this);
            attentionBtn.setOnClickListener(this);
            likeBtn.setOnClickListener(this);
            commentBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                if (view.getId() == R.id.square_detail_like_icon) {
                    if (PalSquareUtils.palSquareBeans != null) {
                        int i ;
                        if (!likeBtn.isSelected()) {
                            likeBtn.setSelected(true);
                            PalSquareUtils.palSquareBeans.get(getAdapterPosition()).setLike(true);
                            i = 1;
                        }else {
                            likeBtn.setSelected(false);
                            PalSquareUtils.palSquareBeans.get(getAdapterPosition()).setLike(false);
                            i = -1;
                        }
                        PalSquareUtils.palSquareBeans.get(getAdapterPosition())
                                .getPalcircle().setPallicknum(
                                PalSquareUtils.palSquareBeans.get(getAdapterPosition())
                                        .getPalcircle().getPallicknum() + i);
                    }
                }
                if (view.getId() == R.id.square_attention) {
                    if (PalSquareUtils.palSquareBeans != null) {
                        if (!attentionBtn.isSelected()) {
                            attentionBtn.setText("已关注");
                            attentionBtn.setSelected(true);
                            attentionOperation(PalSquareUtils.palSquareBeans
                                    .get(getAdapterPosition()).getAccount().getAid(), true);
                        }else {
                            attentionBtn.setText("+关注");
                            attentionBtn.setSelected(false);
                            attentionOperation(PalSquareUtils.palSquareBeans
                                    .get(getAdapterPosition()).getAccount().getAid(), false);
                        }
                    }
                }
                itemClickListener.onItemClick(this, view, getAdapterPosition());
            }
        }
        private void attentionOperation(int uid, boolean state) {
            if (PalSquareUtils.palSquareBeans != null) {
                for (int i = 0; i < PalSquareUtils.palSquareBeans.size(); i++) {
                    if (PalSquareUtils.palSquareBeans.get(i).getAccount().getAid() == uid) {
                        PalSquareUtils.palSquareBeans.get(i).setAttention(state);
                    }
                }
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
