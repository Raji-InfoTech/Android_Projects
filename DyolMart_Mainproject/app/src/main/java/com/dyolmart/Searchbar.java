package com.dyolmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.dyolmart.HelperClass.products;
import com.dyolmart.ViewHoler.Commonsearch_ViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;

public class Searchbar extends AppCompatActivity {
    private DatabaseReference productsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Commonsearch_ViewHolder viewHolder;
    ImageView imageView;
    SearchView searchView;
    ArrayList<products> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_searchbar);
        productsref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("All_Products");
        recyclerView = findViewById( R.id.recyclerviewsearch1);
        searchView =  findViewById( R.id.Searchbar1);
        layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager(layoutManager);
        imageView=findViewById( R.id.image1);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(productsref != null){
            productsref.addValueEventListener(new ValueEventListener () {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            list.add(ds.getValue( products.class));

                        }
                        viewHolder = new Commonsearch_ViewHolder(list);
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
                    recyclerView.setVisibility (View.VISIBLE);
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
        viewHolder = new Commonsearch_ViewHolder(mlist);
        recyclerView.setVisibility (INVISIBLE);
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