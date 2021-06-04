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

import java.util.ArrayList;

import com.dyolmart.HelperClass.products;
import com.dyolmart.R;

public class ViewHolder_Spares extends RecyclerView.Adapter<ViewHolder_Spares.myview> {

    ArrayList<products> list;


    public ViewHolder_Spares(ArrayList<products> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.list_item2,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        holder.t1.setText(list.get(position).getBrand_Name());
        holder.t2.setText(list.get(position).getSize_1_Price());
        holder.t3.setText(list.get(position).getProduct_Desc());
        Glide.with(holder.img.getContext()).load(list.get(position).getImg_url1()).into(holder.img);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),com.dyolmart.FindnextPage.Findnextpage_Spares.class);
                i.putExtra("",list.get( position ).getBrand_Name());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder {
        ImageView img;
        TextView t1,t2,t3;
        View view;
        public myview(@NonNull View itemView) {
            super( itemView );
            img=(ImageView) itemView.findViewById( R.id.image1);
            t1=(TextView)itemView.findViewById( R.id.list_item_name);
            t3=(TextView)itemView.findViewById( R.id.list_item_Desc);
            t2=(TextView)itemView.findViewById( R.id.list_item_price);
            view = itemView;
        }
    }
}