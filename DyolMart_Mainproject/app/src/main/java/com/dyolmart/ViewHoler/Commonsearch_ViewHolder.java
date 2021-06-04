package com.dyolmart.ViewHoler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyolmart.HelperClass.products;
import com.dyolmart.R;

import java.util.ArrayList;

public class Commonsearch_ViewHolder extends RecyclerView.Adapter<Commonsearch_ViewHolder.myview> {
    ArrayList<products> list;

    public Commonsearch_ViewHolder(ArrayList<products> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Commonsearch_ViewHolder.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ( )).inflate (R.layout.list_item, parent, false);
        return new Commonsearch_ViewHolder.myview (view);
    }

    @Override
    public void onBindViewHolder(@NonNull Commonsearch_ViewHolder.myview holder, int position) {
        holder.t1.setText (list.get (position).getBrand_Name ( ));
        holder.t2.setText (list.get (position).getSize_1_Price ( ));
        holder.t3.setText (list.get (position).getProduct_Desc ( ));
        holder.t4.setText (list.get (position).getShop_ID ( ));

        Glide.with (holder.img.getContext ( )).load (list.get (position).getImg_url1 ( )).into (holder.img);

        holder.view.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (v.getContext ( ), com.dyolmart.Homebannerad_Shop.class);
                i.putExtra ("", list.get (position).getBrand_Name ( ));
                i.putExtra ("price", list.get (position).getSize_1_Price ( ));
                i.putExtra ("Client_shopnumber", list.get (position).getShop_ID ( ));
                i.putExtra ("imageurl", list.get (position).getImg_url1 ( ));
                v.getContext ( ).startActivity (i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ( );
    }

    public class myview extends RecyclerView.ViewHolder {
        ImageView img;
        TextView t1, t2, t3, t4;
        View view;

        public myview(@NonNull View itemView) {
            super (itemView);
            img = (ImageView) itemView.findViewById (R.id.image1);
            t1 = (TextView) itemView.findViewById (R.id.list_item_name);
            t3 = (TextView) itemView.findViewById (R.id.list_item_Desc);
            t2 = (TextView) itemView.findViewById (R.id.list_item_price);
            t4 = itemView.findViewById (R.id.list_item_shopnum);
            view = itemView;
        }
    }
}