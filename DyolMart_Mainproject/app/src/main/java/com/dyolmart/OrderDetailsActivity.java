package com.dyolmart;



import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailsActivity extends AppCompatActivity {
    ImageView img,image_Ordered,image_Packed,image_Shipping,image_delivered;
    ProgressBar progress_Ordered,progress_Packed,progress_shipping;
    TextView textView,txttitle,prices,name,address,phonenumber,pincode,totprices,txttotprice,address_flatno,alterphone,address_city,ordered_date,address_state,
             txtpacked,txtshipped,txtdelivered,txtqty;
    RatingBar ratingBar;
    float ratevalue;
    String ratingbar1,ratingbar2,ratingbar3,ratingbar4,ratingbar5,txttle,shop_id;
    DatabaseReference databaseReference;
    Button button;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        getSupportActionBar().setTitle(R.string.Orderdetails);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        img=findViewById(R.id.product_image);
        txttitle=findViewById(R.id.product_title);
        prices=findViewById(R.id.product_price);
        name=findViewById(R.id.full_name);
        address=findViewById(R.id.address_locality);
        address_flatno=findViewById(R.id.address_flatno);
        address_city=findViewById(R.id.address_city);
        address_state=findViewById(R.id.address_state);
        phonenumber=findViewById(R.id.phone);
        alterphone=findViewById(R.id.alterphone);
        pincode=findViewById(R.id.pincode);
        totprices=findViewById(R.id.total_items_price);
        txttotprice=findViewById(R.id.total_price);
        ordered_date=findViewById (R.id.ordered_date);
        ratingBar= findViewById(R.id.rating);
        textView= findViewById(R.id.ratingcount);
        txtqty=findViewById(R.id.product_quantity);

        txttle=getIntent().getStringExtra("bname");
        txttitle.setText(txttle);
        String price=getIntent().getStringExtra("price");
        prices.setText(price);
        totprices.setText(price);
        txttotprice.setText(price);
        String imgs=getIntent().getStringExtra("image");
        Glide.with(getApplicationContext()).load(imgs).into(img);
        String Name=getIntent().getStringExtra("name");
        name.setText(Name);
        String Address=getIntent().getStringExtra("address");
        address.setText(Address);
        String flat=getIntent().getStringExtra("flatnumber");
        address_flatno.setText(flat);
        String citys=getIntent().getStringExtra("city");
        address_city.setText(citys);
        String states=getIntent().getStringExtra("state");
        address_state.setText(states);
        String Pincode=getIntent().getStringExtra("pincode");
        pincode.setText(Pincode);
        String Phonenumber=getIntent().getStringExtra("phone");
        phonenumber.setText(Phonenumber);
        String date=getIntent().getStringExtra("date");
        ordered_date.setText(date);
        String qty=getIntent().getStringExtra("qty");
        txtqty.setText("Qty : "+qty);
        shop_id=getIntent().getStringExtra("shopid");
        System.out.println("showid"+shop_id);
        SharedPreferences sharedPref =getApplicationContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPref.getString("value","");
        System.out.println("printed"+value);

       /* databaseReference = firebaseDatabase.getReference("Test2/Accessories");
        query=databaseReference.orderByChild("Brand_Name").equalTo(txttle);*/

        //Rating count

        SharedPreferences sharedPreferences =getApplicationContext(). getSharedPreferences("Ratingvalues", MODE_PRIVATE);
         ratingbar1 = sharedPreferences.getString("Rating1","");
         ratingbar2 = sharedPreferences.getString("Rating2","");
         ratingbar3 = sharedPreferences.getString("Rating3","");
         ratingbar4 = sharedPreferences.getString("Rating4","");
         ratingbar5 = sharedPreferences.getString("Rating5","");
         // System.out.println ("karthik"+ratingbar1 );

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratevalue = ratingBar.getRating();
                int name = Math.round(ratevalue);
                textView.setText( name+"");
               /*
                query.addValueEventListener (new ValueEventListener ( ) {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DatabaseReference db=databaseReference.child ("Rating_1");
                        db.setValue (name, new DatabaseReference.CompletionListener ( ) {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText (OrderDetailsActivity.this, "updatesuccess", Toast.LENGTH_SHORT).show ( );
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });

        //Tracking Process

        image_Ordered=findViewById(R.id.ordered_indicator);
        image_Ordered.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        image_Packed=findViewById(R.id.packed_indicator);
        image_Shipping=findViewById(R.id.shipping_indicator);
        image_delivered=findViewById(R.id.delivered_indicator);
        progress_Ordered=findViewById(R.id.ordered_packed_progress);
        progress_Packed=findViewById(R.id.packed_shipping_progress);
        progress_shipping=findViewById(R.id.shipping_delivered_progress);
        txtpacked=findViewById(R.id.packed_body);
        txtshipped=findViewById(R.id.shipping_body);
        txtdelivered=findViewById(R.id.ordered_body);
        button=findViewById(R.id.btn_invoice);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Customer_Details/Order_Details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    com.dyolmart.HelperClass.Userhipper prdct = dataSnapshot.getValue(com.dyolmart.HelperClass.Userhipper.class);
                    String s = prdct.getStatus();
                    String bname=prdct.getBname();
                    String login_id=prdct.getFor_me();
                    if(login_id.equals(value) && bname.equals(txttle) && s.equals("Order-Packed")){
                        progress_Ordered.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_Packed.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtpacked.setVisibility(View.VISIBLE);
                    }
                    else if(login_id.equals(value) && bname.equals(txttle) && s.equals("Order-Shipped")){
                        progress_Ordered.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_Packed.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtpacked.setVisibility(View.VISIBLE);
                        progress_Packed.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_Shipping.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtshipped.setVisibility(View.VISIBLE);
                    }
                    else if(login_id.equals(value) && bname.equals(txttle) && s.equals("Order-Delivered")){
                        progress_Ordered.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_Packed.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtpacked.setVisibility(View.VISIBLE);
                        progress_Packed.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_Shipping.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtshipped.setVisibility(View.VISIBLE);
                        progress_shipping.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        image_delivered.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        txtdelivered.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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
