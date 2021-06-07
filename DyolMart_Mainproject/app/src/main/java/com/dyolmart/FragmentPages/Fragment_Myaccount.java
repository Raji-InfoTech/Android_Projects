package com.dyolmart.FragmentPages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import com.dyolmart.Customer_Login;
import com.dyolmart.R;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_Myaccount extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private Button mLogoutBtn;
    DatabaseReference productsref;


    public Fragment_Myaccount() {
    }


    TextView name,address,pincode,phone,altnumber,flat,city,state,textView_Wishlist,textView_Cart,textView_Order,textView_Helpcenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.myaccount, container, false);



            name=root.findViewById(R.id.address_full_name);
            address=root.findViewById(R.id.address);
            flat=root.findViewById(R.id.flat);
            city=root.findViewById(R.id.city);
            state=root.findViewById(R.id.state);
            pincode=root.findViewById(R.id.address_pincode);
            phone= root.findViewById(R.id.Phonenum);
            altnumber=root.findViewById(R.id.Altermblnum);
            textView_Wishlist=root.findViewById(R.id.mywishlist);
            textView_Cart=root.findViewById(R.id.mycart);
            textView_Order=root.findViewById(R.id.myorder);


           // button=root.findViewById(R.id.view_all_addresses_btn);


            mAuth = FirebaseAuth.getInstance();
            mCurrentUser = mAuth.getCurrentUser();
            mAuth = FirebaseAuth.getInstance();
            mCurrentUser = mAuth.getCurrentUser();


        mLogoutBtn = root.findViewById(R.id.logout);

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                sendUserToLogin();

            }
        });


        SharedPreferences sharedPreferences =getContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        System.out.println("succfully printed"+value);


        SharedPreferences sharedPreferences1 =getContext(). getSharedPreferences("shareaddress", MODE_PRIVATE);
        String value1 = sharedPreferences1.getString("valueadd01","");
        String value2 = sharedPreferences1.getString("valueadd02","");
        String value3 = sharedPreferences1.getString("valueadd03","");
        String value4 = sharedPreferences1.getString("valueadd04","");
        String value5 = sharedPreferences1.getString("valueadd05","");
        String value6 = sharedPreferences1.getString("valueadd06","");
        String value7 = sharedPreferences1.getString("valueadd07","");
        String value8 = sharedPreferences1.getString("valueadd08","");
        System.out.println("printed"+value1);
        name.setText(value1);
        address.setText(value2);
        flat.setText(value3);
        city.setText(value4);
        state.setText(value5);
        pincode.setText(value6);
        phone.setText(value7);
        altnumber.setText(value8);
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        if(mCurrentUser == null){
            sendUserToLogin();
        }
    }

    private void sendUserToLogin() {
        Intent loginIntent = new Intent( getContext(), Customer_Login.class);
        loginIntent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }
}