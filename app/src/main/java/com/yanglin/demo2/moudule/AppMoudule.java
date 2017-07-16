package com.yanglin.demo2.moudule;

import com.yanglin.demo2.api.ApiInterface;
import com.yanglin.demo2.api.LoggingInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yanglin.demo2.Constants.BASE_URL;

/**
 * 作者 Administrator
 * 时间   2017/7/14 0014 12:31.
 * 描述   负责提供依赖的模块
 */
@Module
public class AppMoudule {

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        return client;
    }
    @Provides
    ApiInterface provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }
}
