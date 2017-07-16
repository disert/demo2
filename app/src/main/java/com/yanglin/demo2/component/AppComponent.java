package com.yanglin.demo2.component;

/**
 * 作者 Administrator
 * 时间   2017/7/14 0014 12:57.
 * 描述   注入之间的桥梁
 */

import com.yanglin.demo2.GlobalContext;
import com.yanglin.demo2.MainActivity;
import com.yanglin.demo2.moudule.AppMoudule;

import dagger.Component;

@Component(modules = {AppMoudule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(GlobalContext context);
}
