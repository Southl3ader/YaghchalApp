package com.example.yaghjava;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class RecyclerViewShoppingListAdapter extends RecyclerView.Adapter<RecyclerViewShoppingListAdapter.ViewHolder> {

    private int[] mID;
    private String[] mData;
    private String[] mType;
    private String[] BDate;
    private String[] EDate;
    private String[] company;
    private ItemClickListener mClickListener;
    Context cnt;
    Bitmap[] pic;
    private String[] itemcount;
    String S = "0";

    // data is passed into the constructor
    RecyclerViewShoppingListAdapter(Context context, int[] ID ,String[] data, String[] company, Bitmap[] pic, String[] ItemAmount, String[] type) {
        this.mID = ID;
        this.mData = data;
        this.company = company;
        this.cnt = context;
        this.pic = pic;
        this.itemcount = ItemAmount;
        this.mType = type;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cnt = parent.getContext();
        View view= LayoutInflater.from(cnt).inflate(R.layout.recycler_view_shoppinglist,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.myTextView.setText(mData[position].toString());
        holder.ItemCountText.setText(itemcount[position].toString());
        holder.imageView.setImageBitmap(pic[position]);

        holder.buy.setOnClickListener(new View.OnClickListener() {
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
                                SearchActivity.access.shopToFridge(mID[position],mData[position],a,bdate.getText().toString(),edate.getText().toString(),company[position]);
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

//        holder.remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FridgeActivity.access.openDB();
//                FridgeActivity.access.remove(mID[position]);
//                FridgeActivity.access.closeDB();
//                Toast.makeText(cnt.getApplicationContext(), mData[position] + " از لیست خرید شما حذف شد", Toast.LENGTH_SHORT).show();
//                ((FridgeActivity)cnt).onResume();
//            }
//        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For Items With Company
                if (mType.equals("Dairy") || mType.equals("Protein")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.shoppinglist_details, null);
                    ImageView imageView = dialogview.findViewById(R.id.sh_c_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.sh_c_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.sh_c_amount);
                    amount.setText(holder.ItemCountText.getText());
                    amount.setEnabled(false);
                    EditText companytext = dialogview.findViewById(R.id.sh_c_company);
                    companytext.setText(company[position]);
                    companytext.setEnabled(false);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog shoppinglistdialog = builder.show();
                }
                //For Items Without Company
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.shoppinglist_details_no_company, null);
                    ImageView imageView = dialogview.findViewById(R.id.sh_n_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.shop_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.sh_n_amount);
                    amount.setText(holder.ItemCountText.getText());
                    amount.setEnabled(false);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog shoppinglistdialog = builder.show();
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
        TextView ItemCountText;
        ImageView imageView;
        ImageButton buy;
        ImageButton remove;
        LinearLayout linearLayout;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.groceries);
            ItemCountText = itemView.findViewById(R.id.groceries_count);
            imageView = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.selective_shoppinglist);
            buy = itemView.findViewById(R.id.buy);
            remove = itemView.findViewById(R.id.remove);
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
