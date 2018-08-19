package com.softsum.jxd.learn;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.softsum.jxd.learn.kanmeiziRecyc.MeiziAdapter;
import com.softsum.jxd.learn.kanmeiziRecyc.MeiziBean;
import com.softsum.jxd.learn.kanmeiziRecyc.UrlImage;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KanmeiziRecyclerview extends AppCompatActivity {

    private MeiziAdapter meiziAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NoHttp.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanmeizi_recyclerview);

//        RecyclerView
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.meizi_recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        meiziAdapter = new MeiziAdapter(recyclerView);
        recyclerView.setAdapter(meiziAdapter);

        initMeizi();
    }

    private void initMeizi(){
        //妹子图片url
        //String url = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1";
        String url = "http://wallpaper.apc.360.cn/index.php?c=WallPaper&a=getAppsByOrder&order=create_time&start=0&count=20&from=360chrome";
        RequestQueue queue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        queue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                loadRescource(getMeiziUrlList(response.get()));
            }
            @Override
            public void onFailed(int what, Response<String> response) {
            }
            @Override
            public void onFinish(int what) {
            }
        });

            //meiziBeans.add(item);
    }
    private List<String> getMeiziUrlList(String json) {
        //妹子api解析
//        List<String> urlList = new ArrayList<>();
//        try {
//            JSONObject object = new JSONObject(json);
//            JSONArray array = object.getJSONArray("results");
//            for (int i = 0; i < array.length() ; i++) {
//                JSONObject sub = array.getJSONObject(i);
//                 urlList.add(sub.getString("url"));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //壁纸api解析
        List<String> urlList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length() ; i++) {
                JSONObject sub = array.getJSONObject(i);
                 urlList.add(sub.getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return urlList;
    }

    private void loadRescource(List<String> list){
        for (int i = 0; i < list.size() ; i++) {
            UrlImage.getInstance(this).getImage(list.get(i),i,
                    new UrlImage.OnFinshListerener() {
                        @Override
                        public void onFinish(boolean sucess ,int what, Bitmap bitmap){
                            MeiziBean bean = new MeiziBean(String.valueOf(what),bitmap);
                            meiziAdapter.addItem(what,bean);
                        }
                    });

        }
    }
}
