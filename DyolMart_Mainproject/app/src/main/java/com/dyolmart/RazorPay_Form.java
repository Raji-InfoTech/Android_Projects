package com.dyolmart;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import com.dyolmart.HelperClass.Userhipper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class RazorPay_Form extends AppCompatActivity implements PaymentResultListener {

    Button imageView,change_or_add_address_btn;
    TextView txtstatus,full_name,address,address_flatno,address_city,address_state,pincode,phone,alterphone,ordernumber,permentno,Brandname,bprice,snum,text_qutyprice,total_price,total_items_price,date;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Userhipper userhipper=new Userhipper();
    RadioButton rdbrzpay,rbbcod;
    String  value0,value3,value1,value2,value4,value5,Payment;
    ImageView img;

    //    RazorPay_Form
    private static final String TAG = com.dyolmart.RazorPay_Form.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.razorpay_form);
        Checkout.preload(getApplicationContext());
        getSupportActionBar().setTitle("Payments");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        date=findViewById(R.id.date);
        full_name=findViewById(R.id.full_name);
        address=findViewById(R.id.address_locality);
        address_flatno=findViewById(R.id.address_flatno);
        address_city=findViewById(R.id.address_city);
        address_state=findViewById(R.id.address_state);
        pincode=findViewById(R.id.pincode);
        phone=findViewById(R.id.phone);
        alterphone=findViewById(R.id.alterphone);
        imageView=findViewById(R.id.img_paymoney);
        rdbrzpay=findViewById( R.id.radio_Rzpay );
        rbbcod=findViewById( R.id.radio_cod );
        ordernumber=findViewById(R.id.ordernumber);
        txtstatus=findViewById(R.id.status);
        total_items_price=findViewById(R.id.total_items_price);
        total_price=findViewById(R.id.total_price);
        text_qutyprice=findViewById(R.id.text_qutyprice);
        permentno=findViewById( R.id.permentno );

        Brandname=findViewById(R.id.brandname);
        change_or_add_address_btn=findViewById(R.id.change_or_add_address_btn);
        permentno=findViewById(R.id.permentno);
        snum=findViewById(R.id.snum);
        img=findViewById(R.id.img);
        bprice=findViewById(R.id.bprice);


        change_or_add_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),MyAddressesActivity.class);
                startActivity(intent);
            }
        });

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh:mm:ss a");
        String s= simpleDateFormat.format(calendar.getTime());
        date.setText(s);


        // Login_id sharedpreferences
        SharedPreferences sharedPreferences =getApplicationContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        System.out.println("printed"+value);
        permentno.setText(value);


        // Product sharedpreferences
        SharedPreferences sharedPreferences1 =getApplicationContext(). getSharedPreferences("mydetailsamt", MODE_PRIVATE);
        value0 = sharedPreferences1.getString("img1","");
        value1 = sharedPreferences1.getString("bname1","");
        value2 = sharedPreferences1.getString("shopnum1","");
        value3 = sharedPreferences1.getString("bprice1","");
        value4 = sharedPreferences1.getString("qty_items","");
        value5 = sharedPreferences1.getString("item_Size","");
        System.out.println("printerddd"+value3);
        total_price.setText("₹"+value3);
        total_items_price.setText("₹"+value3);
        text_qutyprice.setText("₹"+value3);
        Glide.with(this).load(value0).into(img);
        Brandname.setText(value1);
        snum.setText(value2);


        //address sharedpreferences
        SharedPreferences sharedPreferences2 =getApplicationContext(). getSharedPreferences("shareaddress", MODE_PRIVATE);
        String valueadd1 = sharedPreferences2.getString("valueadd01","");
        String valueadd2 = sharedPreferences2.getString("valueadd02","");
        String valueadd3 = sharedPreferences2.getString("valueadd03","");
        String valueadd4 = sharedPreferences2.getString("valueadd04","");
        String valueadd5 = sharedPreferences2.getString("valueadd05","");
        String valueadd6 = sharedPreferences2.getString("valueadd06","");
        String valueadd7 = sharedPreferences2.getString("valueadd07","");
        String valueadd8 = sharedPreferences2.getString("valueadd08","");
        full_name.setText(valueadd1);
        address.setText(valueadd2);
        address_flatno.setText(valueadd3);
        address_city.setText(valueadd4);
        address_state.setText(valueadd5);
        pincode.setText(valueadd6);
        phone.setText(valueadd7);
        alterphone.setText(valueadd8);


        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Customer_Details/Order_Details");
        ordernumber.setText( generatenumber( ) );



        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy( policy );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RazorPay_Form.this);

                builder.setMessage("Are you sure to confirm your ordered?");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onRadioclickable();
                        SendSms.sendSms( value1+"order successfully" , phone.getText ().toString () );
                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "Sorry! your ordered canceled",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();

            }

        } );

    }


    public void onRadioclickable(){
        final int id = ((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        switch (id){
            case R.id.radio_Rzpay:{
                startPayment();
                break;
            }

            case R.id.radio_cod:{
                Payment="Cash_On_Delivery";
                getvalue();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        databaseReference.child(userhipper.getOrder_number()+(userhipper.getBname())).setValue(userhipper);
                        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

            }

        }
    }

    private String generatenumber() {

        String phoneNumber = permentno.getText().toString().trim();
        String strLastFourDi = phoneNumber.length() >= 5 ? phoneNumber.substring(phoneNumber.length() - 5): "";
        SharedPreferences sp = getSharedPreferences( "key_code", MODE_PRIVATE );
        int code = sp.getInt( "code", 10 );
        if (code <= 10) {
            code = 11;
        } else {
            code++;
        }
        sp.edit().putInt( "code", code ).commit();
        String newKey = "HyperMart"+strLastFourDi+ "0" + code;
        System.out.println("successfully"+newKey);
        return newKey;
    }
    private void getvalue(){
        userhipper.setName(full_name.getText().toString());
        userhipper.setPincode(pincode.getText().toString());
        userhipper.setLocality(address.getText().toString());
        userhipper.setFlatno(address_flatno.getText().toString());
        userhipper.setCity(address_city.getText().toString());
        userhipper.setState(address_state.getText().toString());
        userhipper.setPhoneno(phone.getText().toString());
        userhipper.setAltermbl( alterphone.getText().toString() );
        userhipper.setFor_me(permentno.getText().toString());
        userhipper.setBname(Brandname.getText().toString());
        userhipper.setPrice(text_qutyprice.getText().toString());
        userhipper.setOrder_number( ordernumber.getText().toString() );
        userhipper.setShop_number( snum.getText().toString());
        userhipper.setStatus( txtstatus.getText().toString() );
        userhipper.setImagelink( value0 );
        userhipper.setDate(date.getText().toString());
        userhipper.setQty(value4);
        userhipper.setItem_Size(value5);
        userhipper.setPayment_method(Payment);

    }


    public static class SendSms {

        public static void sendSms(String message, String number) {

            try {

                String apiKey = "uCLtGPNsq6aklzwT1ZWcRY5p8EegSOFBVmyX2407h3MijQDdvHGCJuUZgYdXN3RjopcQ6rtK1f8ADxmv";
                String sendId = "Dyolmart";
                //important step...
                message = URLEncoder.encode( message, "UTF-8" );
                String language = "english";

                String route = "p";


                String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId + "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + number;

                //sending get request using java..

                URL url = new URL( myUrl );

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();


                con.setRequestMethod( "GET" );

                con.setRequestProperty( "User-Agent", "Mozilla/5.0" );
                con.setRequestProperty( "cache-control", "no-cache" );
                System.out.println( "Wait.............." );

                int code = con.getResponseCode();

                System.out.println( "Response code : " + code );

                StringBuffer response = new StringBuffer();

                BufferedReader br = new BufferedReader( new InputStreamReader ( con.getInputStream() ) );

                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    response.append( line );
                }

                System.out.println( response );


            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
    }



     public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount",value3+"00"  );
            JSONObject preFill = new JSONObject();
            preFill.put("email", "");
            preFill.put("contact", phone.getText().toString());
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        try {
            Payment="Online_Payment";
            getvalue();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    databaseReference.child(userhipper.getOrder_number()+(userhipper.getBname())).setValue(userhipper);
                    Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(i);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }

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
