package com.dyolmart;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.dyolmart.HelperClass.Userhipper;
import com.dyolmart.ViewHoler.Account_ViewHolder;


public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myaddressesRecyclerView;
    private Button deliverHereBtn;
    TextView textView24;
    DatabaseReference productsref;
    RecyclerView.LayoutManager layoutManager;
    Account_ViewHolder viewHolder;
    Query query;
    ArrayList<Userhipper> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        getSupportActionBar().setTitle(R.string.address);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* SharedPreferences sharedPreferences1 =getApplicationContext(). getSharedPreferences("mydetails", MODE_PRIVATE);
        String value0 = sharedPreferences1.getString("img1","");
        String value1 = sharedPreferences1.getString("bname1","");
        String value2 = sharedPreferences1.getString("shopnum1","");
        String value3 = sharedPreferences1.getString("bprice1","");*/



        textView24 = findViewById(R.id.textView24);
        deliverHereBtn = findViewById(R.id.deliver_here_btn);
        textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddAddressActivity.class);
                startActivity(i);
            }
        });



       deliverHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.dyolmart.RazorPay_Form.class);
                SharedPreferences sharedPref = getSharedPreferences("shareaddress", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString( "valueadd01",viewHolder.getSelected().getName());
                editor.putString( "valueadd02",viewHolder.getSelected().getLocality());
                editor.putString( "valueadd03",viewHolder.getSelected().getFlatno());
                editor.putString( "valueadd04",viewHolder.getSelected().getCity());
                editor.putString( "valueadd05",viewHolder.getSelected().getState());
                editor.putString( "valueadd06", viewHolder.getSelected().getPincode());
                editor.putString( "valueadd07",viewHolder.getSelected().getPhoneno());
                editor.putString( "valueadd08",viewHolder.getSelected().getAltermbl());
               /* editor.putString("img2",value0);
                editor.putString("bname2",value1);
                editor.putString("shopnum2",value2);
                editor.putString("bprice2",value3);*/
                editor.commit();
               /* i.putExtra("Name",viewHolder.getSelected().getName());
                i.putExtra("Locality",viewHolder.getSelected().getLocality() );
                i.putExtra("Flatno",viewHolder.getSelected().getFlatno());
                i.putExtra("City", viewHolder.getSelected().getCity());
                i.putExtra("state", viewHolder.getSelected().getState());
                i.putExtra("Pincode",  viewHolder.getSelected().getPincode());
                i.putExtra("Mblnum",viewHolder.getSelected().getPhoneno() );
                i.putExtra("alternum",  viewHolder.getSelected().getAltermbl());
                i.putExtra("img01",value0);
                i.putExtra("bname01",value1);
                i.putExtra("shopnum01",value2);
                i.putExtra("bprice01",value3);*/
                startActivity(i);

            }
        });

        myaddressesRecyclerView = findViewById(R.id.addresses_recyclerview);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value", "");
        System.out.println("printed" + value);
        productsref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Customer_Details/MyAccount_Details");
        query = productsref.orderByChild("for_me").equalTo(value);
        layoutManager = new LinearLayoutManager (this);
        myaddressesRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                            list.add(ds.getValue( Userhipper.class));
                            

                        }
                        viewHolder = new Account_ViewHolder(list);
                        myaddressesRecyclerView.setAdapter(viewHolder);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
