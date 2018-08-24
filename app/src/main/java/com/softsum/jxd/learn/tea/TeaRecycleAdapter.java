package com.softsum.jxd.learn.tea;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.softsum.jxd.learn.R;

import java.util.ArrayList;
import java.util.List;

public class TeaRecycleAdapter extends RecyclerView.Adapter<TeaRecycleAdapter.ViewHolder>{

    private List<TeaBean> mTeaBeanList = new ArrayList<>();
    volatile int index = 0;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemAuthorImg;
        TextView itemAuthor;
        TextView itemText;
        ImageView itemImage;
        TextView itemUpvote;

        public  ViewHolder(View view){
            super(view);
            itemAuthor = (TextView)view.findViewById(R.id.tea_author);
            itemAuthorImg = (ImageView)view.findViewById(R.id.tea_authorImg);
            itemText = (TextView)view.findViewById(R.id.tea_text);
            itemImage = (ImageView)view.findViewById(R.id.tea_image);
            itemUpvote = (TextView)view.findViewById(R.id.tea_upvote);

        }
    }

    public TeaRecycleAdapter(RecyclerView recyclerView){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int topPosition = layoutManager.findFirstVisibleItemPosition();
                int endPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                Log.d("Top Position --> ", String.valueOf(topPosition));
                Log.d("End Position --> ", String.valueOf(endPosition));
                Log.d("itemCount  --> ", itemCount + " ");
            }
        });
    }

    @Override
    public TeaRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_bean_lagout,parent,false);
        final TeaRecycleAdapter.ViewHolder holder = new TeaRecycleAdapter.ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                TeaBean baen = mTeaBeanList.get(position);
                Toast.makeText(view.getContext(),"clicked viwe" + baen.getAuthor(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemAuthorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                TeaBean baen = mTeaBeanList.get(position);
                Toast.makeText(view.getContext(),"clicked image" + baen.getAuthor(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(TeaRecycleAdapter.ViewHolder holder, int position){
        TeaBean bean = mTeaBeanList.get(position);
        holder.itemAuthorImg.setImageBitmap(bean.getAuthorImg());
        holder.itemAuthor.setText(bean.getAuthor());
//        holder.itemAuthorImg.setImageResource(R.drawable.apple);
        holder.itemText.setText(bean.getTitle());
    }

    public synchronized void addItem(int position,TeaBean baen){
          mTeaBeanList.add(baen);
        notifyItemInserted(index);
        index++;
    }

    @Override
    public int getItemCount(){
        return mTeaBeanList.size();
    }
}