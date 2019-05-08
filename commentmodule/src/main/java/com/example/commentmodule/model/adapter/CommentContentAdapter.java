package com.example.commentmodule.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basemodule.bean.CommentUIBean;
import com.example.basemodule.utils.PalSquareUtils;
import com.example.commentmodule.R;

import java.util.ArrayList;
import java.util.List;

public class CommentContentAdapter extends RecyclerView.Adapter<CommentContentAdapter.CommentContentViewHoder> {

    private int curPosition;


    public CommentContentAdapter() {
    }


    public CommentContentAdapter(int curPosition) {
        this.curPosition = curPosition;
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
       List<CommentUIBean> commentUIBeanList
               = PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans();
        if (commentUIBeanList != null && commentUIBeanList.size() > 0) {
            vh.comName.setText(commentUIBeanList.get(i).getmAccount().getAname());
            vh.comContent.setText(commentUIBeanList.get(i).getmComment().getComcontent());
        }
    }

    @Override
    public int getItemCount() {
        return PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans() == null ?
                0 : PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans().size();
    }

    public void addItem(int position, CommentUIBean commentUIBean) {
        if (PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans() == null) {
            PalSquareUtils.palSquareBeans.get(curPosition).setCommentUIBeans(new ArrayList<CommentUIBean>());
        }
        PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans().add(position,commentUIBean );
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        if (PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans() != null) {
            PalSquareUtils.palSquareBeans.get(curPosition).getCommentUIBeans().remove(position);
            notifyItemRemoved(position);
        }
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
