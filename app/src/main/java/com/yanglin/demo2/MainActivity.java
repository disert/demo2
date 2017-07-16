package com.yanglin.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanglin.demo2.api.ApiUtils;
import com.yanglin.demo2.base.BaseActivity;
import com.yanglin.demo2.bean.DemoJsonBean;
import com.yanglin.demo2.ui.StudyActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

import static android.support.v4.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

public class MainActivity extends BaseActivity {


    @BindView(R.id.dl_root)
    DrawerLayout mDlRoot;
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
    @BindView(R.id.cv_identity1)
    CardView mCvIdentity1;
    @BindView(R.id.cv_identity2)
    CardView mCvIdentity2;
    @BindView(R.id.nv_left)
    NavigationView mNvLeft;
    @BindView(R.id.nv_right)
    NavigationView mNvRight;


    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        init();
    }

    private void init() {
        mDlRoot.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);
        ApiUtils.request(ApiUtils.getApiInterface().demojson(), new Subscriber<DemoJsonBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DemoJsonBean demoJsonBean) {

            }
        });
    }


    @OnClick({R.id.iv_right, R.id.iv_left,R.id.cv_identity1, R.id.cv_identity2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                mDlRoot.openDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_left:
                mDlRoot.openDrawer(Gravity.LEFT);
                break;
            case R.id.cv_identity1:
                startActivity(StudyActivity.newIntent(true));
                break;
            case R.id.cv_identity2:
                startActivity(StudyActivity.newIntent(false));
                break;
        }
    }


}
