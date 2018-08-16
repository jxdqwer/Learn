package com.softsum.jxd.learn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>{

    private List<MyItem> mMyItemList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public  ViewHolder(View view){
            super(view);
            itemName = (TextView)view.findViewById(R.id.item_name);
            itemImage = (ImageView)view.findViewById(R.id.item_image);
        }
    }

    public ItemRecyclerAdapter(List<MyItem> itemList){
        mMyItemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_son,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MyItem item = mMyItemList.get(position);
                Toast.makeText(view.getContext(),"clicked viwe" + item.getName(),
                Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MyItem item = mMyItemList.get(position);
                Toast.makeText(view.getContext(),"clicked image" + item.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        MyItem item = mMyItemList.get(position);
        holder.itemImage.setImageResource(item.getImageId());
        holder.itemName.setText(item.getName());
    }

    @Override
    public int getItemCount(){
        return mMyItemList.size();
    }
}
