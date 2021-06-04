package com.dyolmart.AdpterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyolmart.HelperClass.UserProfile;
import com.dyolmart.R;

import java.util.ArrayList;


public class CustomAdapter_Recycle extends RecyclerView.Adapter<com.dyolmart.AdpterClass.CustomAdapter_Recycle.viewHolder> {

    Context context;
    ArrayList<UserProfile> arrayList1;

    public CustomAdapter_Recycle(Context context, ArrayList<UserProfile> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }

    @Override
    public com.dyolmart.AdpterClass.CustomAdapter_Recycle.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate( R.layout.horizontal_homedesign2_form, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        viewHolder.btimg.setImageResource(arrayList1.get(position).getBtimages());
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        static ImageView btimg;


        public viewHolder (View itemView) {
            super(itemView);
            btimg = (ImageView) itemView.findViewById(R.id.rbtnimg);

        }
    }

}