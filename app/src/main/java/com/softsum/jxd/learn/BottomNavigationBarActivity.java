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
    Handler handler = new Handler();
    Runnable update_thread = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: I am Happy");
            Message message = new Message();
            message.what = 123;
            handlerStop.sendMessage(message);
        }
    };
    
    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case 123:
                    Log.d(TAG, "handleMessage: handleStop");
                    handler.removeCallbacks(update_thread);
                    break;
            }
            super.handleMessage(msg);
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
        handler.post(update_thread);
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
