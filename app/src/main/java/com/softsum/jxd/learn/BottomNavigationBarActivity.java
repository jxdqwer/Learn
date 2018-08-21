package com.softsum.jxd.learn;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.softsum.jxd.learn.relaxedRead.AppsFragment;
import com.softsum.jxd.learn.relaxedRead.HomeFragment;
import com.softsum.jxd.learn.relaxedRead.SettingsFragment;
import com.softsum.jxd.learn.relaxedRead.TeaFragment;
import com.softsum.jxd.learn.tea.QiuShiJsoupThread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BottomNavigationBarActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectPosition = 0;
    private String TAG = BottomNavigationBarActivity.class.getSimpleName();
    private Map fragmentMap;
    private HomeFragment homeFragment;
    private AppsFragment appsFragment;
    private SettingsFragment settingsFragment;
    private TeaFragment teaFragment;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //mTestTV.setText("This is handleMessage");//更新UI
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_nb);
        bottomNavigationBar.setTabSelectedListener(this).setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.bottomNavigationBarSelectColor)
                .setInActiveColor(R.color.bottomNavigationBarNoSelectColor)
                .setBarBackgroundColor(R.color.bottomNavigationBarBackgroundColor);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_icon_home,"Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_breakfast,"Tea"))
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_apps,"Apps"))
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_settings,"Settings"))
                .setFirstSelectedPosition(lastSelectPosition)
                .initialise();
        initFragment();
        QiuShiJsoupThread thread = new QiuShiJsoupThread();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    try {
//                        Document mozilla = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").userAgent("Mozilla")
//                                .timeout(3000)
//                                .post();
//
//                        Elements select1 = mozilla.select("div.author.clearfix");
//                        for (Element element : select1) {
//                            Document parse = Jsoup.parse(element.toString());
//                            Elements select = parse.select("a h2");
//                            Elements select2 = parse.select("a img");
//                            Log.d("QiuShiJsoupThread","element 名字："+select.text());
//                            Log.d("QiuShiJsoupThread","element 头像："+select2.attr("src"));
//                        }
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Thread.sleep(1000);//在子线程有一段耗时操作,比如请求网络
//                    mHandler.sendEmptyMessage(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        //replaceFragment(homeFragment);
        //replaceFragment(new HomeFragment());
    }

    private Runnable mythread = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: mythread run!");
        }
    };


    private void initFragment(){
        fragmentMap = new HashMap<Fragment,Integer>();

        homeFragment = new HomeFragment();
        appsFragment = new AppsFragment();
        settingsFragment = new SettingsFragment();
        teaFragment = new TeaFragment();

        fragmentMap.put(homeFragment,R.id.home_frag_layout);
        fragmentMap.put(appsFragment,R.id.apps_frag_layout);
        fragmentMap.put(teaFragment,R.id.tea_frag_layout);
        fragmentMap.put(settingsFragment,R.id.settings_frag_layout);

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_frag_layout, homeFragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Iterator<Map.Entry<Fragment, Integer>> iterator = fragmentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Fragment, Integer> entry = iterator.next();
            Fragment entryFragment = entry.getKey();
            //entry.getValue();
            if(entryFragment != null){
                transaction.hide(entryFragment);
            }
        }
        if(fragment.isAdded()){
            transaction.show(fragment);
        }
        else{
            transaction.add((Integer) fragmentMap.get(fragment),fragment).show(fragment);
        }
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                replaceFragment(homeFragment);
                break;
            case 1:
                replaceFragment(teaFragment);
                break;
            case 2:
                replaceFragment(appsFragment);
                break;
            case 3:
                replaceFragment(settingsFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
