package com.example.commentmodule.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basemodule.bean.CommentUIBean;
import com.example.commentmodule.R;

import java.util.List;

public class CommentContentAdapter extends RecyclerView.Adapter<CommentContentAdapter.CommentContentViewHoder> {

    private List<CommentUIBean> commentUIBeanList;

    public CommentContentAdapter() {
    }

    public CommentContentAdapter(List<CommentUIBean> commentUIBeanList) {
        this.commentUIBeanList = commentUIBeanList;
    }

    @NonNull
    @Override
    public CommentContentViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_detail_comment_item, viewGroup, false);
        return new CommentContentViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentContentViewHoder vh, int i) {
        if (commentUIBeanList != null && commentUIBeanList.size() > 0) {
            vh.comName.setText(commentUIBeanList.get(i).getmAccount().getAname());
            vh.comContent.setText(commentUIBeanList.get(i).getmComment().getComcontent());
        }
    }

    @Override
    public int getItemCount() {
        return commentUIBeanList == null ? 0 : commentUIBeanList.size();
    }

    public void addItem(int position, CommentUIBean commentUIBean) {
        commentUIBeanList.add(position,commentUIBean );
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        commentUIBeanList.remove(position);
        notifyItemRemoved(position);
    }

    static class CommentContentViewHoder extends RecyclerView.ViewHolder {
        TextView comName;
        TextView comContent;

        CommentContentViewHoder(@NonNull View itemView) {
            super(itemView);
            comName = itemView.findViewById(R.id.comment_user_name);
            comContent= itemView.findViewById(R.id.comment_user_content);
        }
    }

}
