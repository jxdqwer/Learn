package com.softsum.jxd.learn.dagger2Test.di;

import com.softsum.jxd.learn.dagger2Test.DaggerTestActivity;

import dagger.Component;

/**
 * @author jxd
 * @date 2018/9/17-16:48
 * @blog www.softsum.cn
 */
@Component(modules = CheckVersionModule.class)
public interface CheckVersionComponent {
    void inject(DaggerTestActivity daggerTestActivity);
}