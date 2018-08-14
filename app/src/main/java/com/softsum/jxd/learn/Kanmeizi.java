package com.softsum.jxd.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.nohttp.*;
import com.yanzhenjie.nohttp.rest.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Kanmeizi extends AppCompatActivity {

    private TextView textShow;
    private ImageView meiziImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NoHttp.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanmeizi);
        textShow = (TextView) findViewById(R.id.respond);
        meiziImage = (ImageView) findViewById(R.id.meizi_image);
        httpRequest();
    }

    protected void httpRequest(){
        String url = "http://gank.io/api/today";
        RequestQueue queue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        queue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                Log.d("httpRequest", "onStart: ");
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.d("httpRequest", "onSucceed: ");
                String result = response.get();
                String url = analysisUrl(result);
                textShow.setText(url);
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                Log.d("httpRequest", "onFailed: ");
            }
            @Override
            public void onFinish(int what) {
                Log.d("httpRequest", "onFinish: ");
            }
        });
    }

    protected String analysisUrl(String data){
        String url = "";
        try {
        JSONObject object = new JSONObject(data);
        JSONObject resultsObject = object.getJSONObject("results");
        JSONArray fuliArray = resultsObject.getJSONArray("福利");
        for (int i = 0; i < fuliArray.length() ; i++) {
            JSONObject sub = fuliArray.getJSONObject(i);
            url = sub.getString("url");
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(url.isEmpty()){
            url = "empty url";
        }
        return url;
    }
}
