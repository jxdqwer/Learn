package com.softsum.jxd.learn;

public class MyItem{

    private String name;

    private int imageId;

    public MyItem(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public  int getImageId(){
        return imageId;
    }
}