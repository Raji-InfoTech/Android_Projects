package com.dyolmart.FragmentPages;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.dyolmart.HelperClass.Userhipper;
import com.dyolmart.R;
import com.dyolmart.ViewHoler.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_Myorder extends Fragment {
    DatabaseReference productsref;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerHolder viewHolder;
    ImageView imageView;
    Query query;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_myorder, container, false);
        recyclerView = root.findViewById( R.id.my_orders_recyclerview);
        SharedPreferences sharedPreferences =getContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        System.out.println("printed"+value);
        productsref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Customer_Details/Order_Details");
        query=productsref.orderByChild("for_me").equalTo(value);
        layoutManager = new LinearLayoutManager (getContext());
        recyclerView.setLayoutManager(layoutManager);
        imageView=root.findViewById(R.id.image1);
        FirebaseRecyclerOptions<Userhipper> options =new FirebaseRecyclerOptions.Builder<Userhipper>().setQuery(query,Userhipper.class).build();
        viewHolder = new RecyclerHolder(options);
        recyclerView.setAdapter(viewHolder);
        return root;
    }
    @Override
    public void onStart(){
        super.onStart();
        viewHolder.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        viewHolder.stopListening();

    }

}