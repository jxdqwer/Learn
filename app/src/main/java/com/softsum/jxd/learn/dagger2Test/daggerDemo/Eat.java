package com.softsum.jxd.learn.dagger2Test.daggerDemo;

import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;


public class Eat{

    Food food;

    @Inject

    Eat(Food food){
        this.food = food;
        Log.d(TAG, "Eat: inject");
    }

    String getEatting(){
        return "吃";
    }
}
