package com.dyolmart;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView policy;
    Button Continue;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        policy=findViewById(R.id.txt_terms_condition);
        Continue=findViewById(R.id.btn_Continue);
        firebaseAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(1);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestPermissionWarning objGP=new RequestPermissionWarning();
                Boolean result=objGP.checkPermissions(getApplicationContext());
                if (!result) {
                    Intent intent = new Intent(getApplicationContext(), GrantPermission.class);
                    startActivity(intent);
                }
                else {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Customer_Login.class);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null){

        }
        else{
            Intent mainintent = new Intent(com.dyolmart.MainActivity.this,HomeScreen.class);
            startActivity(mainintent);
            finish();
        }
    }
}