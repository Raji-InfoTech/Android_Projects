package com.dyolmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.dyolmart.HelperClass.Userhipper;

import static com.dyolmart.GrantPermission.activity;


public class    AddAddressActivity extends AppCompatActivity {


    private Button saveBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText name,alternatemobileno,state,pincode,city,locality,flatno,landmark,codee,mobilenumber;
    Userhipper userhipper=new Userhipper();
    TextView textView;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar =findViewById(R.id.toolbar);
        name =findViewById(R.id.name);
        alternatemobileno =findViewById(R.id.alternate_mobile_no);
        state =findViewById(R.id.state);
        pincode=findViewById(R.id.pincode);
        city =findViewById(R.id.city);
        locality =findViewById(R.id.locality);
        flatno =findViewById(R.id.flat_no);
        landmark =findViewById(R.id.landmark);
        codee = findViewById(R.id.codee);
        mobilenumber=findViewById(R.id.mobile_no);
        textView = findViewById(R.id.mobile_no1);
        codee.setText(generatenumber());
        SharedPreferences sharedPreferences =getApplicationContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        System.out.println("succfully printed"+value);
        textView.setText(value);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Account details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saveBtn = findViewById(R.id.save_btn);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Customer_Details/MyAccount_Details");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getvalue();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        databaseReference.child(userhipper.getGenerate ()).setValue(userhipper);
                        Toast.makeText(com.dyolmart.AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), com.dyolmart.MyAddressesActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });
    }
    private void getvalue(){
        userhipper.setName(name.getText().toString());
        userhipper.setLocality(locality.getText().toString());
        userhipper.setFlatno(flatno.getText().toString());
        userhipper.setPincode(pincode.getText().toString());
        userhipper.setState(state.getText().toString());
        userhipper.setPhoneno(mobilenumber.getText().toString());
        userhipper.setAltermbl( alternatemobileno.getText().toString() );
        userhipper.setCity( city.getText().toString() );
        userhipper.setLandmark( landmark.getText().toString() );
        userhipper.setGenerate( codee.getText().toString() );
        userhipper.setFor_me( textView.getText().toString() );

    }
    private String generatenumber() {
        textView=findViewById( R.id.mobile_no1 );
//        String phoneNumber = mobileno.getText().toString().trim();
//        String strLastFourDi = phoneNumber.length() >= 5 ? phoneNumber.substring(phoneNumber.length() - 5): "";
        SharedPreferences sp = getSharedPreferences( "key_code", MODE_PRIVATE );
        int code = sp.getInt( "code", 1 );
        if (code <= 1) {
            code = 2;
        } else {
            code++;
        }
        sp.edit().putInt( "code", code ).commit();
        String newKey = "HyperMart"+ code;
        System.out.println("successfully"+newKey);
        return newKey;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
