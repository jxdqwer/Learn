package com.softsum.jxd.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lagout);

        Button yaogan1 = (Button)findViewById(R.id.yaogan1);
        Button meizi = (Button)findViewById(R.id.kanmeizi);
        Button meiziRecyc = (Button)findViewById(R.id.kanmeiziRecyc);
        Button bottomNBAR = (Button)findViewById(R.id.bottom_navigation_bar_test);
        yaogan1.setOnClickListener(this);
        meizi.setOnClickListener(this);
        meiziRecyc.setOnClickListener(this);
        bottomNBAR.setOnClickListener(this);
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
            case R.id.kanmeiziRecyc:
                Log.d("onClick", "onClick: kanmeiziRecyc");
                intent = new Intent("android.suftsum.activity.KANMEIZIRECYC");
                intent.addCategory("android.suftsum.category.KANMEIZIRECYC");
                startActivity(intent);
                break;
            case R.id.bottom_navigation_bar_test:
                Log.d("onClick", "onClick: bottom_navigation_bar_test");
                intent = new Intent("android.suftsum.activity.BOOTOM_N_B");
                intent.addCategory("android.suftsum.category.BOOTOM_N_B");
                startActivity(intent);
                break;
            default:
        }
    }
}



