package com.dyolmart.ViewHoler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.dyolmart.HelperClass.products;
import com.dyolmart.Homebannerad_Shop;
import com.dyolmart.R;

import static android.content.Context.MODE_PRIVATE;

public class Homescreenholder1 extends FirebaseRecyclerAdapter<products, Homescreenholder1.myview> {

    public Homescreenholder1(@NonNull FirebaseRecyclerOptions<products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myview holder, int position, @NonNull products model) {
        holder.t1.setText(model.getBrand_Name());
        holder.t2.setText(model.getSize_1_Price ());
        holder.t3.setText(model.getShop_ID());
        Glide.with(holder.img.getContext()).load(model.getImg_url1()).into(holder.img);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), com.dyolmart.Homebannerad_Shop.class);
                i.putExtra("",model.getBrand_Name());
                i.putExtra("price",model.getSize_1_Price ());
                i.putExtra( "Client_shopnumber",model.getShop_ID() );
                i.putExtra( "imageurl",model.getImg_url1() );
                v.getContext().startActivity(i);
            }
        });


    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout,parent,false);
        return new myview(view);
    }

    public class myview extends RecyclerView.ViewHolder {

        ImageView img;
        TextView t1, t2, t3;
        View view;


        public myview(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.homeimg);
            t1 = (TextView) itemView.findViewById(R.id.hometxtbname);
            t2 = (TextView) itemView.findViewById(R.id.hometxtprice);
            t3 = (TextView) itemView.findViewById(R.id.hometxtshopid);
            view = itemView;

        }
    }
}