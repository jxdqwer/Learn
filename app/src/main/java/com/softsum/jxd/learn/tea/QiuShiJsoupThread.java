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
                        Document doc = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").userAgent("Mozilla")
                                .timeout(3000)
                                .post();
                        Elements els = doc.select("a.contentHerf");
                        Log.i("一、HTML內容", els.toString());

                        for (int i = 0; i < els.size(); i++) {
                            Element el = els.get(i);
                            Log.i("1.标题", el.text());

                            String href = el.attr("href");
                            Log.i("2.链接", href);

                            Document doc_detail = Jsoup.connect("http://www.qiushibaike.com" + href).get();

                            Elements els_detail = doc_detail.select(".content");
                            Log.i("3.內容", els_detail.text());

                            Elements auth_detail = doc_detail.select("div.author.clearfix");
                            Elements select = auth_detail.select("a h2");
                            Log.i(TAG,"5、名字："+select.text());
                            Elements select2 = auth_detail.select("a img");
                            Log.i(TAG,"6、头像链接："+select2.attr("src"));

                            Elements els_pic = doc_detail.select(".thumb img[src$=jpg]");
                            if (!els_pic.isEmpty()) {
                                String pic = els_pic.attr("src");
                                Log.i("4.图片连接", "" + pic);
                            } else {
                                Log.i("4.图片连接", "无");
                            }
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
