package com.softsum.jxd.learn.kanmeiziRecyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.softsum.jxd.learn.R;
import java.util.List;

public class MeiziAdapter extends ArrayAdapter<MeiziBean> {

    private int resourceId;

    public MeiziAdapter(Context context, int textViewResourceId,
                       List<MeiziBean> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MeiziBean bean = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view = convertView;
        }
        ImageView itemImage = (ImageView)view.findViewById(R.id.item_image);
        itemImage.setImageBitmap(bean.getImage());
        TextView itemName = (TextView)view.findViewById(R.id.item_name);
        itemName.setText(bean.getName());
        return view;
    }
}
