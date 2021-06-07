package com.dyolmart;



import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    TextView txttitle,prices,name,address,phonenumber,pincode,totprices,txttotprice,address_flatno,alterphone,address_city,ordered_date,address_state,
             txtpacked,txtshipped,txtdelivered,txtqty;


    String txttle,shop_id,order_id,payment_mode;
    DatabaseReference databaseReference;
    LinearLayout ll;
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
        txtqty=findViewById(R.id.product_quantity);
        ll=findViewById(R.id.invoice);

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
        String customeradd= Name+"\n"+Address+"\n"+flat+"\n"+citys+"\n"+states+","+Pincode+"\n"+Phonenumber;
        ordered_date.setText(date);
        String qty=getIntent().getStringExtra("qty");
        txtqty.setText("Qty : "+qty);
        shop_id=getIntent().getStringExtra("shopid");
        System.out.println("showid"+shop_id);
        SharedPreferences sharedPref =getApplicationContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPref.getString("value","");
        System.out.println("printed"+value);


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
        txtdelivered=findViewById(R.id.delivered_body);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Customer_Details/Order_Details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    com.dyolmart.HelperClass.Userhipper prdct = dataSnapshot.getValue(com.dyolmart.HelperClass.Userhipper.class);
                    String s = prdct.getStatus();
                    String bname=prdct.getBname();
                    String login_id=prdct.getFor_me();
                    order_id=prdct.getOrder_number();
                    payment_mode=prdct.getPayment_method();
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
                        ll.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Invoice.class);
                i.putExtra("order_id",order_id);
                i.putExtra("order_date",date);
                i.putExtra("shop_id",shop_id);
                i.putExtra("customer_add",customeradd);
                i.putExtra("product_name",txttle);
                i.putExtra("quantity",qty);
                i.putExtra("price",price);
                i.putExtra("Payment_Mode",payment_mode);
                startActivity(i);
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
