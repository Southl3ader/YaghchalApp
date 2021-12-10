package com.example.yaghjava;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemsAdapter extends RecyclerView.Adapter<RecyclerViewItemsAdapter.ViewHolder> {

    private String[] mData;
    private ItemClickListener mClickListener;
    Context cnt;
    Bitmap[] pic;
    private String[] itemcount;

    // data is passed into the constructor
    RecyclerViewItemsAdapter(Context context, String[] data, Bitmap[] pic, String[] ItemAmount) {
        this.mData = data;
        this.cnt = context;
        this.pic = pic;
        this.itemcount = ItemAmount;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cnt = parent.getContext();
        View view= LayoutInflater.from(cnt).inflate(R.layout.recyclerview_items,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position].toString());
        holder.ItemCountText.setText(itemcount[position].toString());
        holder.imageView.setImageBitmap(pic[position]);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView ItemCountText;
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.groceries);
            ItemCountText = itemView.findViewById(R.id.groceries_count);
            imageView = itemView.findViewById(R.id.img);
            //myTextView.setBackgroundResource(R.drawable.cabbage);
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
}
