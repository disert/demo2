package com.yanglin.demo2.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanglin.demo2.Constants;
import com.yanglin.demo2.MainActivity;
import com.yanglin.demo2.R;
import com.yanglin.demo2.base.BaseActivity;
import com.yanglin.demo2.bean.SplashData;
import com.yanglin.demo2.utils.FileUtils;
import com.yanglin.demo2.utils.GsonHelper;
import com.yanglin.demo2.utils.ShareUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 15:39.
 * 描述   启动图
 *  启动图的逻辑是这样的
 *
 *  1.加载启动图的信息
 *      有启动图信息,通过信息加载图片,并为图片设置点击事件,根据信息跳转到指定的页面
 *      没有启动图信息,显示默认图片,没有点击效果
 *  2.每次进入到主页面加载一次启动图信息,并保存到本地,方便下次启动时调用
 *
 */
public class SplashActivity extends BaseActivity {


    private static final long SPLASH_DISPLAY_LENGHT = 3000;
    @BindView(R.id.splash_image)
    ImageView mSplashImage;
    @BindView(R.id.splash_next)
    TextView mSplashNext;
    @BindView(R.id.splash_root)
    RelativeLayout mSplashRoot;

    @Override
    public int getResId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSplashNext.setVisibility(View.VISIBLE);
        mSplashNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,MainActivity.class));
                finish();
            }
        });

        loadImg();
        initAnimation();
    }

    private void loadImg() {
        //加载本地splash数据
        String splashInfo = ShareUtils.getShare(Constants.SPLASH_INFO, "");
        if(!TextUtils.isEmpty(splashInfo)) {
            Bitmap bitmap = null;
            try {
                //解析成对象
                SplashData data = GsonHelper.parseObject(splashInfo, SplashData.class);

                File imgPath = new File(FileUtils.getCacheDir(Constants.SPLASH_PATH), data.getImgName());
                //加载图片
                if (imgPath.exists()) {
                    String splashPath = imgPath.getAbsolutePath();
                    bitmap = BitmapFactory.decodeFile(splashPath);
                }
                //设置图片,及事件
                if (null != bitmap) {
                    mSplashImage.setImageBitmap(bitmap);
                    mSplashImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO 点击图片跳转到对应的页面
                            startActivity(new Intent(mContext,MainActivity.class));
                            finish();
                        }
                    });
                }else {
//                    mSplashImage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(mContext,MainActivity.class));
//                            finish();
//                        }
//                    });
                }

            }catch (Exception e){

            }
        }


    }

    private void initAnimation() {
        //初始化动画
        Animation animation = AnimationUtils.loadAnimation(this, R.animator.splash_in);
        animation.setDuration(SPLASH_DISPLAY_LENGHT);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                //动画开始
                Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            subscriber.onNext(3);
                            Thread.sleep(1000);
                            subscriber.onNext(2);
                            Thread.sleep(1000);
                            subscriber.onNext(1);
                            Thread.sleep(1000);
                            subscriber.onCompleted();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                Intent mainIntent = new Intent(mContext,
                                        MainActivity.class);
                                mContext.startActivity(mainIntent);
                                mContext.finish();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                mSplashNext.setText(integer-- + " 跳过");
                            }
                        });
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重播
            }
        });


        findViewById(R.id.splash_root).setAnimation(animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
