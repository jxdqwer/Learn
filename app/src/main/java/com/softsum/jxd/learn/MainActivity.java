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

        Button yaogan1 = findViewById(R.id.yaogan1);
        Button meizi = findViewById(R.id.kanmeizi);
        Button meiziRecyc = findViewById(R.id.kanmeiziRecyc);
        Button bottomNBAR = findViewById(R.id.bottom_navigation_bar_test);
        Button bottomDex = findViewById(R.id.bottom_dex_test);
        Button bottomFile = findViewById(R.id.bottom_file_test);
        Button bottomJni = findViewById(R.id.bottom_jni_test);
        Button bottomDegger = findViewById(R.id.bottom_degger_test);
        yaogan1.setOnClickListener(this);
        meizi.setOnClickListener(this);
        meiziRecyc.setOnClickListener(this);
        bottomNBAR.setOnClickListener(this);
        bottomDex.setOnClickListener(this);
        bottomFile.setOnClickListener(this);
        bottomJni.setOnClickListener(this);
        bottomDegger.setOnClickListener(this);
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
            case R.id.bottom_dex_test:
                Log.d("onClick", "onClick: bottom_dex_test");
                intent = new Intent("android.suftsum.activity.DEX_TEST");
                intent.addCategory("android.suftsum.category.DEX_TEST");
                startActivity(intent);
                break;
            case R.id.bottom_file_test:
                Log.d("onClick", "onClick: bottom_file_test");
                intent = new Intent("android.suftsum.activity.FILE_TEST");
                intent.addCategory("android.suftsum.category.FILE_TEST");
                startActivity(intent);
                break;
            case R.id.bottom_jni_test:
                Log.d("onClick", "onClick: bottom_jni_test");
                intent = new Intent("android.suftsum.activity.JNI_TEST");
                intent.addCategory("android.suftsum.category.JNI_TEST");
                startActivity(intent);
                break;
            case R.id.bottom_degger_test:
                Log.d("onClick", "onClick: bottom_degger_test");
                intent = new Intent("android.suftsum.activity.DEGGER_TEST");
                intent.addCategory("android.suftsum.category.DEGGER_TEST");
                startActivity(intent);
                break;
            default:
        }
    }
}



