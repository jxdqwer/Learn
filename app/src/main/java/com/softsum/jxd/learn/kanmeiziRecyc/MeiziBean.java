package com.softsum.jxd.learn.kanmeiziRecyc;

import android.graphics.Bitmap;

public class MeiziBean {

    private String name;
    private Bitmap image;

    public MeiziBean(String name,Bitmap image){
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public Bitmap getImage(){
        return image;
    }
}
