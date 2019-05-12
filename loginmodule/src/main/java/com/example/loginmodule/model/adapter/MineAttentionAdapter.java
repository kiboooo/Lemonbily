package com.example.loginmodule.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basemodule.bean.Account;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.basemodule.utils.LoginStatusUtils;
import com.example.loginmodule.R;

import java.util.List;

public class MineAttentionAdapter
        extends RecyclerView.Adapter<MineAttentionAdapter.AttentionViewHolder> {

    private Context curContext ;
    private List<Account> curDatalist;

    public MineAttentionAdapter(List<Account> curDatalist) {
        this.curDatalist = curDatalist;
    }

    public void setCurDatalist(List<Account> curDatalist) {
        this.curDatalist = curDatalist;
    }

    @NonNull
    @Override
    public AttentionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        curContext = viewGroup.getContext();
        View view = LayoutInflater.from(curContext).inflate(
                R.layout.login_mine_attention_item_view, viewGroup, false);
        return new AttentionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttentionViewHolder vh, int i) {
        if (LoginStatusUtils.isLogin && curDatalist != null) {
            vh.name.setText(curDatalist.get(i).getAname());
            Glide.with(curContext)
                    .load(NetWorkServer.SERVER_URL
                            + curDatalist.get(i).getAavatar())
                    .apply(CommonUtils.avatarRequestOption())
                    .into(vh.avatar);
        }
    }

    @Override
    public int getItemCount() {
        return curDatalist == null ? 0 : curDatalist.size();
    }

    static class AttentionViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;

        public AttentionViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.attention_item_avatar);
            name = itemView.findViewById(R.id.attention_item_name);
        }
    }
}
