package com.dyolmart;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class RequestPermissionWarning extends AppCompatActivity {
    String warning="";
    Button btnOK;
    Intent inte;
    List<String> listPermissionsNeeded = new ArrayList<>();

    String permission1="",permission2="",permission3="",permission4="",permission5="";
    String[] permissions = new String[]{
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS
    }; // Here i used multiple permission check

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_permission_warning);

        //To display popup window
        DisplayMetrics dn = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dn);
        int width = dn.widthPixels;
        int height = dn.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        //retrieve textview control to display received message
        TextView txtViewMsg = findViewById(R.id.txtMsg);

        inte= getIntent();
        if (inte.getStringExtra("NeverAsk").equals("false")) {
            if (!checkPermissions(getApplicationContext())) {

                String[] str = inte.getStringExtra("Permissions").split("\n");
                warning = "The following permissions are needed to install this app.Kindly allow us to help you \n\n";
                for (int i = 0; i < str.length; i++)
                    switch (i) {
                        case 0:
                        case 1:
                        case 2:
                            permission1 = "SMS";
                            break;
                        case 3:
                        case 4:
                            permission2 = "Location";
                            break;
                        case 5:
                        case 6:

                            permission3 = "Storage";
                            break;
                        case 7:
                            permission4 = "Phone";
                            break;
                        case 8:
                            permission5 = "Contacts";
                            break;
                    }

                if (!permission1.equals(""))
                    warning = warning + permission1;
                if (!permission2.equals(""))
                    warning = warning + "," + permission2;

                if (!permission3.equals(""))
                    warning = warning + "," + permission3;


                if (!permission4.equals(""))
                    warning = warning + "," + permission4;

                if (!permission5.equals(""))
                    warning = warning + "," + permission5;

            }
            else
            {
                Log.d("sthree","pop fffff first");
                Intent intent = new Intent(getApplicationContext(),com.dyolmart.Customer_Login.class);
                startActivity(intent);
            }
        }
        else
        {
             warning="Kindly go to Settings and allow Storage,SMS,Call,Contacts,Location permissions for this app.If not this app will fail to support you.";
        }
        txtViewMsg.setText(warning);

        btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inte.getStringExtra("NeverAsk").equals("false")) {
                    if (!checkPermissions(getApplicationContext())) {

                        finish();
                        Intent intent = new Intent(getApplicationContext(), com.dyolmart.GrantPermission.class);
                        startActivity(intent);
                    }
                    else
                    {
                        finish();
                        Log.d("sthree","pop secst");
                        Intent intent = new Intent(getApplicationContext(), com.dyolmart.Customer_Login.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    if (!checkPermissions(getApplicationContext())) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                        com.dyolmart.RequestPermissionWarning.this.startActivity(intent);
                    }
                    else
                    {
                        finish();
                        Log.d("sthree","pop first");
                        Intent intent = new Intent(getApplicationContext(), com.dyolmart.Customer_Login.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
     boolean checkPermissions(Context c) {
        int result;
        listPermissionsNeeded.clear();
        for (String p : permissions) {
            //check whether permissions already given or not
            result = ContextCompat.checkSelfPermission(c, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);

            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            return false;
        }
        return true;
    }
}
