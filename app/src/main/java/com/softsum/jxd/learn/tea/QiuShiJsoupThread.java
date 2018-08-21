package com.softsum.jxd.learn.tea;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

public class QiuShiJsoupThread {
    private Handler mHandler;

    public QiuShiJsoupThread(){
        Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        Log.d(TAG, "run: I am a Handler");
                        break;
                }
            }
        };
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
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mHandler.sendEmptyMessage(123);
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
