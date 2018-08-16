package com.softsum.jxd.learn.kanmeiziRecyc;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.softsum.jxd.learn.R;

import java.util.ArrayList;
import java.util.List;

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.ViewHolder>{

    private List<MeiziBean> mMeiziBeanList = new ArrayList<>();
    volatile int index = 0;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public  ViewHolder(View view){
            super(view);
            itemName = (TextView)view.findViewById(R.id.meizi_name);
            itemImage = (ImageView)view.findViewById(R.id.meizi_image);
        }
    }

    public MeiziAdapter(RecyclerView recyclerView){

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int[] topbenchmark = {0,0,0};
                int[] topPosition = layoutManager.findFirstVisibleItemPositions(topbenchmark);
                int[] endbenchmark = {0,0,0};
                int[] endPosition = layoutManager.findLastCompletelyVisibleItemPositions(endbenchmark);
                Log.d("Top Position --> ", topPosition[0] + ":" + topPosition[1] + ":" + topPosition[2]);
                Log.d("End Position --> ", endPosition[0] + ":" + endPosition[1] + ":" + endPosition[2]);
                Log.d("itemCount  --> ", itemCount + " ");
            }
        });
    }

    @Override
    public MeiziAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meizi_recycle,parent,false);
        final MeiziAdapter.ViewHolder holder = new MeiziAdapter.ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MeiziBean baen = mMeiziBeanList.get(position);
                Toast.makeText(view.getContext(),"clicked viwe" + baen.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MeiziBean baen = mMeiziBeanList.get(position);
                Toast.makeText(view.getContext(),"clicked image" + baen.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(MeiziAdapter.ViewHolder holder, int position){
        MeiziBean baen = mMeiziBeanList.get(position);
        holder.itemImage.setImageBitmap(baen.getImage());
        holder.itemName.setText(baen.getName());
    }

    public synchronized void addItem(int position,MeiziBean baen){
            baen.setName(String.valueOf(index));
            mMeiziBeanList.add(baen);
            notifyItemInserted(index);
            index++;
    }

    @Override
    public int getItemCount(){
        return mMeiziBeanList.size();
    }
}
