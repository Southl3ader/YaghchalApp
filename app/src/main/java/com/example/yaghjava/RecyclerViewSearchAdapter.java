package com.example.yaghjava;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaghjava.dataBase.groceriesDA;

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.ViewHolder> {

    private String[] mData;
    private ItemClickListener mClickListener;
    Context cnt;
//    groceriesDA access;


    // data is passed into the constructor
    RecyclerViewSearchAdapter(Context context, String[] search) {
        this.cnt = context;
        this.mData = search;
//        access = new groceriesDA(SearchActivity.activity);
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cnt = parent.getContext();
        View view = LayoutInflater.from(cnt).inflate(R.layout.recyclerview_search, parent, false);
        return new ViewHolder(view);

    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position].toString());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.access.openDB();
                SearchActivity.access.addToFridge(holder.myTextView.getText().toString());
                SearchActivity.access.closeDB();
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imageView;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.searchItems);
            //myTextView.setBackgroundResource(R.drawable.cabbage);
            linearLayout = itemView.findViewById(R.id.selective);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

//    public void filter(String[] s){
//        mData = s;
//        notifyDataSetChanged();
//    }
}
