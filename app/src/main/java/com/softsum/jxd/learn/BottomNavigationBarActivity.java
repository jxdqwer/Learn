package com.softsum.jxd.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class BottomNavigationBarActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectPosition = 0;
    private String TAG = BottomNavigationBarActivity.class.getSimpleName();

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
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_apps,"Apps"))
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_breakfast,"Tea"))
                .addItem(new BottomNavigationItem(R.drawable.ic_icon_settings,"Settings"))
                .setFirstSelectedPosition(lastSelectPosition)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
