package com.softsum.jxd.learn.degger2Test.di;

import com.softsum.jxd.learn.degger2Test.CheckVersionContract;
import com.softsum.jxd.learn.degger2Test.CheckVersionModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author jxd
 * @date 2018/9/17-16:48
 * @blog www.softsum.cn
 */
@Module
public class CheckVersionModule {

    private CheckVersionContract.IView mILoginView;

    public Login1Module(CheckVersionContract.ILoginView ILoginView) {
        mILoginView = ILoginView;
    }

    @Provides
    CheckVersionContract.ILoginView getView() {
        return mILoginView;
    }

    @Provides
    CheckVersionContract.ILoginModel getModel(CheckVersionModel model) {
        return model;
    }
}
