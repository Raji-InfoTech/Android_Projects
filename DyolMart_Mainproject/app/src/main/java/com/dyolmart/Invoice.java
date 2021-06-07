package com.dyolmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Invoice extends AppCompatActivity {

    TextView txtorderid,txtorderdate,txtclientaddress,txtcustomeraddress,txttotalprice,txtproduct,txtquantity,txtprice,txtpayment;
    String orderid,orderdate,shopid,address,product,qty,price,payment_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        txtorderid=findViewById(R.id.txt_orderid);
        txtorderdate=findViewById(R.id.txt_orderdate);
        txtclientaddress=findViewById(R.id.txt_clientAddress);
        txtcustomeraddress=findViewById(R.id.txt_customerAddress);
        txttotalprice=findViewById(R.id.txt_totprice);
        txtproduct=findViewById(R.id.txt_product);
        txtquantity=findViewById(R.id.txt_quantity);
        txtprice=findViewById(R.id.txt_price);
        txtpayment=findViewById(R.id.txt_paymentmode);

        orderid=getIntent().getStringExtra("order_id");
        orderdate=getIntent().getStringExtra("order_date");
        shopid=getIntent().getStringExtra("shop_id");
        address=getIntent().getStringExtra("customer_add");
        product=getIntent().getStringExtra("product_name");
        qty=getIntent().getStringExtra("quantity");
        price=getIntent().getStringExtra("price");
        payment_mode=getIntent().getStringExtra("Payment_Mode");

        txtorderid.setText(orderid);
        txtorderdate.setText(orderdate);
        txtcustomeraddress.setText(address);
        txttotalprice.setText("₹"+price);
        txtprice.setText(price);
        txtproduct.setText(product);
        txtquantity.setText(qty);
        txtpayment.setText(payment_mode);
        txtclientaddress.setText(shopid);


    }
}