package com.example.yaghjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private int[] mID;
    private ItemClickListener mClickListener;
    Context cnt;
    private Bitmap[] pic;
    String S = "0";
    //PopupCompanyActivity popupCompanyActivity = new PopupCompanyActivity();
//    groceriesDA access;


    // data is passed into the constructor
    RecyclerViewSearchAdapter(Context context,int[] id, String[] search, Bitmap[] pic, String[] type) {
        this.mID = id;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.myTextView.setText(mData[position].toString());
//        holder.imageView.setImageBitmap(pic[position]);
        holder.addToShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mType[position].equals("Dairy" ) || mType[position].equals("Protein") || mType[position].equals("Others")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.search_popup_cart, null);
                    ImageView imageView = dialogview.findViewById(R.id.shop_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textview = dialogview.findViewById(R.id.shop_name);
                    textview.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.shop_amount);
                    EditText company = dialogview.findViewById(R.id.shop_company);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog shopppop = builder.show();
                    Button button = dialogview.findViewById(R.id.shop_add);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TextUtils.isEmpty(amount.getText().toString())) {
                                Toast.makeText(cnt.getApplicationContext(), "لطفا تعداد را تعیین کنید", Toast.LENGTH_SHORT).show();
                            } else {
                                String convert = amount.getText().toString();
                                int amountToint = Integer.parseInt(convert);
                                SearchActivity.access.openDB();
                                SearchActivity.access.addToShop(textview.getText().toString(), amountToint, company.getText().toString());
                                Toast.makeText(cnt.getApplicationContext(), "به لیست خرید اضافه شد", Toast.LENGTH_SHORT).show();
                                SearchActivity.access.closeDB();
                                shopppop.cancel();
                            }
                        }
                    });
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.search_popup_cart_nocompany, null);
                    ImageView imageView = dialogview.findViewById(R.id.shop_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textview = dialogview.findViewById(R.id.shop_name);
                    textview.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.shop_amount);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog shopppop = builder.show();
                    Button button = dialogview.findViewById(R.id.shop_add);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TextUtils.isEmpty(amount.getText().toString())) {
                                Toast.makeText(cnt.getApplicationContext(), "لطفا تعداد را تعیین کنید", Toast.LENGTH_SHORT).show();
                            } else {
                                String convert = amount.getText().toString();
                                int amountToint = Integer.parseInt(convert);
                                SearchActivity.access.openDB();
                                SearchActivity.access.addToShop(textview.getText().toString(), amountToint, null);
                                Toast.makeText(cnt.getApplicationContext(), "به لیست خرید اضافه شد", Toast.LENGTH_SHORT).show();
                                SearchActivity.access.closeDB();
                                shopppop.cancel();
                            }
                        }
                    });
                }
            }
        });
        holder.addToFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mType[position].equals("Dairy" ) || mType[position].equals("Protein") || mType[position].equals("Others")){
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
                    AlertDialog ad = builder.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(amount.getText().toString())){
                                Toast.makeText(cnt.getApplicationContext(), "لطفا تعداد را تعیین کنید", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String S = amount.getText().toString();
                                int a =Integer.parseInt(S);
                                SearchActivity.access.openDB();
                                SearchActivity.access.addToFridge2(textView.getText().toString(),a ,bdate.getText().toString(),edate.getText().toString(),company.getText().toString());
                                SearchActivity.access.closeDB();
                                Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                                ad.cancel();
                            }
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
                    AlertDialog ad = builder.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(amount.getText().toString())){
                                Toast.makeText(cnt.getApplicationContext(), "لطفا تعداد را تعیین کنید", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                S = amount.getText().toString();
                                int a =Integer.parseInt(S);
                                SearchActivity.access.openDB();
                                SearchActivity.access.addToFridge2(textView.getText().toString(),a ,bdate.getText().toString(),edate.getText().toString(),null);
                                System.out.println(mID[position]);
                                SearchActivity.access.closeDB();
                                Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                                ad.cancel();
                            }

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
        LinearLayout linearLayout;
        ImageButton addToFridge;
        ImageButton addToShoppingList;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.searchItems);
            linearLayout = itemView.findViewById(R.id.selective_search);
            addToFridge = itemView.findViewById(R.id.addToFridge);
            addToShoppingList = itemView.findViewById(R.id.addToShoppinglist);
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
