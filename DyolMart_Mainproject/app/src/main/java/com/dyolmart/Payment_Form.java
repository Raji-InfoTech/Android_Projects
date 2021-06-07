package com.dyolmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Payment_Form extends AppCompatActivity {
    ImageView image;
    Button imageView, change_or_add_address_btn;
    TextView txtbname, txtprice,  txtshopnum,text_qutyprice,total_price,total_items_price,quty_items;

    String images, price,qty,sp;
    private Spinner SpOc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_form);
        getSupportActionBar().setTitle(R.string.Orderdetails);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtbname = findViewById(R.id.list_item_name);
        txtprice = findViewById(R.id.list_item_price);
        txtshopnum = findViewById(R.id.shopnum);
        total_items_price=findViewById(R.id.total_items_price);
        total_price=findViewById(R.id.total_price);
        text_qutyprice=findViewById(R.id.text_qutyprice);
        quty_items=findViewById(R.id.quty_items);

        image = findViewById(R.id.image1);
        change_or_add_address_btn = findViewById(R.id.change_or_add_address_btn);

        images = getIntent().getStringExtra("image");
        Glide.with(com.dyolmart.Payment_Form.this).load(images).into(image);
        String shopnum = getIntent().getStringExtra("shopnumber");
        txtshopnum.setText(shopnum);
        String names = getIntent().getStringExtra("name");
        txtbname.setText(names);
        price = getIntent().getStringExtra("price");
        txtprice.setText("₹"+price);
        String item_size=getIntent().getStringExtra("itemSize");
        System.out.println("printed_item_size"+item_size);

        // spinner coding
        SpOc = findViewById(R.id.product_quantity);
        List<String> catagories = new ArrayList<>();
        catagories.add("1");
        catagories.add("2");
        catagories.add("3");
        catagories.add("4");
        catagories.add("5");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, catagories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpOc.setAdapter(dataAdapter);
        SpOc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp = SpOc.getItemAtPosition(SpOc.getSelectedItemPosition()).toString();
                System.out.println("added" + sp);
                qty = String.valueOf(Integer.valueOf(price) * Integer.valueOf(sp));
                System.out.println("suss" + qty);
                txtprice.setText("₹"+qty);
                text_qutyprice.setText("₹"+qty);
                total_items_price.setText("₹"+qty);
                total_price.setText("₹"+qty);
                quty_items.setText(sp+" items");

                //shared coding
                SharedPreferences sharedPref = getSharedPreferences("mydetailsamt", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString( "img1",images);
                editor.putString( "bname1",txtbname.getText().toString());
                editor.putString( "shopnum1",txtshopnum.getText().toString());
                editor.putString( "bprice1",qty);
                editor.putString("qty_items",sp);
                editor.putString("item_Size",item_size);
                System.out.println("successedraji"+qty);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageView = findViewById(R.id.btn_continue);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.dyolmart.RazorPay_Form.class);
                startActivity(intent);

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
