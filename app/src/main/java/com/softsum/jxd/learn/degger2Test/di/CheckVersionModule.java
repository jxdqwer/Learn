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

    private CheckVersionContract.ICheckVersionView mICheckVersionView;

    public CheckVersionModule(CheckVersionContract.ICheckVersionView ICheckVersionView) {
        mICheckVersionView = ICheckVersionView;
    }

    @Provides
    CheckVersionContract.ICheckVersionView getView() {
        return mICheckVersionView;
    }

    @Provides
    CheckVersionContract.ICheckVersionModel getModel(CheckVersionModel model) {
        return model;
    }
}
