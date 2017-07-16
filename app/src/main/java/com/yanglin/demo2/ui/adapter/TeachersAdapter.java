package com.yanglin.demo2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanglin.demo2.R;
import com.yanglin.demo2.bean.TeacherInfoBean;

import java.util.List;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 23:01.
 * 描述   老师列表的适配器
 */

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.TeacherHolder> {
    private final List<TeacherInfoBean> mDatas;
    private Context mContext;

    public TeachersAdapter(Context context, List<TeacherInfoBean> infoBeens) {
        mContext = context;
        mDatas = infoBeens;
    }

    @Override
    public TeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeacherHolder(View.inflate(mContext, R.layout.recycle_list,null));
    }

    @Override
    public void onBindViewHolder(TeacherHolder holder, int position) {
            holder.mImageView.setImageResource(R.mipmap.teacher_card);
            holder.mTextView.setText("介绍" + position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class TeacherHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextView;

        public TeacherHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
