package com.softsum.jxd.learn.dagger2Test.daggerDemo;

import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

public class Food {

    @Inject
    public Food(){
        Log.d(TAG, "Food: Inject");
    }

    String getName(){
        return "巧克力";
    }
}
