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
    private String[] mCompany;
    private ItemClickListener mClickListener;
    Context cnt;
    Bitmap[] pic;
    private String[] mAmount;
    String S = "0";

    // data is passed into the constructor
    RecyclerViewShoppingListAdapter(Context context, int[] ID ,String[] data, String[] company, Bitmap[] pic, String[] ItemAmount, String[] type) {
        this.mID = ID;
        this.mData = data;
        this.mCompany = company;
        this.cnt = context;
        this.pic = pic;
        this.mAmount = ItemAmount;
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
        holder.imageView.setImageBitmap(pic[position]);

        holder.buy.setOnClickListener(new View.OnClickListener() {
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
                    amount.setText(mAmount[position]);
                    EditText bdate = dialogview.findViewById(R.id.c_bdate);
                    EditText edate = dialogview.findViewById(R.id.c_edate);
                    EditText company = dialogview.findViewById(R.id.c_company);
                    company.setText(mCompany[position]);
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
                                ShoppingListActivity.access.openDB();
                                ShoppingListActivity.access.shopToFridge(mID[position],mData[position],a,bdate.getText().toString(),edate.getText().toString(),mCompany[position]);
                                ShoppingListActivity.access.closeDB();
                                Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                                ad.cancel();
                                ((ShoppingListActivity)cnt).onResume();
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
                    amount.setText(mAmount[position]);
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
                                ShoppingListActivity.access.openDB();
                                ShoppingListActivity.access.shopToFridge(mID[position],mData[position],a,bdate.getText().toString(),edate.getText().toString(),mCompany[position]);
                                System.out.println(mID[position]);
                                ShoppingListActivity.access.closeDB();
                                Toast.makeText(cnt.getApplicationContext(), textView.getText().toString() + " به یخچال شما اضافه شد", Toast.LENGTH_SHORT).show();
                                ad.cancel();
                                ((ShoppingListActivity)cnt).onResume();
                            }

                        }
                    });

                }
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingListActivity.access.openDB();
                ShoppingListActivity.access.removeShop(mID[position]);
                ShoppingListActivity.access.closeDB();
                Toast.makeText(cnt.getApplicationContext(), mData[position] + " از لیست خرید شما حذف شد", Toast.LENGTH_SHORT).show();
                ((ShoppingListActivity)cnt).onResume();
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For Items With Company
                if (mType[position].equals("Dairy") || mType[position].equals("Protein") || mType[position].equals("Others")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.shoppinglist_details, null);
                    ImageView imageView = dialogview.findViewById(R.id.sh_c_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.sh_c_name);
                    textView.setText(holder.myTextView.getText().toString());
                    TextView amount = dialogview.findViewById(R.id.sh_c_amount);
                    amount.setText(mAmount[position]);
                    amount.setEnabled(false);
                    TextView companytext = dialogview.findViewById(R.id.sh_c_company);
                    companytext.setText(mCompany[position]);
                    companytext.setEnabled(false);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    builder.show();
                }
                //For Items Without Company
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.shoppinglist_details_no_company, null);
                    ImageView imageView = dialogview.findViewById(R.id.sh_n_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.sh_n_name);
                    textView.setText(mData[position]);
                    TextView amount = dialogview.findViewById(R.id.sh_n_amount);
                    amount.setText(mAmount[position]);
                    amount.setEnabled(false);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    builder.show();
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
        TextView noCompanyD;
        TextView ItemCountText;
        ImageView imageView;
        ImageView noCompanyImage;
        ImageButton buy;
        ImageButton remove;
        LinearLayout linearLayout;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.sh_name);
            imageView = itemView.findViewById(R.id.sh_image);
            noCompanyD = itemView.findViewById(R.id.sh_n_name);
            noCompanyImage = itemView.findViewById(R.id.sh_n_image);
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
