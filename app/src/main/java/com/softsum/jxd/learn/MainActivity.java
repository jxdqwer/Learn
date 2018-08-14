package com.softsum.jxd.learn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softsum.jxd.learn.yaogan.Draw;
import com.softsum.jxd.learn.yaogan.ViewAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//public class MyRecycleViewAdapter extends RecyclerView.Adapter

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lagout);

        Button yaogan1 = (Button)findViewById(R.id.yaogan1);
        Button meizi = (Button)findViewById(R.id.kanmeizi);
        yaogan1.setOnClickListener(this);
        meizi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.yaogan1:
                Log.d("onClick", "onClick: yaogan1");
                intent = new Intent("android.suftsum.activity.YAOGAN1");
                intent.addCategory("android.suftsum.category.YAOGAN1");
                startActivity(intent);
                break;
            case R.id.kanmeizi:
                Log.d("onClick", "onClick: kanmeizi");
                intent = new Intent("android.suftsum.activity.KANMEIZI");
                intent.addCategory("android.suftsum.category.KANMEIZI");
                startActivity(intent);
                break;
            default:
        }
    }
}



