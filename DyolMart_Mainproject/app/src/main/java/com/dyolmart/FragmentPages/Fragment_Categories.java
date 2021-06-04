package com.dyolmart.FragmentPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.dyolmart.R;

public class Fragment_Categories extends Fragment {

    private DatabaseReference databaseReference;
    CardView fashion,watches,shoes,lap_pc,spare,mobile,sports,beauty,accessories,books;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_categories, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        fashion=root.findViewById(R.id.cardfashion);
        watches=root.findViewById(R.id.cardWatches);
        shoes=root.findViewById(R.id.cardshoe);
        lap_pc=root.findViewById(R.id.cardlapcp);
        mobile=root.findViewById(R.id.cardmobile);
        sports=root.findViewById(R.id.cardsports);
        spare=root.findViewById(R.id.cardvecyparts);
        beauty=root.findViewById(R.id.cardbeauty);
        accessories=root.findViewById(R.id.cardAccessories);
        books=root.findViewById(R.id.cardbook);

        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Fashion.class);
                startActivity(intent);
            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Watches.class);
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(),com.dyolmart.AllCatalogue.Home_Shoes.class);
                startActivity(intent);
            }
        });
        lap_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Lappc.class);
                startActivity(intent);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(),com.dyolmart.AllCatalogue.Home_Mobile.class);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Sports.class);
                startActivity(intent);
            }
        });
        spare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Spare.class);
                startActivity(intent);
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Beauty.class);
                startActivity(intent);
            }
        });
        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(), com.dyolmart.AllCatalogue.Home_Accessories.class);
                startActivity(intent);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef=database.getReference("");
                Intent intent=new Intent(getContext(),com.dyolmart.AllCatalogue.Home_Books.class);
                startActivity(intent);
            }
        });
           return root;
    }
}
