package com.softsum.jxd.learn.dagger2Test;

import android.os.Handler;

import cn.softsum.base.BaseModel;
import cn.softsum.base.BaseView;

/**
 * @author jxd
 * @date 2018/9/17-16:50
 * @blog www.softsum.cn
 */
public interface CheckVersionContract {
    public interface ICheckVersionModel extends BaseModel{

        void checkVersion(Handler handler,String url, String currVersion,OnCheckVersionListener checkVersionListener);
    }

    interface ICheckVersionView extends BaseView{

        void showVersion(String version);

        void showLoading();

        void hideLoading();

        String getSerUrl();

    }

    interface OnCheckVersionListener {

        void getRemoteVersionSuccess(String data);

        void getRemoteVersionFailed();
    }
}
