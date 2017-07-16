package com.yanglin.demo2.api;

import rx.Subscriber;

/**
 * 作者 Administrator
 * 时间   2017/7/13 0013 16:03.
 * 描述   当请求时需要显示进度效果的时候的一个订阅者
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    private OnSuccess<T> mOnSuccess;

    public ProgressSubscriber(OnSuccess<T> onSuccess) {
        mOnSuccess = onSuccess;
    }

    @Override
    public void onStart() {
        super.onStart();
        //TODO 启动进度条
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T o) {
        //TODO 关闭进度条

        mOnSuccess.success(o);
    }

    public interface OnSuccess<T>{
        void success(T t);
    }
}
