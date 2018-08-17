package com.softsum.jxd.learn;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.softsum.jxd.learn.yaogan.Draw;
import com.softsum.jxd.learn.yaogan.ViewAttributes;

import java.util.ArrayList;
import java.util.List;

public class Yaogan1 extends AppCompatActivity {

    private List<MyItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaogan1);

        //ListView
//        ItemAdapter adapter = new ItemAdapter(MainActivity.this,
//                R.layout.recycle_son,itemList);
//        ListView listView = (ListView)findViewById(R.id.recycler_view);
//        listView.setAdapter(adapter);
        //RecyclerView
//                    initMyItem();
//                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
////        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//                    recyclerView.setLayoutManager(layoutManager);
//                    ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(itemList);
//                    recyclerView.setAdapter(itemRecyclerAdapter);

        ViewAttributes.main = this;
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //获取屏幕的宽高
            DisplayMetrics dis = getResources().getDisplayMetrics();
            ViewAttributes.w = dis.widthPixels;
            ViewAttributes.h = dis.heightPixels;
            Log.v("View Width:",String.valueOf(ViewAttributes.w));
            Log.v("View High:",String.valueOf(ViewAttributes.h));
            //获取屏幕分辨率和1920*1080的比例 以便适应不同大小的屏幕
            ViewAttributes.bili = (float) (Math.sqrt(ViewAttributes.w * ViewAttributes.h) / Math.sqrt(1920 * 1080));
            setContentView(new Draw(this));
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        }

    }

    private void initMyItem(){
        for (int j=0;j<100;j++){
            MyItem item = new MyItem("失眠+" + String.valueOf(j),R.drawable.apple);
            itemList.add(item);
        }
    }
}
