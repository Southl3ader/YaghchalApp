package com.example.yaghjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private String[] mType;
    private ItemClickListener mClickListener;
    Context cnt;
    private Bitmap[] pic;
    String S = "0";
    PopupCompanyActivity popupCompanyActivity = new PopupCompanyActivity();
//    groceriesDA access;


    // data is passed into the constructor
    RecyclerViewSearchAdapter(Context context, String[] search, Bitmap[] pic, String[] type) {
        this.cnt = context;
        this.mData = search;
        this.pic = pic;
        this.mType = type;
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
//        holder.imageView.setImageBitmap(pic[position]);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mType[position].equals("Dairy" ) || mType[position].equals("Protein") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.popup_company,null);
                    ImageView imageView = dialogview.findViewById(R.id.c_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView =  dialogview.findViewById(R.id.c_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.c_amount);
                    EditText bdate = dialogview.findViewById(R.id.c_bdate);
                    EditText edate = dialogview.findViewById(R.id.c_edate);
                    EditText company = dialogview.findViewById(R.id.c_company);
                    Button button = dialogview.findViewById(R.id.c_add);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    builder.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String S = amount.getText().toString();
                            int a =Integer.parseInt(S);
                            SearchActivity.access.openDB();
                            SearchActivity.access.addToFridge2(textView.getText().toString(),a ,bdate.getText().toString(),edate.getText().toString(),company.getText().toString());
                            SearchActivity.access.closeDB();
                            Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.popup_no_company,null);
                    ImageView imageView = dialogview.findViewById(R.id.n_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView =  dialogview.findViewById(R.id.n_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.n_amount);
                    EditText bdate = dialogview.findViewById(R.id.n_bdate);
                    EditText edate = dialogview.findViewById(R.id.n_edate);
                    Button button = dialogview.findViewById(R.id.n_add);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    builder.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            S = amount.getText().toString();
                            int a =Integer.parseInt(S);
                            SearchActivity.access.openDB();
                            SearchActivity.access.addToFridge2(textView.getText().toString(),a ,bdate.getText().toString(),edate.getText().toString(),null);
                            SearchActivity.access.closeDB();
                            Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


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
