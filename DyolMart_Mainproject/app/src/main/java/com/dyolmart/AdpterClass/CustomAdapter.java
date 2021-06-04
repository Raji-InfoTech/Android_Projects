package com.dyolmart.AdpterClass;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyolmart.AllCatalogue.Home_Accessories;
import com.dyolmart.AllCatalogue.Home_Beauty;
import com.dyolmart.AllCatalogue.Home_Books;
import com.dyolmart.AllCatalogue.Home_Fashion;
import com.dyolmart.AllCatalogue.Home_Lappc;
import com.dyolmart.AllCatalogue.Home_Mobile;
import com.dyolmart.AllCatalogue.Home_Shoes;
import com.dyolmart.AllCatalogue.Home_Spare;
import com.dyolmart.AllCatalogue.Home_Sports;
import com.dyolmart.AllCatalogue.Home_Watches;
import com.dyolmart.FragmentPages.Fragment_Home;
import com.dyolmart.HelperClass.UserProfile;
import com.dyolmart.R;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

    Context context;
    ArrayList<UserProfile> arrayList;

    public CustomAdapter(Context context, ArrayList<UserProfile> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate( R.layout.horizonal_homedesin_form, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        viewHolder.iconName.setText(arrayList.get(position).getHtname());
        viewHolder.icon.setImageResource(arrayList.get(position).getImage());
       viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                if (arrayList.get(position).getHtname().equals("Accessories")){
                    intent =  new Intent(context, Home_Accessories.class);
                } else if (arrayList.get(position).getHtname().equals("Beauty")){
                    intent =  new Intent(context, Home_Beauty.class);
                } else if (arrayList.get(position).getHtname().equals("Books")){
                    intent =  new Intent(context, Home_Books.class);
                }else if (arrayList.get(position).getHtname().equals("Fashion")){
                    intent =  new Intent(context, Home_Fashion.class);
                }else if (arrayList.get(position).getHtname().equals("Laptops")){
                    intent =  new Intent(context, Home_Lappc.class);
                }else if (arrayList.get(position).getHtname().equals("Mobiles")){
                    intent =  new Intent(context, Home_Mobile.class);
                }else if (arrayList.get(position).getHtname().equals("Footwear")){
                    intent =  new Intent(context, Home_Shoes.class);
                }else if (arrayList.get(position).getHtname().equals("Watches")){
                    intent =  new Intent(context, Home_Watches.class);
                }else if (arrayList.get(position).getHtname().equals("SpareParts")){
                    intent =  new Intent(context, Home_Spare.class);
                }else if (arrayList.get(position).getHtname().equals("Sports")){
                    intent =  new Intent(context, Home_Sports.class);
                }
                else {
                    intent =  new Intent(context, Fragment_Home.class);
                }
                context.startActivity(intent);
            }
            
        } );



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        static ImageView icon;
        static TextView iconName;

        public viewHolder (View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById( R.id.icon);
            iconName = (TextView) itemView.findViewById( R.id.icon_name);

        }
    }

}
