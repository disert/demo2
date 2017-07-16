package com.yanglin.demo2.utils;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2015/11/11.
 * 缓存的工具类
 */
public class CacheConfigUtils {


    public static String getCache(String key) {
        return ShareUtils.getShare(key, "");
    }

    /**
     * 该方法比较耗时,放在子线程中进行
     *
     * @param key
     * @param value
     */
    public static void putCache(final String key, final String value) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                long start = System.currentTimeMillis();
                ShareUtils.putShare(key, value);
                DebugLog.d("put:" + (start - System.currentTimeMillis()));
                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                    }
                });
    }

    public static void clearCache() {
        ShareUtils.clear();
    }

}
