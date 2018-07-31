package com.softsum.jxd.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<MyItem> {

    private int resourceId;

    public ItemAdapter(Context context, int textViewResourceId,
                       List<MyItem> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MyItem item = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view = convertView;
        }
        ImageView itemImage = (ImageView)view.findViewById(R.id.item_image);
        itemImage.setImageResource(item.getImageId());
        TextView itemName = (TextView)view.findViewById(R.id.item_name);
        itemName.setText(item.getName());
        return view;
    }
}