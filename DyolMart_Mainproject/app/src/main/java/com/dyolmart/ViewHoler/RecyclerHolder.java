package com.dyolmart.ViewHoler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyolmart.Customer_Login;
import com.dyolmart.GrantPermission;
import com.dyolmart.HomeScreen;
import com.dyolmart.RequestPermissionWarning;
import com.dyolmart.ReviewRating;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.dyolmart.HelperClass.Userhipper;
import com.dyolmart.OrderDetailsActivity;
import com.dyolmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RecyclerHolder extends FirebaseRecyclerAdapter<Userhipper, RecyclerHolder.myview> {


    public RecyclerHolder(@NonNull FirebaseRecyclerOptions<Userhipper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myview holder, int position, @NonNull Userhipper model) {

        holder.t1.setText(model.getBname());
        holder.t2.setText(model.getPrice());
        holder.t3.setText(model.getName());
        holder.t4.setText(model.getEmail());
        holder.t5.setText(model.getPhoneno());
        holder.t6.setText(model.getLocality());
        holder.t9.setText(model.getFlatno());
        holder.t10.setText(model.getCity());
        holder.t11.setText(model.getState());
        holder.t7.setText(model.getPincode());
        holder.t8.setText(model.getShop_number());
        holder.t12.setText(model.getDate());
        holder.t13.setText(model.getQty());
        Glide.with(holder.img.getContext()).load(model.getImagelink()).into(holder.img);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), OrderDetailsActivity.class);
                i.putExtra("bname",model.getBname());
                i.putExtra("price",model.getPrice());
                i.putExtra("name",model.getName());
                i.putExtra("phone",model.getPhoneno());
                i.putExtra("address",model.getAddress());
                i.putExtra("flatnumber",model.getFlatno());
                i.putExtra("city",model.getCity());
                i.putExtra("state",model.getState());
                i.putExtra("pincode",model.getPincode());
                i.putExtra("image",model.getImagelink());
                i.putExtra("date",model.getDate());
                i.putExtra("shopid",model.getShop_number());
                i.putExtra("qty",model.getQty());
                v.getContext().startActivity(i);
            }
        });


    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
        TextView txt1= view.findViewById (R.id.product_title);
        TextView textView=view.findViewById (R.id.holdreview);
        textView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent ( v.getContext (), ReviewRating.class);
                intent.putExtra("bname",txt1.getText().toString());
                v.getContext ().startActivity (intent);
            }
        });
        return new myview(view);

    }

    public static class myview extends RecyclerView.ViewHolder {

        ImageView img;
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
        View view;

        public myview(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById( R.id.product_image);
            t1=itemView.findViewById( R.id.product_title);
            t3=itemView.findViewById( R.id.myorder_name);
            t2=itemView.findViewById( R.id.product_price);
            t4=itemView.findViewById( R.id.myorder_email);
            t5=itemView.findViewById( R.id.myorder_phonenumber);
            t6=itemView.findViewById( R.id.myorder_address);
            t7=itemView.findViewById( R.id.myorder_pincode);
            t8=itemView.findViewById( R.id.myorder_shopid);
            t9=itemView.findViewById( R.id.myorder_flatnum);
            t10=itemView.findViewById( R.id.myorder_city);
            t11=itemView.findViewById( R.id.myorder_state);
            t12=itemView.findViewById(R.id.date_time);
            t13=itemView.findViewById(R.id.item_qty);
            view = itemView;

        }

    }
}
