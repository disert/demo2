package com.yanglin.demo2.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanglin.demo2.R;
import com.yanglin.demo2.bean.StudyInfoBean;

import java.util.List;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 23:01.
 * 描述   学生想学列表的适配器
 */

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.TeacherHolder> {
    private final List<StudyInfoBean> mDatas;
    private Context mContext;

    public StudyAdapter(Context context, List<StudyInfoBean> infoBeens) {
        mContext = context;
        mDatas = infoBeens;
    }

    @Override
    public TeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeacherHolder(View.inflate(mContext, R.layout.recycle_list,null));
    }

    @Override
    public void onBindViewHolder(TeacherHolder holder, int position) {
            holder.mImageView.setImageResource(R.mipmap.study_task);
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
