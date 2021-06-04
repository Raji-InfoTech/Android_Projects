package com.dyolmart.AllCatalogue;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.dyolmart.HelperClass.products;
import com.dyolmart.R;
import com.dyolmart.ViewHoler.ViewHolder_Watches;

public class Home_Watches extends AppCompatActivity {
    private DatabaseReference productsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ViewHolder_Watches viewHolder;
    ImageView imageView;
    SearchView searchView;
    ArrayList<products> list;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.home_watches);
        getSupportActionBar().setTitle(R.string.Watches);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productsref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("All_Products");
        query=productsref.orderByChild ("Category").equalTo ("Watches");
        recyclerView = findViewById( R.id.recyclerview_watches);
        searchView =  findViewById( R.id.Searchbar_watches);
        layoutManager = new GridLayoutManager (this,2);
        recyclerView.setLayoutManager(layoutManager);
        imageView=findViewById( R.id.image1);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(query != null){
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            list.add(ds.getValue( products.class));

                        }
                        viewHolder = new ViewHolder_Watches(list);
                        recyclerView.setAdapter(viewHolder);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }

            });
        }
    }

    private void search(String str){
        ArrayList<products> mlist = new ArrayList<>();
        for(products objects : list){
            if(objects.getBrand_Name().toLowerCase().contains(str.toLowerCase())){
                mlist.add(objects);
            }
        }
        viewHolder = new ViewHolder_Watches(mlist);
        recyclerView.setAdapter(viewHolder);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}