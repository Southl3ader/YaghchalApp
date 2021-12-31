package com.example.yaghjava;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class RecyclerViewItemsAdapter extends RecyclerView.Adapter<RecyclerViewItemsAdapter.ViewHolder> {

    private int[] mID;
    private String[] mData;
    private String mType;
    private String[] BDate;
    private String[] EDate;
    private String[] company;
    private ItemClickListener mClickListener;
    Context cnt;
    Bitmap[] pic;
    private String[] itemcount;

    // data is passed into the constructor
    RecyclerViewItemsAdapter(Context context, int[] ID ,String[] data, String[] BDate, String[] EDate, String[] company, Bitmap[] pic, String[] ItemAmount, String type) {
        this.mID = ID;
        this.mData = data;
        this.BDate = BDate;
        this.EDate = EDate;
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
        View view= LayoutInflater.from(cnt).inflate(R.layout.recyclerview_items,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.myTextView.setText(mData[position].toString());
        holder.ItemCountText.setText(itemcount[position].toString());
        holder.imageView.setImageBitmap(pic[position]);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FridgeActivity.access.openDB();
                FridgeActivity.access.increase(mID[position]);
                FridgeActivity.access.closeDB();
                ((FridgeActivity)cnt).onResume();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FridgeActivity.access.openDB();
                FridgeActivity.access.reduce(mID[position]);
                FridgeActivity.access.closeDB();
                ((FridgeActivity)cnt).onResume();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For Items With Company
                if (mType.equals("Dairy") || mType.equals("Protein")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.details_popup, null);
                    ImageView imageView = dialogview.findViewById(R.id.fridgepop_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.fridgepop_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.fridgepop_amount);
                    amount.setText(holder.ItemCountText.getText());
                    amount.setEnabled(false);
                    Button editbutton = dialogview.findViewById(R.id.editbutton);
                    EditText bdate = dialogview.findViewById(R.id.fridgepop_bdate);
                    bdate.setText(BDate[position]);
                    bdate.setEnabled(false);
                    EditText edate = dialogview.findViewById(R.id.fridgepop_edate);
                    edate.setText(EDate[position]);
                    edate.setEnabled(false);
                    EditText companytext = dialogview.findViewById(R.id.fridgepop_company);
                    companytext.setText(company[position]);
                    companytext.setEnabled(false);
                    Button button = dialogview.findViewById(R.id.fridgepop_savechange);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog fridgedialog = builder.show();

                    //Enabling Edit
                    editbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            amount.setEnabled(true);
                            bdate.setEnabled(true);
                            edate.setEnabled(true);
                            companytext.setEnabled(true);
                        }
                    });

                    //Saving Edited Fields
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String convert = amount.getText().toString();
                            int amountToint = Integer.parseInt(convert);
                            FridgeActivity.access.openDB();
                            FridgeActivity.access.updateAttributes(mID[position], amountToint, bdate.getText().toString(), edate.getText().toString(), companytext.getText().toString());
                            FridgeActivity.access.closeDB();
                            fridgedialog.cancel();
                            ((FridgeActivity) cnt).onResume();

                        }
                    });
                }
                //For Items Without Company
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.details_popup_company, null);
                    ImageView imageView = dialogview.findViewById(R.id.fridgepop_image);
                    imageView.setImageBitmap(pic[position]);
                    TextView textView = dialogview.findViewById(R.id.fridgepop_name);
                    textView.setText(holder.myTextView.getText().toString());
                    EditText amount = dialogview.findViewById(R.id.fridgepop_amount);
                    amount.setText(holder.ItemCountText.getText());
                    amount.setEnabled(false);
                    Button editbutton = dialogview.findViewById(R.id.editbutton);
                    EditText bdate = dialogview.findViewById(R.id.fridgepop_bdate);
                    bdate.setText(BDate[position]);
                    bdate.setEnabled(false);
                    EditText edate = dialogview.findViewById(R.id.fridgepop_edate);
                    edate.setText(EDate[position]);
                    edate.setEnabled(false);
                    Button button = dialogview.findViewById(R.id.fridgepop_savechange);
                    builder.setView(dialogview);
                    builder.setCancelable(true);
                    AlertDialog fridgedialog = builder.show();

                    //Enabling Edit
                    editbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            amount.setEnabled(true);
                            bdate.setEnabled(true);
                            edate.setEnabled(true);
                        }
                    });

                    //Saving Edits
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String convert = amount.getText().toString();
                            int amountToint = Integer.parseInt(convert);
                            FridgeActivity.access.openDB();
                            FridgeActivity.access.updateAttributes(mID[position], amountToint, bdate.getText().toString(), edate.getText().toString(), null);
                            FridgeActivity.access.closeDB();
                            fridgedialog.cancel();
                            ((FridgeActivity) cnt).onResume();

                        }
                    });
                }

            }
        });

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
        TextView plus;
        TextView minus;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.groceries);
            ItemCountText = itemView.findViewById(R.id.groceries_count);
            imageView = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.selective_items);
            plus = itemView.findViewById(R.id.groceries_add);
            minus = itemView.findViewById(R.id.groceries_remove);
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
