package com.yanglin.demo2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanglin.demo2.GlobalContext;
import com.yanglin.demo2.R;
import com.yanglin.demo2.base.BaseActivity;
import com.yanglin.demo2.bean.StudyInfoBean;
import com.yanglin.demo2.bean.TeacherInfoBean;
import com.yanglin.demo2.ui.adapter.StudyAdapter;
import com.yanglin.demo2.ui.adapter.TeachersAdapter;
import com.yanglin.demo2.ui.other.SpaceItemDecoration;
import com.yanglin.demo2.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 14:28.
 * 描述   学习发布
 */
public class StudyActivity extends BaseActivity {


    private static final String IS_TEACHER = "isTeacher";
    private boolean isTeacher;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;

    @Override
    public int getResId() {
        return R.layout.activity_study;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSrlRefresh.setRefreshing(false);
            }
        });
        loadData();
    }

    private void loadData() {
        isTeacher = getIntent().getBooleanExtra(IS_TEACHER,false);
        if(isTeacher) {
            //模拟加载数据
            List<TeacherInfoBean> teachers = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                teachers.add(new TeacherInfoBean());
            }
            mRvList.setAdapter(new TeachersAdapter(mContext, teachers));
        }else {
            //模拟加载数据
            List<StudyInfoBean> studies = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                studies.add(new StudyInfoBean());
            }
            mRvList.setAdapter(new StudyAdapter(mContext, studies));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.addItemDecoration(new SpaceItemDecoration(15));
        UiUtils.configRecycleView(mRvList,linearLayoutManager );


    }


    @OnClick({R.id.iv_right, R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                //TODO 开启搜索
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }


    public static Intent newIntent(boolean studyOrTeacher){
        Intent intent = new Intent(GlobalContext.getContext(),StudyActivity.class);
        intent.putExtra(IS_TEACHER,studyOrTeacher);
        return intent;
    }
}
