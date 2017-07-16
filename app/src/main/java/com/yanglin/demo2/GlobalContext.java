package com.yanglin.demo2;

import android.app.Application;

import com.yanglin.demo2.api.ApiInterface;
import com.yanglin.demo2.component.AppComponent;
import com.yanglin.demo2.component.DaggerAppComponent;
import com.yanglin.demo2.moudule.AppMoudule;

import javax.inject.Inject;


public class GlobalContext extends Application {

	private static GlobalContext 	mContext;
	private AppComponent mAppComponent;
	@Inject
	ApiInterface mApiInterface;

	public static GlobalContext getContext(){
		return mContext;
	}


	// 应用程序的入口
	@Override
	public void onCreate(){
		super.onCreate();
		// 上下文
		mContext = this;
		//初始化dagger的依赖注入
		initInjector();


	}


	private void initInjector() {
		mAppComponent = DaggerAppComponent.builder()
				.appMoudule(new AppMoudule())
				.build();
		mAppComponent.inject(this);
	}
	public ApiInterface getApiInterface() {
		return mApiInterface;
	}

	public AppComponent getAppComponent() {
		return mAppComponent;
	}

}
