package com.softsum.jxd.learn.dagger2Test;

import android.os.Handler;
import android.util.Log;

import javax.inject.Inject;

import cn.softsum.base.BasePresenter;

import static android.content.ContentValues.TAG;

/**
 * @author jxd
 * @date 2018/9/17-17:19
 * @blog www.softsum.cn
 */
public class CheckVersionPresenter  extends BasePresenter<CheckVersionContract.ICheckVersionModel,CheckVersionContract.ICheckVersionView>{

    @Inject
    public CheckVersionPresenter(CheckVersionContract.ICheckVersionModel model, CheckVersionContract.ICheckVersionView view) {
        super(model, view);
    }

    private Handler mHandler = new Handler();

    public void getVersion(){
        mView.showLoading();
        String url = mView.getSerUrl();
        if(url.isEmpty()){
            url = "http://www.softsum.cn/xunfei/version.json";
        }
        mModel.checkVersion(mHandler, url, "1001", new CheckVersionContract.OnCheckVersionListener() {

            @Override
            public void getRemoteVersionSuccess(final String data) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoading();
                        mView.showVersion(data);
                        Log.d(TAG, "run: thread 2");
                    }
                });
            }

            @Override
            public void getRemoteVersionFailed() {
                mView.hideLoading();
                mView.showVersion("获取版本失败");
                
            }
        });
    }
}
