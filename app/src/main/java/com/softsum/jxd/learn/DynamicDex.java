package com.softsum.jxd.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softsum.jxd.learn.dynamic.DynamicTestIn;

import java.io.File;
import java.io.IOException;

import dalvik.system.DexClassLoader;

public class DynamicDex extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_dex);

        Button openDex = (Button)findViewById(R.id.dex_test);
        openDex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.dex_test:
                    loadDexClass();
                break;
            default:
        }
    }

    private DynamicTestIn dynamic;

    private void loadDexClass() {
        File cacheFile = FileUtils.getCacheDir(getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "dynamic_dex.jar";
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(this, "dynamic_dex.jar", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下面开始加载dex class
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        try {
            Class libClazz = dexClassLoader.loadClass("com.softsum.jxd.learn.dynamic.impl.DynamicDexTest");
            dynamic = (DynamicTestIn) libClazz.newInstance();
            if (dynamic != null)
                Toast.makeText(this, dynamic.DynamicDexTestCode(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
