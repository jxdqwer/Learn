package com.softsum.jxd.learn.tea;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

public class QiuShiJsoupThread {

    private MyHandler myHandler;

    public static class MyHandler extends Handler {
        private WeakReference<Fragment> reference;
        public MyHandler(Fragment fragment) {
            reference = new WeakReference<Fragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                switch (msg.what) {
                    case 123:
                        Log.d(TAG, "handleMessage: 123");
                        break;
                    default:
                        // do something...
                        break;
                }
            }
        }
    }


    public QiuShiJsoupThread(Fragment fragment){
        myHandler = new MyHandler(fragment);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    try {
                        Document mozilla = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").userAgent("Mozilla")
                                .timeout(3000)
                                .post();

                        Elements select1 = mozilla.select("div.author.clearfix");
                        for (Element element : select1) {
                            Document parse = Jsoup.parse(element.toString());
                            Elements select = parse.select("a h2");
                            Elements select2 = parse.select("a img");
                            Log.d("QiuShiJsoupThread","element 名字："+select.text());
                            Log.d("QiuShiJsoupThread","element 头像："+select2.attr("src"));
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Thread.sleep(1000);//在子线程有一段耗时操作,比如请求网络
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            myHandler.sendEmptyMessage(123);
                            Log.d(TAG, "run: I am a thread");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public interface OnFinshListerener {
        public void onFinish(boolean sucess ,int what);
    }


}
