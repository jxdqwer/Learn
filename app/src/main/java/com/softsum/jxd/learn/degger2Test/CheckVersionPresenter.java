package com.softsum.jxd.learn.degger2Test;

import javax.inject.Inject;

import cn.softsum.base.BasePresenter;

/**
 * @author jxd
 * @date 2018/9/17-17:19
 * @blog www.softsum.cn
 */
public class CheckVersionPresenter  extends BasePresenter
        <CheckVersionContract.ICheckVersionModel,CheckVersionContract.ICheckVersionView>{

    @Inject
    public CheckVersionPresenter(CheckVersionContract.ICheckVersionModel model, CheckVersionContract.ICheckVersionView view) {
        super(model, view);
    }

}
