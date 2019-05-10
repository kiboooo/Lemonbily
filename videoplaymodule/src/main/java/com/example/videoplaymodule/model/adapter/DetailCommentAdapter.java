package com.example.videoplaymodule.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basemodule.bean.CommentUIBean;
import com.example.basemodule.net.NetWorkServer;
import com.example.basemodule.utils.CommonUtils;
import com.example.videoplaymodule.R;

import java.util.ArrayList;
import java.util.List;

public class DetailCommentAdapter
        extends RecyclerView.Adapter<DetailCommentAdapter.CommentViewHolder> {


    private Context mContext;
    List<CommentUIBean> uiBeanList;

    public DetailCommentAdapter(Context mContext, List<CommentUIBean> uiBeanList) {
        this.mContext = mContext;
        this.uiBeanList = uiBeanList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.videoplay_detail_comment_item, viewGroup, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder vh, int i) {
        if (uiBeanList != null) {
            CommentUIBean cuib = uiBeanList.get(i);
            if (cuib != null  && cuib.getmComment() != null) {
                String fool = String.valueOf((getItemCount() - i)) + " 楼";
                vh.floorNum.setText(fool);
                vh.time.setText(CommonUtils.simpleDateFormat.format(
                        cuib.getmComment().getComtime() == null ?
                                System.currentTimeMillis() : cuib.getmComment().getComtime()));
                vh.content.setText(cuib.getmComment().getComcontent());
                if (cuib.getmAccount() != null) {
                    vh.name.setText(cuib.getmAccount().getAname());
                    Glide.with(mContext)
                            .load(NetWorkServer.SERVER_URL
                                    + cuib.getmAccount().getAavatar())
                            .apply(CommonUtils.avatarRequestOption())
                            .into(vh.avatar);
                }
            }
        }
    }

    public void addItem(int position, CommentUIBean commentUIBean) {
        if (uiBeanList == null) {
            uiBeanList = new ArrayList<>();
            uiBeanList.add(commentUIBean);
        } else {
            uiBeanList.add(position, commentUIBean);
        }
        notifyItemInserted(position);

    }

    public void removeItem(int position) {
        if (uiBeanList != null) {
            uiBeanList.remove(position);
        }
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return uiBeanList == null ? 0 : uiBeanList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView floorNum;
        TextView name;
        TextView time;
        ImageView avatar;
        TextView content;

        CommentViewHolder(@NonNull View v) {
            super(v);
            floorNum = v.findViewById(R.id.detail_comment_floor_number);
            name = v.findViewById(R.id.detail_comment_name);
            time = v.findViewById(R.id.detail_comment_time);
            avatar = v.findViewById(R.id.detail_comment_avatar);
            content = v.findViewById(R.id.detail_comment_content);
        }
    }
}
