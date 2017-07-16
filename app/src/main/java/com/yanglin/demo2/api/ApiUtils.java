package com.yanglin.demo2.api;

import com.yanglin.demo2.GlobalContext;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 作者 Administrator
 * 时间   2017/7/12 0012 18:13.
 * 描述   ${TODO}
 */

public class ApiUtils {

    public static ApiInterface getApiInterface(){
        return GlobalContext.getContext().getApiInterface();
    }

    /**
     * 用于发送http请求的方法,该方法会返回一个没有去掉公共部分的bean对象
     * @param observable
     * @param subscriber
     * @param <T>
     */
    public static  <T> void request(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
    /**
     * 用于发送http请求的方法,该方法会返回一个去掉公共部分的bean对象
     * 由于暂时没有公共部分,这个方法暂时还写不了
     * @param observable
     * @param subscriber
     * @param <T>
     */
    public <T> void requestData(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
