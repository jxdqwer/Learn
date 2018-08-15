package com.softsum.jxd.learn.kanmeiziRecyc;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

public class UrlImage {
    OnFinshListerener callback;
    private volatile static UrlImage mUrlImage;
    private  UrlImage(Context context){
        NoHttp.initialize(context);
    }

    public interface OnFinshListerener {
        public void onFinish(boolean sucess ,int what, Bitmap bitmap);
    }

    public static UrlImage getInstance(Context context){
        if (mUrlImage == null){
            synchronized (UrlImage.class){
                if (mUrlImage == null){
                    mUrlImage = new UrlImage(context);
                }
            }
        }
        return  mUrlImage;
    }

    public void getImage(String url,int id,OnFinshListerener listerener){
        callback = listerener;
        RequestQueue queue = NoHttp.newRequestQueue();
        Request<Bitmap> request = NoHttp.createImageRequest(url, RequestMethod.GET);
        queue.add(id, request, new OnResponseListener<Bitmap>() {
            boolean sucess = false;
            Bitmap imgaer;
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<Bitmap> response) {
                sucess = true;
                imgaer = response.get();
                callback.onFinish(false,what,imgaer);
            }
            @Override
            public void onFailed(int what, Response<Bitmap> response) {
                sucess = false;
                callback.onFinish(false,what,imgaer);
            }
            @Override
            public void onFinish(int what) {

            }
        });
    }
}
