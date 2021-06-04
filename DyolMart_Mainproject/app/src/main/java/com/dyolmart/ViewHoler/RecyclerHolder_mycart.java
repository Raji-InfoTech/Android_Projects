package com.dyolmart.ViewHoler;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyolmart.HelperClass.Userhipper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.dyolmart.HelperClass.products;
import com.dyolmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerHolder_mycart extends FirebaseRecyclerAdapter<products, RecyclerHolder.myview> {
    private int checkedPosition = 0;
    private ArrayList<Userhipper> list;
    public RecyclerHolder_mycart(@NonNull FirebaseRecyclerOptions<products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerHolder.myview holder, int position, @NonNull products model) {
        holder.t1.setText(model.getBrand_Name());
        holder.t2.setText(model.getCommon_price());
        holder.t8.setText(model.getShop_ID());

        Glide.with(holder.img.getContext()).load(model.getImg_url1()).into(holder.img);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), com.dyolmart.Findnextpage_Mycart.class);
                i.putExtra("",model.getBrand_Name());
                i.putExtra("price",model.getCommon_price());
                i.putExtra( "Client_shopnumber",model.getShop_ID() );
                i.putExtra( "imageurl",model.getImg_url1() );
                v.getContext().startActivity(i);
            }
        });

    }

    @NonNull
    @Override
    public RecyclerHolder.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewdesign,parent,false);
        /*TextView textView=view.findViewById (R.id.remove_mycart);
        textView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                DatabaseReference data= FirebaseDatabase.getInstance ().getReference ().child ("Customer_Details/Mycart");
                data.removeValue ();
            }
        });*/

        return new RecyclerHolder.myview(view);

    }

    public class myview extends RecyclerView.ViewHolder {

        ImageView img;
        TextView t1, t2, t3, t4, t5, t6, t7, t8,remove;
        View view;


        public myview(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.product_image);
            t1 = (TextView) itemView.findViewById(R.id.product_title);
           // t3 = (TextView) itemView.findViewById(R.id.remove_mycart);
            t2 = (TextView) itemView.findViewById(R.id.product_price);
           // remove=(TextView) itemView.findViewById (R.id.remove_mycart);
            /*t4 = itemView.findViewById(R.id.myorder_email);
            t5 = itemView.findViewById(R.id.myorder_phonenumber);
            t6 = itemView.findViewById(R.id.myorder_address);
            t7 = itemView.findViewById(R.id.myorder_pincode);
            t8 = itemView.findViewById(R.id.myorder_shopid);*/
            view = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });


        }



    }
   /* public Userhipper getSelected() {
        if (checkedPosition != -1) {
            return list.get(checkedPosition);
        }
        return null;
    }*/
}
