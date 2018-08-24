package com.softsum.jxd.learn.tea;

import android.content.Context;
import android.graphics.Bitmap;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

public class ImgRequestHelp {
    private int index = 10000;
    OnFinishListener callback;
    private volatile static ImgRequestHelp imgRequestHelp;
    private  ImgRequestHelp(Context context){
        NoHttp.initialize(context);
    }

    public interface OnFinishListener {
        public void onFinish(boolean success ,int what, Bitmap bitmap);
    }

    public static ImgRequestHelp getInstance(Context context){
        if (imgRequestHelp == null){
            synchronized (ImgRequestHelp.class){
                if (imgRequestHelp == null){
                    imgRequestHelp = new ImgRequestHelp(context);
                }
            }
        }
        return  imgRequestHelp;
    }

    public int getImage(String url,OnFinishListener listener){
        ++index;
        callback = listener;
        RequestQueue queue = NoHttp.newRequestQueue();
        Request<Bitmap> request = NoHttp.createImageRequest(url, RequestMethod.GET);
        queue.add(index, request, new OnResponseListener<Bitmap>() {
            boolean success = false;
            Bitmap image;
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<Bitmap> response) {
                success = true;
                image = response.get();
                callback.onFinish(false,what, image);
            }
            @Override
            public void onFailed(int what, Response<Bitmap> response) {
                success = false;
                callback.onFinish(false,what, image);
            }
            @Override
            public void onFinish(int what) {

            }
        });
        return index;
    }
}
