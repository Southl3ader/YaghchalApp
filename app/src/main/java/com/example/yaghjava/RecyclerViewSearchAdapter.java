package com.example.yaghjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        View search = LayoutInflater.from(cnt).inflate(R.layout.recyclerview_search, parent, false);
        return new ViewHolder(search);

    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position].toString());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SearchActivity.access.openDB();
//                SearchActivity.access.addToFridge2(holder.myTextView.getText().toString());
//                SearchActivity.access.closeDB();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.popup_company,null);
                ImageView imageView = view.findViewById(R.id.c_image);
                TextView textView =  view.findViewById(R.id.c_name);
                EditText amount = view.findViewById(R.id.c_amount);
                EditText bdate = view.findViewById(R.id.c_bdate);
                EditText edate = view.findViewById(R.id.c_edate);
                EditText company = view.findViewById(R.id.c_company);
                EditText button = view.findViewById(R.id.c_add);
                builder.setView(dialogview);
                builder.setCancelable(true);
                builder.show();
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });


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
