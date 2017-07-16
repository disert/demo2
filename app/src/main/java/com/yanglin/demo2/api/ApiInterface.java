package com.yanglin.demo2.api;

import com.yanglin.demo2.bean.DemoJsonBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 作者 Administrator
 * 时间   2017/7/12 0012 18:30.
 * 描述   ${TODO}
 */

public interface ApiInterface {
    @GET("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=美女&bk_length=600")
    Observable<DemoJsonBean> demojson();
}
