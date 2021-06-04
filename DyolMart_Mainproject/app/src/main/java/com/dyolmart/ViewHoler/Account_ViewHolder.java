package com.dyolmart.ViewHoler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.dyolmart.HelperClass.Userhipper;
import com.dyolmart.R;

public class Account_ViewHolder extends RecyclerView.Adapter<Account_ViewHolder.myview> {

    private ArrayList<Userhipper> list;
    private int checkedPosition = 0;

    public Account_ViewHolder(ArrayList<Userhipper> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.addresses_item_layout,parent,false);
     /*   SharedPreferences sharedPref = view.getContext().getSharedPreferences("account", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString( "value",Userhipper.class.getName());
        editor.commit();*/
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {

        holder.fullName.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getLocality()+"\n"+list.get(position).getFlatno()+"\n"+list.get(position).getCity());
        holder.pincode.setText(list.get(position).getState()+"\n"+list.get(position).getPincode());
        holder.mblnum.setText(list.get(position).getPhoneno());
        holder.alternum.setText(list.get(position).getAltermbl());
        holder.bind(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();

    }

    public class myview extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView address;
        TextView pincode,mblnum,alternum;
        RecyclerView deliveryRecyclerView;
        ImageView iconview;
        View view;

        public myview(@NonNull View itemView) {
            super( itemView );
            fullName = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            deliveryRecyclerView =itemView.findViewById(R.id.delivery_recyclerview);
            mblnum=itemView.findViewById(R.id.mblnumber);
            alternum=itemView.findViewById(R.id.alternum);
            iconview=itemView.findViewById(R.id.icon_view);
            view = itemView;
        }
        void bind(final Userhipper employee) {
            if (checkedPosition == -1) {
                iconview.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    iconview.setVisibility(View.VISIBLE);
                    iconview.setImageResource(R.drawable.check1);

                } else {
                    iconview.setVisibility(View.GONE);
                }
            }
            fullName.setText(employee.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iconview.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }
    public Userhipper getSelected() {
        if (checkedPosition != -1) {
            return list.get(checkedPosition);
        }
        return null;
    }

}



















