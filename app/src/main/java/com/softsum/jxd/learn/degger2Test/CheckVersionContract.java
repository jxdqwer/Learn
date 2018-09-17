package com.softsum.jxd.learn.degger2Test;

import cn.softsum.base.BaseModel;
import cn.softsum.base.BaseView;

/**
 * @author jxd
 * @date 2018/9/17-16:50
 * @blog www.softsum.cn
 */
public class CheckVersionContract {
    interface ICheckVersionModel extends BaseModel{

        void checkVersion(String url,String cul);
    }

    interface ICheckVersionView extends BaseView{

        void showVersion();

        void showLoading();

        void hideLoading();

    }

    interface OnCheckVersion {

        void getRemoteVersionSuccess(String data);

        void getRemoteVersionFailed();
    }
}
