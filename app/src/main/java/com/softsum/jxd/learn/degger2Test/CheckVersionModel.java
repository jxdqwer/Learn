package com.softsum.jxd.learn.degger2Test;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * @author jxd
 * @date 2018/9/17-16:50
 * @blog www.softsum.cn
 */
public class CheckVersionModel implements CheckVersionContract.ICheckVersionModel{
    @Inject
    public CheckVersionModel(){

    }

    @Override
    public void checkVersion(Handler handler, String url, String cul, CheckVersionContract.OnCheckVersionListener checkVersionListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = NoHttp.newRequestQueue();
                Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
                queue.add(0, request, new OnResponseListener<String>() {

                            @Override
                            public void onStart(int what) {

                            }

                            @Override
                            public void onSucceed(int what, Response<String> response) {
                                Log.d(TAG, response.get());
                                checkVersionListener.getRemoteVersionSuccess(response.get());
                            }

                            @Override
                            public void onFailed(int what, Response<String> response) {
                                checkVersionListener.getRemoteVersionFailed();
                            }

                            @Override
                            public void onFinish(int what) {

                            }
                        });
            }
        }).start();
    }

    @Override
    public void onDestroy() {

    }

}
