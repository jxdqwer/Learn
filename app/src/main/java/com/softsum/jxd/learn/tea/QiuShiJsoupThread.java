package com.softsum.jxd.learn.tea;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.softsum.jxd.learn.kanmeiziRecyc.MeiziBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class QiuShiJsoupThread {
    OnFinishListener callback;
    private MyHandler myHandler;
    public static class MyHandler extends Handler {
        private static Fragment fragment;
        private static  int  index =0;
        private WeakReference<Fragment> reference;
        TeaRecycleAdapter mAdapter;

        public MyHandler(Fragment fragment , TeaRecycleAdapter adapter) {
            reference = new WeakReference<Fragment>(fragment);
            mAdapter = adapter;
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                switch (msg.what) {
                    case 0x1625:
                        ArrayList data = msg.getData().getParcelableArrayList("data");
                        assert data != null;
                        TeaBean bean = (TeaBean) data.get(0);
                        index = ImgRequestHelp.getInstance(fragment.getActivity()).getImage(bean.getAuthorImgUrl(),
                                new ImgRequestHelp.OnFinishListener() {
                                    @Override
                                    public void onFinish(boolean success ,int what, Bitmap bitmap){
                                        if (what == index){
                                            bean.setAuthorImg(bitmap);
                                            mAdapter.addItem(0,bean);
                                        }
                                    }
                                });

                        Log.d(TAG, bean.getTitle());
                        break;
                    default:
                        // do something...
                        break;
                }
            }
        }
    }

    public QiuShiJsoupThread(Fragment fragment,TeaRecycleAdapter adapter ,OnFinishListener onFinishListener){
        callback = onFinishListener;
        MyHandler.fragment = fragment;
        myHandler = new MyHandler(fragment, adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        Document doc_top = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").userAgent("Chrome")
                                .timeout(3000)
                                .post();
                        Elements els = doc_top.select("a.contentHerf");

                        for (int i = 0; i < els.size(); i++) {
                            TeaBean bean = new TeaBean();
                            //标题
                            Element el = els.get(i);
                            bean.setTitle(el.text());
                            //获取每个项目链接
                            String href = el.attr("href");
                            try {
                                //让他们的服务器休息一会
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Document doc = Jsoup.connect("http://www.qiushibaike.com" + href).get();
                            bean.setUrl("http://www.qiushibaike.com" + href);
                            //内容
                            Elements text_detail = doc.select(".content");
                            bean.setText(text_detail.text());

                            Elements auth_detail = doc.select("div.author.clearfix");
                            Elements select = auth_detail.select("a h2");
                            bean.setAuthor(select.text());
                            Elements select2 = auth_detail.select("a img");
                            bean.setAuthorImgUrl("http:" + select2.attr("src"));

                            Elements els_pic = doc.select(".thumb img[src$=jpg]");
                            if (!els_pic.isEmpty()) {
                                String pic = els_pic.attr("src");
                                bean.setImageUrl("http:" + pic);
                            } else {
                                bean.setImageUrl("");
                            }

                            myHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Message msg = new Message();
                                    Bundle b = new Bundle();
                                    ArrayList arr = new ArrayList();
                                    arr.add(bean);
                                    b.putStringArrayList("data",arr);
                                    msg.what = 0x1625;
                                    msg.setData(b);
                                    myHandler.sendMessage(msg);
                                    Log.d(TAG, "run: I am a thread");
                                }
                            });

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
    }
    public interface OnFinishListener {
        public void onFinish(TeaBean beans);
    }


}
