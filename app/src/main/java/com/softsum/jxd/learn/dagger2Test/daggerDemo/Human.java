package com.softsum.jxd.learn.dagger2Test.daggerDemo;

import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;


public class Human {

    @Inject
    Eat eat;

    public void action(){
        DaggerHumanComponent.create().inject(this);
        Log.d(TAG, "action: " +
                eat.getEatting() +
                eat.food.getName());
    }
}
