package com.example.yaghjava;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemsAdapter extends RecyclerView.Adapter<RecyclerViewItemsAdapter.ViewHolder> {

    private int[] mID;
    private String[] mData;
    private ItemClickListener mClickListener;
    Context cnt;
    Bitmap[] pic;
    private String[] itemcount;

    // data is passed into the constructor
    RecyclerViewItemsAdapter(Context context, int[] ID ,String[] data, Bitmap[] pic, String[] ItemAmount) {
        this.mID = ID;
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
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.activity_delete_items,null);
                Button yes = dialogview.findViewById(R.id.yes);
                Button no = dialogview.findViewById(R.id.no);
//                TextView textView =  dialogview.findViewById(R.id.groceries);
//                textView.setText(holder.myTextView.getText().toString());
                builder.setView(dialogview);
                builder.setCancelable(true);
                AlertDialog ad = builder.show();
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FridgeActivity.access.openDB();
                        FridgeActivity.access.remove(mID[position]);
                        FridgeActivity.access.closeDB();
                        Toast.makeText(cnt.getApplicationContext(), mData[position] + " از یخچال شما حذف شد", Toast.LENGTH_SHORT).show();
                        ad.cancel();
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,getItemCount());
                        ((FridgeActivity)cnt).onResume();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ad.cancel();
                    }
                });
                return true;
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
        TextView ItemCountText;
        ImageView imageView;
        LinearLayout linearLayout;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.groceries);
            ItemCountText = itemView.findViewById(R.id.groceries_count);
            imageView = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.selective_items);
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
